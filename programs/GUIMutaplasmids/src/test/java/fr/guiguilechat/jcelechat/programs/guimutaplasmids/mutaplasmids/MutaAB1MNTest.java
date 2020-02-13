package fr.guiguilechat.jcelechat.programs.guimutaplasmids.mutaplasmids;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.jcelechat.model.sde.attributes.SpeedFactor;
import fr.guiguilechat.jcelechat.programs.guimutaplasmids.MutaplasmidFamily.MutaStr;

public class MutaAB1MNTest {

	@Test
	public void test() {
		Assert.assertEquals(Muta1MN.INSTANCE.minMult(SpeedFactor.INSTANCE, MutaStr.DECAYED), 0.97);
	}

}
