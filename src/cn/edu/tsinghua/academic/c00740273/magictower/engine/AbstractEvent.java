package cn.edu.tsinghua.academic.c00740273.magictower.engine;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractEvent implements Event {

	protected Coordinate coordinate;
	protected Map<String, Object> attributeChanges;
	protected Map<Coordinate, Tile> tileChanges;

	public AbstractEvent(Coordinate coord) {
		this.coordinate = coord;
		this.attributeChanges = new HashMap<String, Object>();
		this.tileChanges = new HashMap<Coordinate, Tile>();
	}

	@Override
	public Coordinate getCoordinate() {
		return this.coordinate;
	}

	public Coordinate setCoordinate(Coordinate coord) {
		Coordinate prev = this.coordinate;
		this.coordinate = coord;
		return prev;
	}

	@Override
	public Map<String, Object> getAttributeChanges() {
		return this.attributeChanges;
	}

	public Map<String, Object> setAttributeChanges(
			Map<String, Object> attributeChanges) {
		Map<String, Object> prev = this.attributeChanges;
		this.attributeChanges = attributeChanges;
		return prev;
	}

	public Object setAttributeChange(String key, Object value) {
		Object prev = this.attributeChanges.get(key);
		this.attributeChanges.put(key, value);
		return prev;
	}

	@Override
	public Map<Coordinate, Tile> getTileChanges() {
		return this.tileChanges;
	}

	public Map<Coordinate, Tile> setTileChanges(
			Map<Coordinate, Tile> tileChanges) {
		Map<Coordinate, Tile> prev = this.tileChanges;
		this.tileChanges = tileChanges;
		return prev;
	}

	public Tile setTileChange(Coordinate key, Tile value) {
		Tile prev = this.tileChanges.get(key);
		this.tileChanges.put(key, value);
		return prev;
	}

}
