package cn.edu.tsinghua.academic.c00740273.magictower.standard;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.tsinghua.academic.c00740273.magictower.engine.Coordinate;

public class AccessibleMixin implements RegularTileMixin {

	@Override
	public void initialize(JSONObject dataMixinValue) throws JSONException,
			DataFormatException {
	}

	@Override
	public boolean enter(StandardEvent event, Coordinate coord,
			RegularTile tile, Coordinate sourceCoord, CharacterTile sourceTile,
			Map<String, Object> args, StandardGame game) {
		StandardTile newTile = (StandardTile) event.getTileChanges().get(coord);
		if (newTile instanceof CharacterTile) {
			event.setTileChange(coord, newTile);
		} else if (newTile instanceof RegularTile) {
			event.setTileChange(coord, new CharacterTile((RegularTile) newTile));
		} else {
			event.setTileChange(coord, new CharacterTile(tile));
		}
		event.setTileChange(sourceCoord, sourceTile.leave());
		return true;
	}

}
