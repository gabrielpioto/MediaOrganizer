package com.mediazer.osub.model.base;

import java.lang.reflect.Array;
import java.util.Map;

import com.mediazer.osub.conversor.Conversors;
import com.mediazer.osub.conversor.DefaultConversor;

public class DataArrayConversor<T extends OData<V[]>, V extends OObject> extends DefaultConversor<T> {

	private Class<V> valueClass;

	public DataArrayConversor(Class<V> valueClass) {
		this.valueClass = valueClass;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T convert(Class<T> klass, Map<String, ?> map) {
		T t = super.convert(klass, map);
		Object ndata = null;
		Object obj;
		if ((obj = map.get("data")) instanceof Object[]) {
			Object[] data = (Object[]) obj;

			ndata = Array.newInstance(valueClass, data.length);
			int i = 0;
			for (Object subMap : data) {
				map = (Map<String, String>) subMap;
				V v = Conversors.<V> convertDefault(valueClass, map);
				Array.set(ndata, i++, v);
			}
		}
		t.setData((V[]) ndata);
		return t;
	}

}
