package cn.edu.tsinghua.academic.c00740273.magictower.standard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.tsinghua.academic.c00740273.magictower.engine.AbstractEvent;
import cn.edu.tsinghua.academic.c00740273.magictower.engine.Coordinate;

public class StandardEvent extends AbstractEvent {

	protected Map<String, Object> extraInformation;

	public StandardEvent(Coordinate coord) {
		super(coord);
		this.extraInformation = new HashMap<String, Object>();
	}

	public Map<String, Object> getExtraInformation() {
		return this.extraInformation;
	}

	public Map<String, Object> setExtraInformation(
			Map<String, Object> extraInformation) {
		Map<String, Object> prev = this.extraInformation;
		this.extraInformation = extraInformation;
		return prev;
	}

	public Object setExtraInformation(String key, Object value) {
		Object prev = this.extraInformation.get(key);
		this.extraInformation.put(key, value);
		return prev;
	}

	public Object addExtraInformation(String key, Object value) {
		Object listObj = this.extraInformation.get(key);
		if (listObj instanceof List) {
			@SuppressWarnings("unchecked")
			List<Object> list = (List<Object>) listObj;
			list.add(value);
		} else {
			List<Object> list = new ArrayList<Object>();
			list.add(value);
			this.extraInformation.put(key, list);
		}
		return listObj;
	}

}
