package cn.edu.tsinghua.academic.c00740273.magictower.engine;

import java.util.Map;

public abstract class AbstractGame implements TileArrayGame {

	protected AbstractGameData gameData;

	@Override
	public AbstractGameData getGameData() {
		return this.gameData;
	}

	@Override
	public void setGameData(GameData gameData) {
		this.gameData = (AbstractGameData) gameData;
	}

	@Override
	public Coordinate getCurrentCoordinate() {
		return this.gameData.getCurrentCoordinate();
	}

	@Override
	public Object getAttribute(String key) {
		return this.gameData.getAttribute(key);
	}

	@Override
	public Map<String, Object> getAttributes() {
		return this.gameData.getAttributes();
	}

	@Override
	public Tile getTile(Coordinate coord) {
		return this.gameData.getTile(coord);
	}

	@Override
	public Tile[][][] getTiles() {
		return this.gameData.getTiles();
	}

	@Override
	public void applyEvent(Event event) {
		this.gameData.applyEvent(event);
	}

	@Override
	public Event initialize() {
		this.gameData = this.initializeGameData();
		return this.initializeFirstEvent();
	}

	protected abstract Event initializeFirstEvent();

	protected abstract AbstractGameData initializeGameData();

}
