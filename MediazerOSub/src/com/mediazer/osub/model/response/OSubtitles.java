package com.mediazer.osub.model.response;

import java.util.Map;

import com.mediazer.osub.model.OSubtitle;
import com.mediazer.osub.model.base.OData;

public class OSubtitles extends OData<OSubtitle[]> {

	public OSubtitles(Map<String, ?> map) {
		super(map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void fromMap(Map<String, ?> map) {
		super.fromMap(map);
		OSubtitle[] ndata = null;
		Object obj;
		if ((obj = map.get("data")) instanceof Object[]) {
			Object[] data = (Object[]) obj;
			ndata = new OSubtitle[data.length];
			int i = 0;
			for (Object subMap : data)
				ndata[i++] = new OSubtitle((Map<String, String>) subMap);
		}
		setData(ndata);
	}

}
