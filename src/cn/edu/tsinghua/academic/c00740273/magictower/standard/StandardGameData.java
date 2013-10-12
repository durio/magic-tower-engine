package cn.edu.tsinghua.academic.c00740273.magictower.standard;

import java.util.List;
import java.util.Map;

import cn.edu.tsinghua.academic.c00740273.magictower.engine.AbstractGameData;
import cn.edu.tsinghua.academic.c00740273.magictower.engine.Coordinate;

public class StandardGameData extends AbstractGameData {

	private static final long serialVersionUID = 1L;

	protected List<String> failureAttributeChecks;
	protected List<String> successAttributeChecks;
	protected String createdFrom;

	public StandardGameData(Coordinate currentCoordinate,
			Map<String, Object> attributes, StandardTile[][][] tiles) {
		super(currentCoordinate, attributes, tiles);
	}
}
