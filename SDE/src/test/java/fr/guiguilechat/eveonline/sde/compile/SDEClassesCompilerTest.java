package fr.guiguilechat.eveonline.sde.compile;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SDEClassesCompilerTest {

	@Test
	public void testFormatName() {
		Assert.assertEquals(SDEClassesCompiler.formatName("ta ta ta"), "TaTaTa");
		Assert.assertEquals(SDEClassesCompiler.formatName("Ta ta ta"), "TaTaTa");
		Assert.assertEquals(SDEClassesCompiler.formatName("tatata"), "Tatata");
		Assert.assertEquals(SDEClassesCompiler.formatName("TATATA"), "TATATA");
	}
}
