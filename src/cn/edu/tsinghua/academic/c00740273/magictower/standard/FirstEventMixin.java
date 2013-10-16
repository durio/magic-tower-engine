package cn.edu.tsinghua.academic.c00740273.magictower.standard;

import java.io.Serializable;

import cn.edu.tsinghua.academic.c00740273.magictower.engine.Coordinate;

public interface FirstEventMixin extends Initializable, Serializable {

	public boolean firstEvent(StandardEvent event, Coordinate coord,
			CharacterTile tile);
}
