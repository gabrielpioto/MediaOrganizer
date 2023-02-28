package com.mediazer.osub.model.response;

import java.util.Map;

import com.mediazer.osub.model.OSubInfo;
import com.mediazer.osub.model.base.OData;

public class OSubInfos extends OData<OSubInfo[]>{
	
	public OSubInfos(Map<String, ?> map) {
		super(map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void fromMap(Map<String, ?> map) {
		super.fromMap(map);
		Object[] data = null;
		OSubInfo[] ndata = null;
		if(map.get("data") instanceof Object[]){
			data = (Object[]) map.get("data");
			ndata = new OSubInfo[data.length];
			int i = 0;
			for(Object subMap : data)
				ndata[i++] = new OSubInfo((Map<String,String>)subMap); 
		}
		setData(ndata);
	}	
	

}
