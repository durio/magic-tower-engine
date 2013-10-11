package cn.edu.tsinghua.academic.c00740273.magictower.engine;

import java.util.Map;

public abstract class AbstractEvent implements Event {

	protected Coordinate coordinate;
	protected Map<String, Object> attributeChanges;
	protected Map<Coordinate, Tile> tileChanges;
	protected Map<String, Object> extraInformation;

	@Override
	public Coordinate getCoordinate() {
		return this.coordinate;
	}

	@Override
	public Map<String, Object> getAttributeChanges() {
		return this.attributeChanges;
	}

	@Override
	public Map<Coordinate, Tile> getTileChanges() {
		return this.tileChanges;
	}

	@Override
	public Map<String, Object> getExtraInformation() {
		return this.extraInformation;
	}

}
