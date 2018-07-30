package fr.guiguilechat.tools;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableSet;

public class JavaFxToolsTest {

	@Test
	public void test() {
		Map<String, String> map = new HashMap<>();
		map.put("a", "aa");
		SimpleObjectProperty<Map<String, String>> obs = new SimpleObjectProperty<>(map);
		ObservableSet<String> created = JFXTools.makeSet(obs, Map::keySet);
		Assert.assertTrue(created.contains("a"));
		Assert.assertFalse(created.contains("b"));
		map = new HashMap<>();
		map.put("b", "bb");
		obs.set(map);
		Assert.assertFalse(created.contains("a"));
		Assert.assertTrue(created.contains("b"));
	}

}
