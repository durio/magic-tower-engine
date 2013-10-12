package cn.edu.tsinghua.academic.c00740273.magictower.standard;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.tsinghua.academic.c00740273.magictower.engine.Coordinate;

public class MessageMixin implements RegularTileMixin {

	@Override
	public void initialize(JSONObject dataMixinValue) throws JSONException,
			DataFormatException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean enter(StandardEvent event, Coordinate coord,
			RegularTile tile, Coordinate sourceCoord, CharacterTile sourceTile,
			Map<String, Object> args, StandardGame game) {
		// TODO Auto-generated method stub
		return true;
	}

}
