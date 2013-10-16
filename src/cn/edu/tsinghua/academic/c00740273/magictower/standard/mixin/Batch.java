package cn.edu.tsinghua.academic.c00740273.magictower.standard.mixin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.tsinghua.academic.c00740273.magictower.engine.Coordinate;
import cn.edu.tsinghua.academic.c00740273.magictower.standard.CharacterTile;
import cn.edu.tsinghua.academic.c00740273.magictower.standard.ClassUtils;
import cn.edu.tsinghua.academic.c00740273.magictower.standard.DataFormatException;
import cn.edu.tsinghua.academic.c00740273.magictower.standard.FirstEventMixin;
import cn.edu.tsinghua.academic.c00740273.magictower.standard.RegularTile;
import cn.edu.tsinghua.academic.c00740273.magictower.standard.RegularTileMixin;
import cn.edu.tsinghua.academic.c00740273.magictower.standard.StandardEvent;
import cn.edu.tsinghua.academic.c00740273.magictower.standard.StandardGame;

public class Batch implements RegularTileMixin, FirstEventMixin {

	private static final long serialVersionUID = 1L;

	protected RegularTileMixin[] regularTileMixins;
	protected FirstEventMixin[] firstEventMixins;

	@Override
	public void initialize(JSONObject dataMixinValue) throws JSONException,
			DataFormatException {
		JSONArray dataMixinArray = dataMixinValue.getJSONArray("mixins");
		this.regularTileMixins = new RegularTileMixin[dataMixinArray.length()];
		this.firstEventMixins = new FirstEventMixin[dataMixinArray.length()];
		for (int i = 0; i < dataMixinArray.length(); i++) {
			JSONObject dataChildMixinValue = dataMixinArray.getJSONObject(i);
			try {
				this.regularTileMixins[i] = ClassUtils
						.makeMixin(dataChildMixinValue);
			} catch (DataFormatException e) {
				if (!(e.getCause() instanceof ClassCastException)) {
					throw e;
				}
			}
			try {
				this.firstEventMixins[i] = ClassUtils
						.makeFirstEventMixin(dataChildMixinValue);
			} catch (DataFormatException e) {
				if (!(e.getCause() instanceof ClassCastException)) {
					throw e;
				}
			}
			if (this.regularTileMixins[i] == null
					&& this.firstEventMixins[i] == null) {
				// Since we've reached here, it must be a ClassCastException.
				throw new DataFormatException("Mixin with unknown type: "
						+ dataChildMixinValue.getString("class"));
			}
		}
	}

	@Override
	public boolean enter(StandardEvent event, Coordinate coord,
			RegularTile tile, Coordinate sourceCoord, CharacterTile sourceTile,
			StandardGame game) {
		for (RegularTileMixin mixin : this.regularTileMixins) {
			if (mixin == null) {
				continue;
			}
			if (!mixin.enter(event, coord, tile, sourceCoord, sourceTile, game)) {
				// Let's have it affect only one level of BatchMixin.
				return true;
			}
		}
		return true;
	}

	@Override
	public boolean firstEvent(StandardEvent event, Coordinate coord,
			CharacterTile tile) {
		for (FirstEventMixin mixin : this.firstEventMixins) {
			if (mixin == null) {
				continue;
			}
			if (!mixin.firstEvent(event, coord, tile)) {
				// Let's have it affect only one level of BatchMixin.
				return true;
			}
		}
		return true;
	}
}
