package cn.edu.tsinghua.academic.c00740273.magictower.standard;

import java.io.Serializable;
import java.util.Map;

import cn.edu.tsinghua.academic.c00740273.magictower.engine.Coordinate;

public interface RegularTileMixin extends Initializable, Serializable {

	public boolean enter(StandardEvent event, Coordinate coord,
			RegularTile tile, Coordinate sourceCoord, CharacterTile sourceTile,
			Map<String, Object> args, StandardGame game);
}
