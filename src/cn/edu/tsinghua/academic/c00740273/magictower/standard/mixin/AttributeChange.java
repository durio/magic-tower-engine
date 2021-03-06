package cn.edu.tsinghua.academic.c00740273.magictower.standard.mixin;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.tsinghua.academic.c00740273.magictower.engine.Coordinate;
import cn.edu.tsinghua.academic.c00740273.magictower.standard.CharacterTile;
import cn.edu.tsinghua.academic.c00740273.magictower.standard.DataFormatException;
import cn.edu.tsinghua.academic.c00740273.magictower.standard.RegularTile;
import cn.edu.tsinghua.academic.c00740273.magictower.standard.RegularTileMixin;
import cn.edu.tsinghua.academic.c00740273.magictower.standard.StandardEvent;
import cn.edu.tsinghua.academic.c00740273.magictower.standard.StandardGame;

public class AttributeChange implements RegularTileMixin {

	private static final long serialVersionUID = 1L;

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
			StandardGame game) {
		Object oldValue = game.getAttribute(event, this.attributeName);
		if (this.operator.equals("+")) {
			event.setAttributeChange(this.attributeName,
					((Number) oldValue).longValue() + this.longOperand);
		} else if (this.operator.equals("*")) {
			event.setAttributeChange(this.attributeName,
					((Number) oldValue).doubleValue() * this.doubleOperand);
		} else if (this.operator.equals("=")) {
			event.setAttributeChange(this.attributeName, this.objectOperand);
		}
		return true;
	}
}
