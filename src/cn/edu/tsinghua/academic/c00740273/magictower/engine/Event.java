package cn.edu.tsinghua.academic.c00740273.magictower.engine;

import java.util.Map;

public interface Event {
	/**
	 * Get the position of the character after the event happened.
	 * 
	 * @return New position, or null if the character didn't move.
	 */
	public Coordinate getCoordinate();

	/**
	 * Get attributes that have been changed in this event.
	 * 
	 * Attributes that haven't been changed are not included.
	 * 
	 * @see Engine.getAttributes()
	 * @return
	 */
	public Map<String, Object> getAttributeChanges();

	/**
	 * Get tiles that have been changed in this event.
	 * 
	 * Tiles that haven't been changed are not included.
	 * 
	 * @see Engine.getTile()
	 * @see Engine.getLayerTiles()
	 * @see Engine.getTiles()
	 * @return
	 */
	public Map<Coordinate, Tile> getTileChanges();
}
