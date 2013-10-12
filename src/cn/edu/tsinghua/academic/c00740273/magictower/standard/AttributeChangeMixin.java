package cn.edu.tsinghua.academic.c00740273.magictower.standard;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.tsinghua.academic.c00740273.magictower.engine.Coordinate;

public class AttributeChangeMixin implements RegularTileMixin {

	protected String attributeName;
	protected String operator;
	protected long longOperand;
	protected double doubleOperand;
	protected Object objectOperand;

	@Override
	public void initialize(JSONObject dataMixinValue) throws JSONException,
			DataFormatException {
		this.attributeName = dataMixinValue.getString("attribute");
		this.operator = dataMixinValue.getString("operator");
		if (this.operator.equals("+")) {
			this.longOperand = dataMixinValue.getLong("operand");
		} else if (this.operator.equals("*")) {
			this.doubleOperand = dataMixinValue.getDouble("operand");
		} else if (this.operator.equals("=")) {
			this.objectOperand = dataMixinValue.get("operand");
		} else {
			throw new DataFormatException("Invalid operator: " + this.operator);
		}
	}

	@Override
	public boolean enter(StandardEvent event, Coordinate coord,
			RegularTile tile, Coordinate sourceCoord, CharacterTile sourceTile,
			Map<String, Object> args, StandardGame game) {
		Object oldValue = event.getAttributeChanges().get(this.attributeName);
		if (oldValue == null) {
			oldValue = game.getAttribute(this.attributeName);
		}
		if (this.operator.equals("+")) {
			event.setAttributeChange(this.attributeName, ((Long) oldValue)
					+ this.longOperand);
		} else if (this.operator.equals("*")) {
			event.setAttributeChange(this.attributeName, ((Double) oldValue)
					* this.doubleOperand);
		} else if (this.operator.equals("=")) {
			event.setAttributeChange(this.attributeName, this.objectOperand);
		}
		return true;
	}
}
