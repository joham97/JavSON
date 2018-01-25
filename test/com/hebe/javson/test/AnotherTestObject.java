package com.hebe.javson.test;

import com.hebe.javson.JSON;
import com.hebe.javson.JSONable;

public class AnotherTestObject implements JSONable {

	private String stringAttribute;
	private int intAttribute;
	private double doubleAttribute;
	private boolean booleanAttribute;
	private AnotherAnotherTestObject anotherAnotherTestObject;

	public AnotherTestObject(String stringAttribute, int intAttribute, double doubleAttribute,
			boolean booleanAttribute) {
		this.stringAttribute = stringAttribute;
		this.intAttribute = intAttribute;
		this.doubleAttribute = doubleAttribute;
		this.booleanAttribute = booleanAttribute;
		this.anotherAnotherTestObject = new AnotherAnotherTestObject(stringAttribute, intAttribute, doubleAttribute, booleanAttribute);
	}

	@Override
	public String toJSON(int level) {
		JSON json = new JSON();

		json.put("stringAttribute", stringAttribute);
		json.put("intAttribute", intAttribute);
		json.put("doubleAttribute", doubleAttribute);
		json.put("booleanAttribute", booleanAttribute);
		json.put("anotherAnotherTestObject", anotherAnotherTestObject);
		
		return json.toJSON(level);
	}

}
