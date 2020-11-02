package fr.guiguilechat.jcelechat.libs.logparser.gamelogs.types;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CombatLineTest {

	@Test
	public void testParseHITYOUCHARGE() {
		String line = "<color=0xffcc0000><b>42</b> <color=0x77ffffff><font size=10>from</font> <b><color=0xffffffff>Gistum Phalanx</b><font size=10><color=0x77ffffff> - Nova Heavy Missile - Hits";
		Assert.assertTrue(CombatLine.HITYOUCHARGE_PAT.matcher(line).matches());
	}

}
