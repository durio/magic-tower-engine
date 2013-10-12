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
		Long selfAttack = (Long) event.getAttributeChanges().get(
				this.attackAttributeName);
		Long selfDefense = (Long) event.getAttributeChanges().get(
				this.defenseAttributeName);
		Long selfHealth = (Long) event.getAttributeChanges().get(
				this.healthAttributeName);
		if (selfAttack == null) {
			selfAttack = (Long) game.getAttribute(this.attackAttributeName);
		}
		if (selfDefense == null) {
			selfDefense = (Long) game.getAttribute(this.defenseAttributeName);
		}
		if (selfHealth == null) {
			selfHealth = (Long) game.getAttribute(this.healthAttributeName);
		}
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
