package fr.guiguilechat.jcelechat.model.sde.compile;

import org.testng.annotations.Test;

import fr.guiguilechat.jcelechat.model.sde.load.fsd.Etypes;

public class SdeLoaderTest {

	@Test
	public void test() {

		var t = Etypes.LOADER.load()
				.entrySet()
				.iterator()
				.next()
				.getValue();
		System.err.println(t.enName());
	}

}
