package cn.edu.tsinghua.academic.c00740273.magictower.standard;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

public class StoreStandardRenderer implements StandardRenderer,
		Map<String, Object> {

	protected Map<String, Object> store;

	@SuppressWarnings("unchecked")
	@Override
	public void initialize(JSONObject dataValue) throws JSONException,
			DataFormatException {
		this.store = (Map<String, Object>) JSONUtils
				.makeObjectSerializable(dataValue);
	}

	@Override
	public void clear() {
		this.store.clear();
	}

	@Override
	public boolean containsKey(Object arg0) {
		return this.store.containsKey(arg0);
	}

	@Override
	public boolean containsValue(Object arg0) {
		return this.store.containsValue(arg0);
	}

	@Override
	public Set<java.util.Map.Entry<String, Object>> entrySet() {
		return this.store.entrySet();
	}

	@Override
	public Object get(Object arg0) {
		return this.store.get(arg0);
	}

	@Override
	public boolean isEmpty() {
		return this.store.isEmpty();
	}

	@Override
	public Set<String> keySet() {
		return this.store.keySet();
	}

	@Override
	public Object put(String arg0, Object arg1) {
		return this.store.put(arg0, arg1);
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> arg0) {
		this.store.putAll(arg0);
	}

	@Override
	public Object remove(Object arg0) {
		return this.store.remove(arg0);
	}

	@Override
	public int size() {
		return this.store.size();
	}

	@Override
	public Collection<Object> values() {
		return this.store.values();
	}

}
