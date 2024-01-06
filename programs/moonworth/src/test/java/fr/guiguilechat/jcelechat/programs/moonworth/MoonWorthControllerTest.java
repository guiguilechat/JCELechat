package fr.guiguilechat.jcelechat.programs.moonworth;

import java.util.regex.Matcher;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MoonWorthControllerTest {

	@Test
	public void testParserMoonName() {
		Matcher matcher = MoonWorthController.moonDescPattern.matcher("CT7-5V I - Moon 1");
		Assert.assertTrue(matcher.matches());
		Assert.assertEquals(matcher.group(1), "CT7-5V");
		Assert.assertEquals(matcher.group(2), "I");
		Assert.assertEquals(matcher.group(3), "1");
		matcher = MoonWorthController.moonDescPattern.matcher("Faurent V - Moon 1");
		Assert.assertTrue(matcher.matches());
		Assert.assertEquals(matcher.group(1), "Faurent");
		Assert.assertEquals(matcher.group(2), "V");
		Assert.assertEquals(matcher.group(3), "1");
		matcher = MoonWorthController.moonDescPattern.matcher("Faurent IX - Moon 18");
		Assert.assertTrue(matcher.matches());
		Assert.assertEquals(matcher.group(1), "Faurent");
		Assert.assertEquals(matcher.group(2), "IX");
		Assert.assertEquals(matcher.group(3), "18");
	}

}
