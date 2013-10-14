package cn.edu.tsinghua.academic.c00740273.magictower.standard;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.tsinghua.academic.c00740273.magictower.engine.Coordinate;

/**
 * A tile that the character is not staying.
 */
public class RegularTile implements StandardTile {

	private static final long serialVersionUID = 1L;

	protected RegularTileMixin mixin;
	protected Map<String, Object> renderingData;

	@Override
	public void initialize(JSONObject dataTileValue) throws JSONException,
			DataFormatException {
		JSONObject dataMixinValue = dataTileValue.optJSONObject("mixin");
		if (dataMixinValue != null) {
			this.mixin = ClassUtils.makeMixin(dataMixinValue);
		}
		JSONObject dataRendering = dataTileValue.optJSONObject("rendering");
		this.renderingData = new HashMap<String, Object>();
		if (dataRendering != null) {
			@SuppressWarnings("unchecked")
			Iterator<String> renderingKeyIterator = dataRendering.keys();
			while (renderingKeyIterator.hasNext()) {
				String renderingName = renderingKeyIterator.next();
				Object renderingValue = dataRendering.get(renderingName);
				this.renderingData.put(renderingName, renderingValue);
			}
		}
		this.renderingData.put("character", false);
	}

	@Override
	public Map<String, Object> getRenderingData() {
		return this.renderingData;
	}

	@Override
	public StandardEvent enter(Coordinate coord, Coordinate sourceCoord,
			CharacterTile sourceTile, StandardGame game) {
		StandardEvent event = new StandardEvent(sourceCoord);
		if (this.mixin != null) {
			this.mixin.enter(event, coord, this, sourceCoord, sourceTile, game);
		}
		return event;
	}
}
