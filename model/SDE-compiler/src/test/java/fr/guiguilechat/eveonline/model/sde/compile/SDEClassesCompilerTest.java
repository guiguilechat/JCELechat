package fr.guiguilechat.eveonline.model.sde.compile;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.eveonline.model.sde.compile.SDEClassesCompiler;

public class SDEClassesCompilerTest {

	@Test
	public void testFormatName() {
		Assert.assertEquals(SDEClassesCompiler.formatName("ta ta ta"), "TaTaTa");
		Assert.assertEquals(SDEClassesCompiler.formatName("Ta ta ta"), "TaTaTa");
		Assert.assertEquals(SDEClassesCompiler.formatName("tatata"), "Tatata");
		Assert.assertEquals(SDEClassesCompiler.formatName("TATATA"), "TATATA");
	}
}
