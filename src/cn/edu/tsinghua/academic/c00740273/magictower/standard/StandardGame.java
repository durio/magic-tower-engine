package cn.edu.tsinghua.academic.c00740273.magictower.standard;

import java.util.Map;

import cn.edu.tsinghua.academic.c00740273.magictower.engine.AbstractGame;
import cn.edu.tsinghua.academic.c00740273.magictower.engine.AbstractGameData;
import cn.edu.tsinghua.academic.c00740273.magictower.engine.Coordinate;
import cn.edu.tsinghua.academic.c00740273.magictower.engine.Event;
import cn.edu.tsinghua.academic.c00740273.magictower.engine.GameRenderer;
import cn.edu.tsinghua.academic.c00740273.magictower.engine.GameTerminationException;

public class StandardGame extends AbstractGame {

	protected StandardGameData gameData;

	@Override
	public Coordinate getMaximumCoordinate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameRenderer getRenderer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Event attemptMoveTo(Coordinate coord, Map<String, Object> args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void simulateEvent(Event event) throws GameTerminationException {
		// TODO Auto-generated method stub
	}

	@Override
	protected Event initializeFirstEvent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AbstractGameData initializeGameData() {
		StandardGameData gameData = new StandardGameData();
		return gameData;
	}

}
