package cn.edu.tsinghua.academic.c00740273.magictower.standard;

import org.json.JSONException;
import org.json.JSONObject;

public class ClassUtils {

	public static StandardTile makeTile(JSONObject dataTileValue)
			throws JSONException, DataFormatException {
		return makeInitializableObject(StandardTile.class, dataTileValue);
	}

	public static RegularTileMixin makeMixin(JSONObject dataMixinValue)
			throws JSONException, DataFormatException {
		return makeInitializableObject(RegularTileMixin.class, dataMixinValue);
	}

	public static FirstEventMixin makeFirstEventMixin(JSONObject dataMixinValue)
			throws JSONException, DataFormatException {
		return makeInitializableObject(FirstEventMixin.class, dataMixinValue);
	}

	public static StandardRenderer makeRenderer(JSONObject dataRendererValue)
			throws JSONException, DataFormatException {
		return makeInitializableObject(StandardRenderer.class,
				dataRendererValue);
	}

	public static <T extends Initializable> T makeInitializableObject(
			Class<T> baseClass, JSONObject dataValue) throws JSONException,
			DataFormatException {
		Class<? extends T> class_;
		try {
			class_ = Class.forName(dataValue.getString("class")).asSubclass(
					baseClass);
		} catch (ClassNotFoundException e) {
			throw new DataFormatException(e);
		} catch (ClassCastException e) {
			throw new DataFormatException(e);
		}
		T object;
		try {
			object = class_.newInstance();
		} catch (InstantiationException e) {
			throw new DataFormatException(e);
		} catch (IllegalAccessException e) {
			throw new DataFormatException(e);
		}
		object.initialize(dataValue);
		return object;
	}
}
