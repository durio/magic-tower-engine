package cn.edu.tsinghua.academic.c00740273.magictower.engine;

import java.util.Map;

public interface Game {

	public Event initialize() throws DataException;

	public GameData getGameData();

	public GameData setGameData(GameData gameData);

	public Coordinate getCurrentCoordinate();

	public Object getAttribute(String key);

	public Map<String, Object> getAttributes();

	public Tile getTile(Coordinate coord);

	public Coordinate getMaximumCoordinate();

	public GameRenderer getRenderer();

	/**
	 * Calculate the event happening if the character moves to the given
	 * coordinate.
	 * 
	 * @param coord
	 * @param args
	 * @return
	 */
	public Event attemptMoveTo(Coordinate coord, Map<String, Object> args);

	/**
	 * Apply the event given in attemptMoveTo().
	 * 
	 * In theory, passing in a mock event can be accepted, which is useful for
	 * debugging.
	 * 
	 * This function doesn't check game termination. Use simulateEvent() before
	 * this function if you need that.
	 * 
	 * @param event
	 */
	public void applyEvent(Event event);

	/**
	 * Throw GameTerminationException exception when applyEvent does, but no
	 * actual data are modified.
	 * 
	 * @param event
	 * @throws GameTerminationException
	 */
	public void simulateEvent(Event event) throws GameTerminationException;

}