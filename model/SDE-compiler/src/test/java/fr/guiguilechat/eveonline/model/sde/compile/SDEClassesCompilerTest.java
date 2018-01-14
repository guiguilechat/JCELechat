package fr.guiguilechat.eveonline.model.sde.compile;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.eveonline.model.sde.compile.SDECompiler;

public class SDEClassesCompilerTest {

	@Test
	public void testFormatName() {
		Assert.assertEquals(SDECompiler.formatName("ta ta ta"), "TaTaTa");
		Assert.assertEquals(SDECompiler.formatName("Ta ta ta"), "TaTaTa");
		Assert.assertEquals(SDECompiler.formatName("tatata"), "Tatata");
		Assert.assertEquals(SDECompiler.formatName("TATATA"), "TATATA");
	}
}
