package fr.neyrick.karax.model;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class StringFeatureAdapter extends XmlAdapter<String, StringFeature>{

	@Override
	public String marshal(StringFeature feature) throws Exception {
		String result = feature.getValue();
		if (result == null) return "";
		return feature.getValue();
	}

	@Override
	public StringFeature unmarshal(String value) throws Exception {
		return null;
	}

}
