package cn.edu.tsinghua.academic.c00740273.magictower.engine;

import java.util.Map;

public abstract class AbstractGame implements TileArrayGame {

	protected AbstractGameData gameData;

	@Override
	public AbstractGameData getGameData() {
		return this.gameData;
	}

	@Override
	public GameData setGameData(GameData gameData) {
		GameData prev = this.gameData;
		this.gameData = (AbstractGameData) gameData;
		return prev;
	}

	@Override
	public Coordinate getCurrentCoordinate() {
		return this.getGameData().getCurrentCoordinate();
	}

	@Override
	public Object getAttribute(String key) {
		return this.getGameData().getAttribute(key);
	}

	public Object getAttribute(Event event, String key) {
		Object attribute = event.getAttributeChanges().get(key);
		if (attribute != null) {
			return attribute;
		} else {
			return this.getGameData().getAttribute(key);
		}
	}

	@Override
	public Map<String, Object> getAttributes() {
		return this.getGameData().getAttributes();
	}

	@Override
	public Tile getTile(Coordinate coord) {
		return this.getGameData().getTile(coord);
	}

	public Tile getTile(Event event, Coordinate coord) {
		Tile tile = event.getTileChanges().get(coord);
		if (tile != null) {
			return tile;
		} else {
			return this.getGameData().getTile(coord);
		}
	}

	@Override
	public Tile[][][] getTiles() {
		return this.getGameData().getTiles();
	}

	@Override
	public void applyEvent(Event event) {
		this.getGameData().applyEvent(event);
	}

	@Override
	public Event initialize() throws DataException {
		GameData gameData = this.initializeGameData();
		Event event = this.initializeFirstEvent();
		this.setGameData(gameData);
		return event;
	}

	protected abstract AbstractEvent initializeFirstEvent()
			throws DataException;

	protected abstract AbstractGameData initializeGameData()
			throws DataException;

}
