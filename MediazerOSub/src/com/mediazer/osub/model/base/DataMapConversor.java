package com.mediazer.osub.model.base;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.mediazer.osub.conversor.Conversors;
import com.mediazer.osub.conversor.DefaultConversor;

public class DataMapConversor<T extends OData<Map<String, V>>, V extends OObject> extends DefaultConversor<T> {

	private Class<V> valueClass;

	public DataMapConversor(Class<V> valueClass) {
		this.valueClass = valueClass;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T convert(Class<T> klass, Map<String, ?> map) {
		T t = super.convert(klass, map);
		Object obj;
		Map<String, V> ndata = null;
		if ((obj = map.get("data")) instanceof Map) {
			Map<String, ?> data = (Map<String, ?>) obj;
			ndata = new HashMap<>();
			for (Entry<String, ?> entry : data.entrySet()) {
				V v = null;
				if ((obj = entry.getValue()) instanceof Map) {
					Map<String, String> movieMap = (Map<String, String>) obj;
					v = Conversors.<V> convertDefault(valueClass, movieMap);
				}
				ndata.put(entry.getKey(), v);
			}
		} else {
			System.out.println("nada encontrado");
			// TODO: Log proper
		}
		t.setData(ndata);
		return t;
	}

}
