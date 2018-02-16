package fr.guiguilechat.snakeyaml;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.Yaml;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;

public class LoadObservable {

	public static class MapStorage {

		private ObservableMap<String, String> map = FXCollections.observableHashMap();

		public void setMap(Map<String, String> newmap) {
			map.clear();
			map.putAll(newmap);
		}

		// /**
		// * fails
		// */
		// public ObservableMap<String, String> getMap() {
		// return map;
		// }

		/**
		 * pass
		 */
		public Map<String, String> getMap() {
			return map;
		}
	}

	@Test
	public void load() {
		MapStorage storage = new MapStorage();
		storage.map.put("nice", "dog");
		String dumped = new Yaml().dump(storage);
		MapStorage loaded = new Yaml().loadAs(dumped, MapStorage.class);
		Assert.assertEquals(loaded.map.get("nice"), "dog", "dumped is " + dumped);
	}


}
