package cn.edu.tsinghua.academic.c00740273.magictower.standard;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.tsinghua.academic.c00740273.magictower.engine.Coordinate;

/**
 * A tile that the character is staying.
 */
public class CharacterTile implements StandardTile {

	private static final long serialVersionUID = 1L;

	protected RegularTile tileAfterLeave;

	public CharacterTile() {
		super();
	}

	public CharacterTile(RegularTile tileAfterLeave) {
		this.tileAfterLeave = tileAfterLeave;
	}

	@Override
	public void initialize(JSONObject dataTileValue) throws JSONException,
			DataFormatException {
		this.tileAfterLeave = new RegularTile();
		this.tileAfterLeave.initialize(dataTileValue);
	}

	@Override
	public StandardEvent enter(Coordinate coord, Coordinate sourceCoord,
			CharacterTile sourceTile, Map<String, Object> args,
			StandardGame game) {
		// This shouldn't happen in real game play.
		return new StandardEvent(sourceCoord);
	}

	public RegularTile leave() {
		return this.tileAfterLeave;
	}

}
