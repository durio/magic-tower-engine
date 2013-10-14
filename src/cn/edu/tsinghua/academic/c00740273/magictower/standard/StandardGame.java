package cn.edu.tsinghua.academic.c00740273.magictower.standard;

import cn.edu.tsinghua.academic.c00740273.magictower.engine.AbstractGame;
import cn.edu.tsinghua.academic.c00740273.magictower.engine.Coordinate;
import cn.edu.tsinghua.academic.c00740273.magictower.engine.Event;
import cn.edu.tsinghua.academic.c00740273.magictower.engine.GameData;
import cn.edu.tsinghua.academic.c00740273.magictower.engine.GameFailureTerminationException;
import cn.edu.tsinghua.academic.c00740273.magictower.engine.GameRenderer;
import cn.edu.tsinghua.academic.c00740273.magictower.engine.GameSuccessTerminationException;
import cn.edu.tsinghua.academic.c00740273.magictower.engine.GameTerminationException;

public class StandardGame extends AbstractGame {

	protected StandardGameDataFactory gameDataFactory;
	protected Coordinate maximumCoordinate;

	public StandardGame() {
		// For unserialization.
	}

	public StandardGame(String data) {
		this(new StandardGameDataFactory(data));
	}

	public StandardGame(StandardGameDataFactory gameDataFactory) {
		this.gameDataFactory = gameDataFactory;
	}

	@Override
	public StandardGameData getGameData() {
		return (StandardGameData) this.gameData;
	}

	@Override
	public GameData setGameData(GameData gameData) {
		GameData prev = this.gameData;
		this.gameData = (StandardGameData) gameData;
		this.maximumCoordinate = null;
		return prev;
	}

	@Override
	public Coordinate getMaximumCoordinate() {
		if (this.maximumCoordinate == null) {
			int maxZ = this.gameData.getTiles().length - 1;
			int maxX = this.gameData.getTiles()[0].length - 1;
			int maxY = this.gameData.getTiles()[0][0].length - 1;
			this.maximumCoordinate = new Coordinate(maxZ, maxX, maxY);
		}
		return this.maximumCoordinate;
	}

	@Override
	public GameRenderer getRenderer() {
		if (this.gameDataFactory == null && this.gameData != null
				&& this.getGameData().createdFrom != null) {
			this.gameDataFactory = new StandardGameDataFactory(
					this.getGameData().createdFrom);
		}
		if (this.gameDataFactory != null) {
			try {
				// This may cause a re-parse for games from unserialization,
				// which is slow, but we don't have another way to get the
				// renderer object back...
				return this.gameDataFactory.makeRenderer();
			} catch (DataFormatException e) {
			}
		}
		return null;
	}

	@Override
	public StandardEvent attemptMoveTo(Coordinate coord) {
		Coordinate sourceCoord = this.getCurrentCoordinate();
		// Out of bound. Keep not moved.
		if (coord.compareCoordinate(Coordinate.ZERO, -1) < 0
				|| coord.compareCoordinate(this.getMaximumCoordinate(), 1) > 0) {
			return new StandardEvent(sourceCoord);
		}
		StandardTile tile = (StandardTile) this.gameData.getTile(coord);
		StandardTile sourceTile = (StandardTile) this.gameData
				.getTile(sourceCoord);
		StandardEvent event = tile.enter(coord, sourceCoord,
				(CharacterTile) sourceTile, this);
		return event;
	}

	@Override
	public void simulateEvent(Event event) throws GameTerminationException {
		for (String key : this.getGameData().failureAttributeChecks) {
			if (!event.getAttributeChanges().containsKey(key)) {
				continue;
			}
			if (((Number) event.getAttributeChanges().get(key)).longValue() < 0) {
				throw new GameFailureTerminationException(key
						+ " is now negative.", event);
			}
		}
		for (String key : this.getGameData().successAttributeChecks) {
			if (!event.getAttributeChanges().containsKey(key)) {
				continue;
			}
			if (((Number) event.getAttributeChanges().get(key)).longValue() > 0) {
				throw new GameSuccessTerminationException(key
						+ " is now positive.", event);
			}
		}
	}

	@Override
	protected StandardEvent initializeFirstEvent() throws DataFormatException {
		return this.gameDataFactory.makeFirstEvent();
	}

	@Override
	protected StandardGameData initializeGameData() throws DataFormatException {
		return this.gameDataFactory.makeGameData();
	}
}
