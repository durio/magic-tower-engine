package cn.edu.tsinghua.academic.c00740273.magictower.standard.mixin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.tsinghua.academic.c00740273.magictower.engine.Coordinate;
import cn.edu.tsinghua.academic.c00740273.magictower.standard.CharacterTile;
import cn.edu.tsinghua.academic.c00740273.magictower.standard.DataFormatException;
import cn.edu.tsinghua.academic.c00740273.magictower.standard.RegularTile;
import cn.edu.tsinghua.academic.c00740273.magictower.standard.RegularTileMixin;
import cn.edu.tsinghua.academic.c00740273.magictower.standard.StandardEvent;
import cn.edu.tsinghua.academic.c00740273.magictower.standard.StandardGame;

public class Fight implements RegularTileMixin {

	private static final long serialVersionUID = 1L;

	protected String attackAttributeName;
	protected String defenseAttributeName;
	protected String healthAttributeName;
	protected String deathAttributeName;

	protected long opponentInitialAttack;
	protected long opponentInitialDefense;
	protected long opponentInitialHealth;

	public class Attributes {

		protected long opponentAttack;
		protected long opponentDefense;
		protected long opponentHealth;

		protected long selfAttack;
		protected long selfDefense;
		protected long selfHealth;

		/**
		 * @param opponentAttack
		 * @param opponentDefense
		 * @param opponentHealth
		 * @param selfAttack
		 * @param selfDefense
		 * @param selfHealth
		 */
		private Attributes(long opponentAttack, long opponentDefense,
				long opponentHealth, long selfAttack, long selfDefense,
				long selfHealth) {
			this.opponentAttack = opponentAttack;
			this.opponentDefense = opponentDefense;
			this.opponentHealth = opponentHealth;
			this.selfAttack = selfAttack;
			this.selfDefense = selfDefense;
			this.selfHealth = selfHealth;
		}

		/**
		 * @return the opponentAttack
		 */
		public long getOpponentAttack() {
			return this.opponentAttack;
		}

		/**
		 * @return the opponentDefense
		 */
		public long getOpponentDefense() {
			return this.opponentDefense;
		}

		/**
		 * @return the opponentHealth
		 */
		public long getOpponentHealth() {
			return this.opponentHealth;
		}

		/**
		 * @return the selfAttack
		 */
		public long getSelfAttack() {
			return this.selfAttack;
		}

		/**
		 * @return the selfDefense
		 */
		public long getSelfDefense() {
			return this.selfDefense;
		}

		/**
		 * @return the selfHealth
		 */
		public long getSelfHealth() {
			return this.selfHealth;
		}

		public void extractTo(Map<String, Long> fightDetails, String suffix) {
			fightDetails.put("opponent-attack-" + suffix, this.opponentAttack);
			fightDetails
					.put("opponent-defense-" + suffix, this.opponentDefense);
			fightDetails.put("opponent-health-" + suffix, this.opponentHealth);
			fightDetails.put("self-attack-" + suffix, this.selfAttack);
			fightDetails.put("self-defense-" + suffix, this.selfDefense);
			fightDetails.put("self-health-" + suffix, this.selfHealth);
		}

		public Attributes nextAttributes(boolean selfAttacking) {
			if (selfAttacking) {
				return new Attributes(this.opponentAttack,
						this.opponentDefense, this.opponentHealth
								- (this.selfAttack - this.opponentDefense),
						this.selfAttack, this.selfDefense, this.selfHealth);
			} else {
				return new Attributes(this.opponentAttack,
						this.opponentDefense, this.opponentHealth,
						this.selfAttack, this.selfDefense, this.selfHealth
								- Math.max(0L, this.opponentAttack
										- this.selfDefense));
			}
		}
	}

	@Override
	public void initialize(JSONObject dataMixinValue) throws JSONException,
			DataFormatException {
		this.attackAttributeName = dataMixinValue.getString("attack-attribute");
		this.defenseAttributeName = dataMixinValue
				.getString("defense-attribute");
		this.healthAttributeName = dataMixinValue.getString("health-attribute");
		this.deathAttributeName = dataMixinValue.getString("death-attribute");
		this.opponentInitialAttack = dataMixinValue.getLong("attack-opponent");
		this.opponentInitialDefense = dataMixinValue
				.getLong("defense-opponent");
		this.opponentInitialHealth = dataMixinValue.getLong("health-opponent");
	}

	@Override
	public boolean enter(StandardEvent event, Coordinate coord,
			RegularTile tile, Coordinate sourceCoord, CharacterTile sourceTile,
			StandardGame game) {
		long opponentAttack = this.opponentInitialAttack;
		long opponentDefense = this.opponentInitialDefense;
		long opponentHealth = this.opponentInitialHealth;
		long selfAttack = ((Number) game.getAttribute(event,
				this.attackAttributeName)).longValue();
		long selfDefense = ((Number) game.getAttribute(event,
				this.defenseAttributeName)).longValue();
		long selfHealth = ((Number) game.getAttribute(event,
				this.healthAttributeName)).longValue();
		Attributes attrs = this.new Attributes(opponentAttack, opponentDefense,
				opponentHealth, selfAttack, selfDefense, selfHealth);
		Map<String, Long> fightDetails = new HashMap<String, Long>();
		attrs.extractTo(fightDetails, "before");
		if (selfAttack <= opponentDefense) {
			fightDetails.put("quick-death", -1L);
			event.setAttributeChange(this.deathAttributeName, -1L);
			event.addExtraInformation("fight-details", fightDetails);
			event.addExtraInformation("fight-logs", null);
			return true;
		} else {
			fightDetails.put("quick-death", 0L);
		}
		List<Attributes> attributeLog = new ArrayList<Attributes>();
		attributeLog.add(attrs);
		boolean selfAttacking = true;
		while (attrs.opponentHealth > 0 && attrs.selfHealth > 0) {
			attrs = attrs.nextAttributes(selfAttacking);
			attributeLog.add(attrs);
			selfAttacking = !selfAttacking;
		}
		event.setAttributeChange(this.attackAttributeName, attrs.selfAttack);
		event.setAttributeChange(this.defenseAttributeName, attrs.selfDefense);
		event.setAttributeChange(this.healthAttributeName, attrs.selfHealth);
		attrs.extractTo(fightDetails, "after");
		event.addExtraInformation("fight-details", fightDetails);
		event.addExtraInformation("fight-logs", attributeLog);
		return true;
	}
}
