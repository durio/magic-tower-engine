package cn.edu.tsinghua.academic.c00740273.magictower.engine;

import java.util.Map;

@SuppressWarnings("serial")
public abstract class AbstractGameData implements GameData {

	protected Coordinate currentCoordinate;
	protected Map<String, Object> attributes;
	protected Tile[][][] tiles;

	public AbstractGameData(Coordinate currentCoordinate,
			Map<String, Object> attributes, Tile[][][] tiles) {
		this.currentCoordinate = currentCoordinate;
		this.attributes = attributes;
		this.tiles = tiles;
	}

	public Coordinate getCurrentCoordinate() {
		return this.currentCoordinate;
	}

	public Coordinate setCurrentCoordinate(Coordinate coord) {
		Coordinate prev = this.currentCoordinate;
		this.currentCoordinate = coord;
		return prev;
	}

	public Tile getTile(Coordinate coord) {
		return this.tiles[coord.getZ()][coord.getX()][coord.getY()];
	}

	public Tile[][][] getTiles() {
		return this.tiles;
	}

	public Tile setTile(Coordinate coord, Tile tile) {
		Tile prev = this.tiles[coord.getZ()][coord.getX()][coord.getY()];
		this.tiles[coord.getZ()][coord.getX()][coord.getY()] = tile;
		return prev;
	}

	public Object getAttribute(String key) {
		return this.attributes.get(key);
	}

	public Map<String, Object> getAttributes() {
		return this.attributes;
	}

	public Object setAttribute(String key, Object value) {
		Object prev = this.attributes.get(key);
		this.attributes.put(key, value);
		return prev;
	}

	public void applyEvent(Event event) {
		this.setCurrentCoordinate(event.getCoordinate());
		for (Map.Entry<String, Object> attributeChange : event
				.getAttributeChanges().entrySet()) {
			this.setAttribute(attributeChange.getKey(),
					attributeChange.getValue());
		}
		for (Map.Entry<Coordinate, Tile> tileChange : event.getTileChanges()
				.entrySet()) {
			this.setTile(tileChange.getKey(), tileChange.getValue());
		}
	}

}
