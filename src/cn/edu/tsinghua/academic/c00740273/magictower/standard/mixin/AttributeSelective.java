package cn.edu.tsinghua.academic.c00740273.magictower.standard.mixin;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.tsinghua.academic.c00740273.magictower.engine.Coordinate;
import cn.edu.tsinghua.academic.c00740273.magictower.standard.CharacterTile;
import cn.edu.tsinghua.academic.c00740273.magictower.standard.ClassUtils;
import cn.edu.tsinghua.academic.c00740273.magictower.standard.DataFormatException;
import cn.edu.tsinghua.academic.c00740273.magictower.standard.RegularTile;
import cn.edu.tsinghua.academic.c00740273.magictower.standard.RegularTileMixin;
import cn.edu.tsinghua.academic.c00740273.magictower.standard.StandardEvent;
import cn.edu.tsinghua.academic.c00740273.magictower.standard.StandardGame;

public class AttributeSelective implements RegularTileMixin {

	private static final long serialVersionUID = 1L;

	protected static String[] supportedOperators = { "==", "!=", "<", ">",
			"<=", ">=" };
	protected String attributeName;
	protected Map<String, RegularTileMixin> mixins;
	protected long referenceValue;

	@Override
	public void initialize(JSONObject dataValue) throws JSONException,
			DataFormatException {
		this.mixins = new HashMap<String, RegularTileMixin>();
		this.attributeName = dataValue.getString("attribute");
		this.referenceValue = dataValue.getLong("reference");
		for (String operator : supportedOperators) {
			JSONObject dataMixinValue = dataValue.optJSONObject(operator);
			if (dataMixinValue != null) {
				this.mixins.put(operator, ClassUtils.makeMixin(dataMixinValue));
			}
		}
	}

	@Override
	public boolean enter(StandardEvent event, Coordinate coord,
			RegularTile tile, Coordinate sourceCoord, CharacterTile sourceTile,
			StandardGame game) {
		Number valueObj = (Number) game.getAttribute(event, this.attributeName);
		long value = valueObj.longValue();
		String[] matchingOperators = this.matchedOperators(value);
		for (String operator : matchingOperators) {
			RegularTileMixin mixin = this.mixins.get(operator);
			if (mixin != null) {
				mixin.enter(event, coord, tile, sourceCoord, sourceTile, game);
			}
		}
		return true;
	}

	public String[] matchedOperators(long value) {
		if (value < this.referenceValue) {
			return new String[] { "!=", "<", "<=" };
		} else if (value > this.referenceValue) {
			return new String[] { "!=", ">", ">=" };
		} else {
			return new String[] { "==", "<=", ">=" };
		}
	}

	/**
	 * @return the attributeName
	 */
	public String getAttributeName() {
		return this.attributeName;
	}
}
