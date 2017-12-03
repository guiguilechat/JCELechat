package fr.guiguilechat.eveonline.model.esi;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.eveonline.model.esi.raw.Character;

public class ESICharacterTest {

	@Test
	public void test() {
		Map<Integer, String> names = Character.getNames(3019356, 3008416);
		Assert.assertEquals(names.get(3019356), "Sister Alitura");
		Assert.assertEquals(names.get(3008416), "Antaken Kamola");
	}

}
