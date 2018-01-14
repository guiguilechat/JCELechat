package fr.guiguilechat.eveonline.programs.settings;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.eveonline.programs.manager.settings.ISettings;

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
		FileReader fileReader = new FileReader(fs1.getFile());
		fs2 = new Yaml().loadAs(fileReader, FalseSettings.class);
		try {
			fileReader.close();
		} catch (IOException e) {
			// ignore
		}
		Assert.assertEquals(fs2.a, "bbb");
		fs2 = ISettings.load(FalseSettings.class);
		Assert.assertEquals(fs2.a, "bbb");
		fs2.erase();
		fs2 = ISettings.load(FalseSettings.class);
		Assert.assertEquals(fs2.a, "bla");
	}

}
