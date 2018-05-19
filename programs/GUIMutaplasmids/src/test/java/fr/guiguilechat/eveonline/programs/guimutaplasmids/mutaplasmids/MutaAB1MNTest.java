package fr.guiguilechat.eveonline.programs.guimutaplasmids.mutaplasmids;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.eveonline.model.sde.items.attributes.SpeedFactor;
import fr.guiguilechat.eveonline.programs.guimutaplasmids.MutaplasmidFamily.MutaStr;

public class MutaAB1MNTest {

	@Test
	public void test() {
		Assert.assertEquals(MutaAB1MN.INSTANCE.minMult(SpeedFactor.INSTANCE, MutaStr.DECAYED), 0.97);
	}

}
