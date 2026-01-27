package fr.guiguilechat.jcelechat.libs.everef.history;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.testng.Assert;
import org.testng.annotations.Test;

public class HistoryEntryTest {

	@Test
	public void testDateFormat() {

		HistoryEntry test = new HistoryEntry("2020-06-01", 0, 0, null, null, null, 0, 0);
		OffsetDateTime convertedBack = test.dateInstant().atOffset(ZoneOffset.UTC);
		Assert.assertEquals(convertedBack.getDayOfMonth(), 1);
		Assert.assertEquals(convertedBack.getMonthValue(), 6);
		Assert.assertEquals(convertedBack.getYear(), 2020);
	}

}
