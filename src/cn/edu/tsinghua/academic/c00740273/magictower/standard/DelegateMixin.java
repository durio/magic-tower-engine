package cn.edu.tsinghua.academic.c00740273.magictower.standard;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.tsinghua.academic.c00740273.magictower.engine.Coordinate;

public class DelegateMixin implements RegularTileMixin {

	private static final long serialVersionUID = 1L;

	protected Coordinate targetCoord;

	@Override
	public void initialize(JSONObject dataMixinValue) throws JSONException,
			DataFormatException {
		int coordZ = dataMixinValue.getInt("z");
		int coordX = dataMixinValue.getInt("x");
		int coordY = dataMixinValue.getInt("y");
		this.targetCoord = new Coordinate(coordZ, coordX, coordY);
	}

	@Override
	public boolean enter(StandardEvent event, Coordinate coord,
			RegularTile tile, Coordinate sourceCoord, CharacterTile sourceTile,
			Map<String, Object> args, StandardGame game) {
		StandardTile targetTile = (StandardTile) game.getTile(event,
				this.targetCoord);
		if (targetTile instanceof RegularTile) {
			RegularTile targetRegularTile = (RegularTile) targetTile;
			if (targetRegularTile.mixin != null) {
				targetRegularTile.mixin.enter(event, this.targetCoord,
						targetRegularTile, sourceCoord, sourceTile, args, game);
			}
			return false;
		}
		return true;
	}
}
