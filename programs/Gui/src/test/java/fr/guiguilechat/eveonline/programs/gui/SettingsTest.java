package fr.guiguilechat.eveonline.programs.gui;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.eveonline.programs.manager.Settings;
import fr.guiguilechat.eveonline.programs.manager.Settings.TeamDescription;
import fr.guiguilechat.eveonline.programs.manager.settings.ISettings;

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
		TeamDescription g1 = new TeamDescription();
		g1.members.addAll(Arrays.asList("toon1", "alt2"));
		sets1.teams.put("group1", g1);
		sets1.store();
		SettingsTests sets2 = null;
		sets2 = ISettings.load(SettingsTests.class);
		Assert.assertEquals(sets2.apiKeys.get(123), "bbb");
		Assert.assertTrue(sets2.teams.get("group1").members.contains("toon1"));
	}

}
