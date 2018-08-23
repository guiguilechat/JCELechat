package fr.guiguilechat.jcelechat.model.jcesi.modeled;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.PI.ColonyInfo;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_characters_character_id_planets;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_characters_character_id_planets_planet_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.responses.R_get_characters_character_id_planets_planet_id_pins;

public class PlanetTest {

	@Test
	public void testCreation() {
		R_get_characters_character_id_planets_planet_id base = new R_get_characters_character_id_planets_planet_id();
		R_get_characters_character_id_planets info = new R_get_characters_character_id_planets();
		base.pins = new R_get_characters_character_id_planets_planet_id_pins[1];
		info.owner_id = 13;

		ColonyInfo colo = new ColonyInfo(base);
		colo.addInfo(info);

		Assert.assertEquals(colo.pins, base.pins);
		Assert.assertEquals(colo.owner_id, info.owner_id);
	}

}
