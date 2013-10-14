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
			StandardGame game) {
		StandardTile targetTile = (StandardTile) game.getTile(event,
				this.targetCoord);
		if (targetTile instanceof RegularTile) {
			RegularTile targetRegularTile = (RegularTile) targetTile;
			if (targetRegularTile.getMixin() != null) {
				targetRegularTile.getMixin().enter(event, this.targetCoord,
						targetRegularTile, sourceCoord, sourceTile, game);
			}
			return false;
		}
		return true;
	}
}
