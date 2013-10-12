package cn.edu.tsinghua.academic.c00740273.magictower.standard;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.tsinghua.academic.c00740273.magictower.engine.Coordinate;

/**
 * A tile that the character is not staying.
 */
public class RegularTile implements StandardTile {

	private static final long serialVersionUID = 1L;

	protected RegularTileMixin mixin;

	@Override
	public void initialize(JSONObject dataTileValue) throws JSONException,
			DataFormatException {
		JSONObject dataMixinValue = dataTileValue.optJSONObject("mixins");
		if (dataMixinValue != null) {
			this.mixin = ClassUtils.makeMixin(dataMixinValue);
		}
	}

	@Override
	public StandardEvent enter(Coordinate coord, Coordinate sourceCoord,
			CharacterTile sourceTile, Map<String, Object> args,
			StandardGame game) {
		StandardEvent event = new StandardEvent(sourceCoord);
		if (this.mixin != null) {
			this.mixin.enter(event, coord, this, sourceCoord, sourceTile, args,
					game);
		}
		return event;
	}
}
