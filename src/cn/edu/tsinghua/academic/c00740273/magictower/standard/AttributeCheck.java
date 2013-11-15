package cn.edu.tsinghua.academic.c00740273.magictower.standard;

import java.util.Arrays;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.tsinghua.academic.c00740273.magictower.engine.Event;
import cn.edu.tsinghua.academic.c00740273.magictower.standard.mixin.AttributeSelective;

public class AttributeCheck {

	protected AttributeSelective selective;
	protected String operator;

	public AttributeCheck(JSONObject jsonObject) throws JSONException,
			DataFormatException {
		this.selective = new AttributeSelective();
		this.selective.initialize(jsonObject);
		this.operator = jsonObject.getString("operator");
	}

	/**
	 * @return
	 * @see cn.edu.tsinghua.academic.c00740273.magictower.standard.mixin.AttributeSelective#getAttributeName()
	 */
	public String getAttributeName() {
		return this.selective.getAttributeName();
	}

	public boolean checkEvent(Event event) {
		if (!event.getAttributeChanges().containsKey(this.getAttributeName())) {
			return false;
		}
		return this.checkValue(((Number) event.getAttributeChanges().get(
				this.getAttributeName())).longValue());
	}

	public boolean checkValue(long value) {
		String[] operators = this.selective.matchedOperators(value);
		return Arrays.asList(operators).contains(this.operator);
	}

}
