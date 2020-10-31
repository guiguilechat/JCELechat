package fr.guiguilechat.jcelechat.libs.logparser.gamelogs;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LogLineTest {

	@Test
	public void testParse() {
		LogLine test = LogLine.of("[ 2020.10.31 15:56:06 ] (hint) Attempting to join a channel");
		Assert.assertNotNull(test);
		Assert.assertEquals(test.type, "hint");
		Assert.assertEquals(test.details, "Attempting to join a channel");
		Assert.assertEquals(test.time.getHour(), 15);
		Assert.assertEquals(test.time.getMinute(), 56);
		Assert.assertEquals(test.time.getSecond(), 06);
	}

}
