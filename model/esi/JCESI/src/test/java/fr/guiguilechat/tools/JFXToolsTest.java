package fr.guiguilechat.tools;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableSet;

public class JFXToolsTest {

	@Test
	public void testMakeSet() {
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

	@Test
	public void testConvertDouble() {
		StringProperty sp = new SimpleStringProperty();
		DoubleProperty dp = JFXTools.convertDouble(sp);
		Assert.assertEquals(dp.get(), 0.0);
		sp.set("10");
		Assert.assertEquals(dp.get(), 10.0);
		dp = JFXTools.convertDouble(sp);
		Assert.assertEquals(dp.get(), 10.0);
		sp.set("-15.45");
		Assert.assertEquals(dp.get(), -15.45);
	}

}
