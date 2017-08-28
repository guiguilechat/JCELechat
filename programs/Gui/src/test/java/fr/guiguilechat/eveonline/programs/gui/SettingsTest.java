package fr.guiguilechat.eveonline.programs.gui;

import java.util.Arrays;
import java.util.LinkedHashSet;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SettingsTest {

	public static class SettingsTests extends Settings {
		@Override
		public String getAppName() {
			return "test app";
		}
	}

	@Test
	public void testLoad() {
		SettingsTests sets1 = new SettingsTests();
		sets1.apiKeys.put(123, "bbb");
		sets1.teams.put("group1", new LinkedHashSet<>(Arrays.asList("toon1", "alt2")));
		sets1.store();
		SettingsTests sets2 = null;
		sets2 = Settings.load(SettingsTests.class);
		Assert.assertEquals(sets2.apiKeys.get(123), "bbb");
		Assert.assertTrue(sets2.teams.get("group1").contains("toon1"));
	}

}
