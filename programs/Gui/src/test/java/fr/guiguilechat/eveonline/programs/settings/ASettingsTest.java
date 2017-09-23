package fr.guiguilechat.eveonline.programs.settings;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.Yaml;

public class ASettingsTest {

	public static class FalseSettings implements ISettings {
		public String a = "bla";

		@Override
		public boolean useTempDir() {
			return true;
		}
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
		fs2 = ISettings.makeYaml().loadAs(new FileReader(fs1.getFile()), FalseSettings.class);
		Assert.assertEquals(fs2.a, "bbb");
		fs2 = ISettings.load(FalseSettings.class);
		Assert.assertEquals(fs2.a, "bbb");
		fs2.erase();
		fs2 = ISettings.load(FalseSettings.class);
		Assert.assertEquals(fs2.a, "bla");
	}

}
