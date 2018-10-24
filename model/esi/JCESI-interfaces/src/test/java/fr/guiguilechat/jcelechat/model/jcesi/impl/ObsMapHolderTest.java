package fr.guiguilechat.jcelechat.model.jcesi.impl;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class ObsMapHolderTest {

	@Test
	public void testMap() {
		ObservableMap<String, String> source = FXCollections.observableHashMap();
		ObsMapHolderImpl<String, String> sourceimpl = new ObsMapHolderImpl<>(source);
		source.put("a", "aa");
		source.put("b", "bb");
		ObsMapHolderImpl<String, String> mapped = ObsMapHolderImpl.map(sourceimpl, s -> "+" + s);
		HashMap<String, String> expected = new HashMap<>();
		expected.put("a", "+aa");
		expected.put("b", "+bb");
		Assert.assertEquals(mapped.copy(), expected);
		source.put("c", "cc");
		Assert.assertEquals(mapped.copy().get("c"), "+cc");
	}

}
