package fr.guiguilechat.jcelechat.model.sde.compile;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SDEClassesCompilerTest {

	@Test
	public void testFormatName() {
		Assert.assertEquals(SDECompiler.sanitize("ta ta ta"), "TaTaTa");
		Assert.assertEquals(SDECompiler.sanitize("Ta ta ta"), "TaTaTa");
		Assert.assertEquals(SDECompiler.sanitize("tatata"), "Tatata");
		Assert.assertEquals(SDECompiler.sanitize("TATATA"), "TATATA");
	}
}
