package cn.edu.tsinghua.academic.c00740273.magictower.engine;

import java.util.HashMap;

/**
 * Shortcut of an empty Event object.
 */
public final class EmptyEvent extends AbstractEvent {

	public EmptyEvent(Coordinate coord) {
		this.coordinate = coord;
		this.attributeChanges = new HashMap<String, Object>();
		this.tileChanges = new HashMap<Coordinate, Tile>();
		this.extraInformation = new HashMap<String, Object>();
	}
}
