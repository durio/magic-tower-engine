package cn.edu.tsinghua.academic.c00740273.magictower.standard;

import java.util.Map;

import cn.edu.tsinghua.academic.c00740273.magictower.engine.Coordinate;
import cn.edu.tsinghua.academic.c00740273.magictower.engine.Tile;

public interface StandardTile extends Tile, Initializable {

	public Map<String, Object> getRenderingData();

	public StandardEvent enter(Coordinate coord, Coordinate sourceCoord,
			CharacterTile sourceTile, StandardGame game);
}
