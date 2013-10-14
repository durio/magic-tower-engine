package cn.edu.tsinghua.academic.c00740273.magictower.standard;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.tsinghua.academic.c00740273.magictower.engine.Coordinate;

public class MessageMixin implements RegularTileMixin {

	private static final long serialVersionUID = 1L;

	@Override
	public void initialize(JSONObject dataMixinValue) throws JSONException,
			DataFormatException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean enter(StandardEvent event, Coordinate coord,
			RegularTile tile, Coordinate sourceCoord, CharacterTile sourceTile,
			StandardGame game) {
		// TODO Auto-generated method stub
		return true;
	}

}
