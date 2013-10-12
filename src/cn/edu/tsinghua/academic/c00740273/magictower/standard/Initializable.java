package cn.edu.tsinghua.academic.c00740273.magictower.standard;

import org.json.JSONException;
import org.json.JSONObject;

public interface Initializable {

	public void initialize(JSONObject dataValue) throws JSONException,
			DataFormatException;

}
