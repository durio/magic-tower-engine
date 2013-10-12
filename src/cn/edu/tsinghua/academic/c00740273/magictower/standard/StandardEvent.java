package cn.edu.tsinghua.academic.c00740273.magictower.standard;

import java.util.Map;

import cn.edu.tsinghua.academic.c00740273.magictower.engine.AbstractEvent;
import cn.edu.tsinghua.academic.c00740273.magictower.engine.Coordinate;

public class StandardEvent extends AbstractEvent {

	protected Map<String, Object> extraInformation;

	public StandardEvent(Coordinate coord) {
		super(coord);
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

}
