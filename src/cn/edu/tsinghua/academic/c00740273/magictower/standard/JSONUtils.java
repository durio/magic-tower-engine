package cn.edu.tsinghua.academic.c00740273.magictower.standard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtils {

	public static Object makeObjectSerializable(Object obj)
			throws JSONException {
		if (obj instanceof JSONArray) {
			JSONArray arrayObj = (JSONArray) obj;
			List<Object> list = new ArrayList<Object>();
			for (int i = 0; i < arrayObj.length(); i++) {
				list.add(makeObjectSerializable(arrayObj.get(i)));
			}
			return list;
		} else if (obj instanceof JSONObject) {
			JSONObject objectObj = (JSONObject) obj;
			Map<String, Object> map = new HashMap<String, Object>();
			@SuppressWarnings("unchecked")
			Iterator<String> keyIterator = objectObj.keys();
			while (keyIterator.hasNext()) {
				String key = keyIterator.next();
				map.put(key, makeObjectSerializable(objectObj.get(key)));
			}
			return map;
		} else {
			return obj;
		}
	}

}
