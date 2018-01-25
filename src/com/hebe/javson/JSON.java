package com.hebe.javson;

import java.util.Set;
import java.util.TreeMap;
import java.util.List;
import java.util.Map.Entry;

public class JSON extends TreeMap<String, Object> implements JSONable {

	private static final long serialVersionUID = 1L;

	public void parse(String s) {

	}
	
	public String toJSON() {
		return toJSON(0);
	}
	
	@Override
	public String toJSON(int level) {
		return stringify(level);
	}

	private String stringify(int level) {
		StringBuilder sb = new StringBuilder();

		sb.append("{\n");

		Set<Entry<String, Object>> entrySet = this.entrySet();
		int i = 0;
		for (Entry<String, Object> entry : entrySet) {
			if (entry.getValue() instanceof Object[]) {
				for (int j = 0; j < level + 1; j++) {
					sb.append("\t");
				}
				sb.append("\"" + entry.getKey() + "\": ");
				sb.append("[\n");
				Object[] objects = (Object[]) entry.getValue();
				for (int j = 0; j < objects.length; j++) {
					sb.append(this.jsonValueLine("", objects[i], level + 1, j < objects.length - 1));
				}
				for (int j = 0; j < level + 1; j++) {
					sb.append("\t");
				}
				sb.append("]\n");
			} else if (entry.getValue() instanceof List) {
				for (int j = 0; j < level + 1; j++) {
					sb.append("\t");
				}
				sb.append("\"" + entry.getKey() + "\": ");
				sb.append("[\n");
				
				@SuppressWarnings("unchecked")
				List<Object> objects = (List<Object>) entry.getValue();
				for (int j = 0; j < objects.size(); j++) {
					sb.append(this.jsonValueLine("", objects.get(i), level + 1, j < objects.size() - 1));
				}
				for (int j = 0; j < level + 1; j++) {
					sb.append("\t");
				}
				sb.append("]\n");
			} else {
				sb.append(jsonValueLine(entry.getKey(), entry.getValue(), level, i < entrySet.size() - 1));
			}

			i++;
		}

		for (int j = 0; j < level; j++) {
			sb.append("\t");
		}
		sb.append("}");

		return sb.toString();
	}

	private String jsonValueLine(String key, Object value, int level, boolean hasNext) {
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < level + 1; j++) {
			sb.append("\t");
		}
		if (value instanceof JSONable) {
			sb.append(this.jsonLine(key, (JSONable) value, level + 1));
		} else if (value instanceof String) {
			sb.append(this.jsonStringLine(key, value.toString()));
		} else {
			sb.append(this.jsonNonStringLine(key, value.toString()));
		}
		if (hasNext) {
			sb.append(",");
		}
		sb.append("\n");
		return sb.toString();
	}
	
	private String jsonStringLine(String key, String value) {		
		return formatKey(key) + "\"" + value + "\"";
	}

	private String jsonNonStringLine(String key, String value) {
		return formatKey(key) + value;
	}

	private String jsonLine(String key, JSONable value, int nextLevel) {
		return formatKey(key) + value.toJSON(nextLevel);
	}

	private String formatKey(String key) {
		if (key.isEmpty()) {
			return key;
		}else {
			return "\"" + key + "\": ";
		}
	}
	
}
