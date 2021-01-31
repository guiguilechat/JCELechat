package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled;

import org.testng.Assert;
import org.testng.annotations.Test;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.character.PI.ColonyInfo;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_planets;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_planets_planet_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_characters_character_id_planets_planet_id_pins;

public class PlanetTest {

	@Test
	public void testCreation() {
		R_get_characters_character_id_planets_planet_id base = new R_get_characters_character_id_planets_planet_id();
		R_get_characters_character_id_planets info = new R_get_characters_character_id_planets();
		base.pins = new get_characters_character_id_planets_planet_id_pins[1];
		info.owner_id = 13;

		ColonyInfo colo = new ColonyInfo(info, base);
		colo.addInfo(info);

		Assert.assertEquals(colo.pins, base.pins);
		Assert.assertEquals(colo.owner_id, info.owner_id);
	}

}
