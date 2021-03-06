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
import cn.edu.tsinghua.academic.c00740273.magictower.standard.StandardTile;

public class Accessible implements RegularTileMixin {

	private static final long serialVersionUID = 1L;

	@Override
	public void initialize(JSONObject dataMixinValue) throws JSONException,
			DataFormatException {
	}

	@Override
	public boolean enter(StandardEvent event, Coordinate coord,
			RegularTile tile, Coordinate sourceCoord, CharacterTile sourceTile,
			StandardGame game) {
		StandardTile newTile = (StandardTile) game.getTile(event, coord);
		if (newTile instanceof CharacterTile) {
			// This shouldn't happen.
			// Maybe another AccessibleMixin has already been used?
		} else {
			event.setTileChange(coord, new CharacterTile((RegularTile) newTile));
			event.setTileChange(sourceCoord, sourceTile.leave());
		}
		event.setCoordinate(coord);
		return true;
	}

}
