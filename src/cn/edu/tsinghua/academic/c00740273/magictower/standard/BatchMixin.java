package cn.edu.tsinghua.academic.c00740273.magictower.standard;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.tsinghua.academic.c00740273.magictower.engine.Coordinate;

public class BatchMixin implements RegularTileMixin {

	private static final long serialVersionUID = 1L;

	protected RegularTileMixin[] mixins;

	@Override
	public void initialize(JSONObject dataMixinValue) throws JSONException,
			DataFormatException {
		JSONArray dataMixinArray = dataMixinValue.getJSONArray("mixins");
		this.mixins = new RegularTileMixin[dataMixinArray.length()];
		for (int i = 0; i < this.mixins.length; i++) {
			JSONObject dataChildMixinValue = dataMixinArray.getJSONObject(i);
			this.mixins[i] = ClassUtils.makeMixin(dataChildMixinValue);
		}
	}

	@Override
	public boolean enter(StandardEvent event, Coordinate coord,
			RegularTile tile, Coordinate sourceCoord, CharacterTile sourceTile,
			Map<String, Object> args, StandardGame game) {
		for (RegularTileMixin mixin : this.mixins) {
			if (!mixin.enter(event, coord, tile, sourceCoord, sourceTile, args,
					game)) {
				// Let's have it affect only one level of BatchMixin.
				return true;
			}
		}
		return true;
	}

}
