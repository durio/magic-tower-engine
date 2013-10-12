package cn.edu.tsinghua.academic.c00740273.magictower.standard;

import java.util.Map;

import cn.edu.tsinghua.academic.c00740273.magictower.engine.AbstractGame;
import cn.edu.tsinghua.academic.c00740273.magictower.engine.Coordinate;
import cn.edu.tsinghua.academic.c00740273.magictower.engine.Event;
import cn.edu.tsinghua.academic.c00740273.magictower.engine.GameFailureTerminationException;
import cn.edu.tsinghua.academic.c00740273.magictower.engine.GameRenderer;
import cn.edu.tsinghua.academic.c00740273.magictower.engine.GameSuccessTerminationException;
import cn.edu.tsinghua.academic.c00740273.magictower.engine.GameTerminationException;

public class StandardGame extends AbstractGame {

	protected StandardGameData gameData;
	protected StandardGameDataFactory gameDataFactory;

	public StandardGame(String data) {
		this(new StandardGameDataFactory(data));
	}

	public StandardGame(StandardGameDataFactory gameDataFactory) {
		this.gameDataFactory = gameDataFactory;
	}

	@Override
	public Coordinate getMaximumCoordinate() {
		int maxZ = this.gameData.getTiles().length - 1;
		int maxX = this.gameData.getTiles()[0].length - 1;
		int maxY = this.gameData.getTiles()[0][0].length - 1;
		return new Coordinate(maxZ, maxX, maxY);
	}

	@Override
	public GameRenderer getRenderer() {
		try {
			return this.gameDataFactory.makeRenderer();
		} catch (DataFormatException e) {
			return null;
		}
	}

	@Override
	public StandardEvent attemptMoveTo(Coordinate coord,
			Map<String, Object> args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void simulateEvent(Event event) throws GameTerminationException {
		for (String key : this.gameData.failureAttributeChecks) {
			if (((Long) this.gameData.getAttribute(key)).longValue() < 0) {
				throw new GameFailureTerminationException(key
						+ " is now negative.");
			}
		}
		for (String key : this.gameData.successAttributeChecks) {
			if (((Long) this.gameData.getAttribute(key)).longValue() > 0) {
				throw new GameSuccessTerminationException(key
						+ " is now positive.");
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
