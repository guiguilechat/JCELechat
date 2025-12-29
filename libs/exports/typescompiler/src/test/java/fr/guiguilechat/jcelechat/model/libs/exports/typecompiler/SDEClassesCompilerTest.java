package fr.guiguilechat.jcelechat.model.libs.exports.typecompiler;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.jcelechat.libs.exports.typecompiler.compile.SDECompiler;

public class SDEClassesCompilerTest {

	@Test
	public void testFormatName() {
		Assert.assertEquals(SDECompiler.sanitize("ta ta ta"), "TaTaTa");
		Assert.assertEquals(SDECompiler.sanitize("Ta ta ta"), "TaTaTa");
		Assert.assertEquals(SDECompiler.sanitize("tatata"), "Tatata");
		Assert.assertEquals(SDECompiler.sanitize("TATATA"), "TATATA");
	}
}
