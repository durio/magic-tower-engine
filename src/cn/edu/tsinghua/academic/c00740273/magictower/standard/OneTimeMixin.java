package cn.edu.tsinghua.academic.c00740273.magictower.standard;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.tsinghua.academic.c00740273.magictower.engine.Coordinate;

public class OneTimeMixin implements RegularTileMixin {

	protected RegularTile nextRegularTile;

	@Override
	public void initialize(JSONObject dataMixinValue) throws JSONException,
			DataFormatException {
		this.nextRegularTile = new RegularTile();
		this.nextRegularTile.initialize(dataMixinValue.getJSONObject("next"));
	}

	@Override
	public boolean enter(StandardEvent event, Coordinate coord,
			RegularTile tile, Coordinate sourceCoord, CharacterTile sourceTile,
			Map<String, Object> args, StandardGame game) {
		StandardTile newTile = (StandardTile) event.getTileChanges().get(coord);
		if (newTile instanceof CharacterTile) {
			((CharacterTile) newTile).tileAfterLeave = this.nextRegularTile;
		} else {
			event.setTileChange(coord, this.nextRegularTile);
		}
		return true;
	}
}
