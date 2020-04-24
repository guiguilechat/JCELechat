package fr.guiguilechat.jcelechat.model.sde.compile;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.jcelechat.model.sde.compile.SDECompiler_old;

public class SDEClassesCompilerTest {

	@Test
	public void testFormatName() {
		Assert.assertEquals(SDECompiler_old.formatName("ta ta ta"), "TaTaTa");
		Assert.assertEquals(SDECompiler_old.formatName("Ta ta ta"), "TaTaTa");
		Assert.assertEquals(SDECompiler_old.formatName("tatata"), "Tatata");
		Assert.assertEquals(SDECompiler_old.formatName("TATATA"), "TATATA");
	}
}
