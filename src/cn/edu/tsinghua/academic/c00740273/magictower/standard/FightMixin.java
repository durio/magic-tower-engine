package cn.edu.tsinghua.academic.c00740273.magictower.standard;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.tsinghua.academic.c00740273.magictower.engine.Coordinate;

public class FightMixin implements RegularTileMixin {

	protected String attackAttributeName;
	protected String defenseAttributeName;
	protected String healthAttributeName;
	protected String deathAttributeName;

	protected long opponentInitialAttack;
	protected long opponentInitialDefense;
	protected long opponentInitialHealth;

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
			Map<String, Object> args, StandardGame game) {
		long opponentAttack = this.opponentInitialAttack;
		long opponentDefense = this.opponentInitialDefense;
		long opponentHealth = this.opponentInitialHealth;
		Number selfAttackObj = (Number) event.getAttributeChanges().get(
				this.attackAttributeName);
		Number selfDefenseObj = (Number) event.getAttributeChanges().get(
				this.defenseAttributeName);
		Number selfHealthObj = (Number) event.getAttributeChanges().get(
				this.healthAttributeName);
		if (selfAttackObj == null) {
			selfAttackObj = (Number) game
					.getAttribute(this.attackAttributeName);
		}
		if (selfDefenseObj == null) {
			selfDefenseObj = (Number) game
					.getAttribute(this.defenseAttributeName);
		}
		if (selfHealthObj == null) {
			selfHealthObj = (Number) game
					.getAttribute(this.healthAttributeName);
		}
		long selfAttack = selfAttackObj.longValue();
		long selfDefense = selfDefenseObj.longValue();
		long selfHealth = selfHealthObj.longValue();
		if (selfAttack <= opponentDefense) {
			event.setAttributeChange(this.deathAttributeName, -1);
			return true;
		}
		while (opponentHealth > 0) {
			opponentHealth -= selfAttack - opponentDefense;
			selfHealth -= opponentAttack - selfDefense;
		}
		event.setAttributeChange(this.attackAttributeName, selfAttack);
		event.setAttributeChange(this.defenseAttributeName, selfDefense);
		event.setAttributeChange(this.healthAttributeName, selfHealth);
		return true;
	}
}
