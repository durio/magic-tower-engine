package cn.edu.tsinghua.academic.c00740273.magictower.standard.mixin;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.tsinghua.academic.c00740273.magictower.engine.Coordinate;
import cn.edu.tsinghua.academic.c00740273.magictower.standard.CharacterTile;
import cn.edu.tsinghua.academic.c00740273.magictower.standard.DataFormatException;
import cn.edu.tsinghua.academic.c00740273.magictower.standard.RegularTile;
import cn.edu.tsinghua.academic.c00740273.magictower.standard.RegularTileMixin;
import cn.edu.tsinghua.academic.c00740273.magictower.standard.StandardEvent;
import cn.edu.tsinghua.academic.c00740273.magictower.standard.StandardGame;

public class ExtraInformation implements RegularTileMixin {

	private static final long serialVersionUID = 1L;

	protected String key;
	protected boolean add;
	protected Object value;

	@Override
	public void initialize(JSONObject dataMixinValue) throws JSONException,
			DataFormatException {
		this.key = dataMixinValue.getString("key");
		String action = dataMixinValue.getString("action");
		if (action.equals("add")) {
			this.add = true;
		} else if (action.equals("set")) {
			this.add = false;
		} else {
			throw new DataFormatException("Invalid action: " + action + ".");
		}
		this.value = dataMixinValue.get("value");
	}

	@Override
	public boolean enter(StandardEvent event, Coordinate coord,
			RegularTile tile, Coordinate sourceCoord, CharacterTile sourceTile,
			StandardGame game) {
		if (this.add) {
			event.addExtraInformation(this.key, this.value);
		} else {
			event.setExtraInformation(this.key, this.value);
		}
		return true;
	}

}
