package cn.edu.tsinghua.academic.c00740273.magictower.standard;

import java.util.HashMap;
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
	protected Map<String, Object> renderingData;

	public CharacterTile() {
		super();
	}

	public CharacterTile(RegularTile tileAfterLeave) {
		this.tileAfterLeave = tileAfterLeave;
	}

	@Override
	public Map<String, Object> getRenderingData() {
		if (this.renderingData == null) {
			Map<String, Object> underlying = this.tileAfterLeave
					.getRenderingData();
			this.renderingData = new HashMap<String, Object>(underlying);
			this.renderingData.put("character", true);
		}
		return this.renderingData;
	}

	@Override
	public void initialize(JSONObject dataTileValue) throws JSONException,
			DataFormatException {
		JSONObject dataBaseValue = dataTileValue.getJSONObject("base");
		this.tileAfterLeave = (RegularTile) ClassUtils.makeTile(dataBaseValue);
	}

	@Override
	public StandardEvent enter(Coordinate coord, Coordinate sourceCoord,
			CharacterTile sourceTile, StandardGame game) {
		// This shouldn't happen in real game play.
		return new StandardEvent(sourceCoord);
	}

	public RegularTile leave() {
		return this.tileAfterLeave;
	}

}
