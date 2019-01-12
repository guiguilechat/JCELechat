package fr.guiguilechat.jcelechat.model.jcesi.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.jcelechat.jcesi.impl.ObsMapHolderImpl;
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

	@Test
	public void testMapreceived() {
		int[] count = new int[] { 0 };
		ObservableMap<String, String> source = FXCollections.observableHashMap();
		ObsMapHolderImpl<String, String> sourceimpl = new ObsMapHolderImpl<>(source);
		Consumer<Map<String, String>> run = (m) -> count[0] += m.size();
		sourceimpl.addReceivedListener(run);
		source.put("a", "b");
		sourceimpl.dataReceived();
		Assert.assertEquals(count[0], 1);
		sourceimpl.dataReceived();
		Assert.assertEquals(count[0], 2);
		source.put("a2", "c");
		sourceimpl.dataReceived();
		Assert.assertEquals(count[0], 4);

	}

}
