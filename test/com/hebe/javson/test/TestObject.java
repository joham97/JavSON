package com.hebe.javson.test;

import java.util.ArrayList;
import java.util.List;

import com.hebe.javson.JSON;
import com.hebe.javson.JSONable;

public class TestObject implements JSONable {

	private String stringAttribute;
	private int intAttribute;
	private double doubleAttribute;
	private boolean booleanAttribute;
	private AnotherTestObject anotherTestObject;
	private AnotherTestObject[] anotherTestObjectArray;
	private List<AnotherTestObject> anotherTestObjectList;
	
	public TestObject() {
		super();
		this.stringAttribute = "test";
		this.intAttribute = 123;
		this.doubleAttribute = 12.34;
		this.booleanAttribute = true;
		this.anotherTestObject = new AnotherTestObject("testen", 56, 78.9, true);
		this.anotherTestObjectArray = new AnotherTestObject[] {
				anotherTestObject,
				anotherTestObject
		};
		this.anotherTestObjectList = new ArrayList<>();
		this.anotherTestObjectList.add(anotherTestObject);
		this.anotherTestObjectList.add(anotherTestObject);
		this.anotherTestObjectList.add(anotherTestObject);
	}

	@Override
	public String toJSON(int level) {
		JSON json = new JSON();

		json.put("stringAttribute", stringAttribute);
		json.put("intAttribute", intAttribute);
		json.put("doubleAttribute", doubleAttribute);
		json.put("booleanAttribute", booleanAttribute);
		json.put("anotherTestObject", anotherTestObject);
		json.put("anotherTestObjectArray", anotherTestObjectArray);
		json.put("anotherTestObjectList", anotherTestObjectList);
		
		return json.toJSON(level);
	}
	
}
