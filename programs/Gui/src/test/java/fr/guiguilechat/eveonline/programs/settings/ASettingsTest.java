package fr.guiguilechat.eveonline.programs.settings;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.eveonline.programs.settings.ASettings;

public class ASettingsTest {

	public static class FalseSettings extends ASettings {
		public String a = "bla";
	}

	@Test
	public void testSave() throws FileNotFoundException {
		FalseSettings fs1 = new FalseSettings();
		FalseSettings fs2 = null;
		Assert.assertEquals(fs1.a, "bla");
		fs1.a = "bbb";
		fs1.store();
		fs2 = new Yaml().loadAs(new FileReader(fs1.getFile()), FalseSettings.class);
		Assert.assertEquals(fs2.a, "bbb");
		fs2 = ASettings.makeYaml().loadAs(new FileReader(fs1.getFile()), FalseSettings.class);
		Assert.assertEquals(fs2.a, "bbb");
		fs2 = ASettings.load(FalseSettings.class);
		Assert.assertEquals(fs2.a, "bbb");
		fs2.erase();
		fs2 = ASettings.load(FalseSettings.class);
		Assert.assertEquals(fs2.a, "bla");
	}

}
