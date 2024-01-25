package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;

import org.testng.Assert;

public class MerRestControllerTest {

// @Test
	public void testformatDate() {
		Instant instant = Instant.ofEpochMilli(1704063600000l);//
		OffsetDateTime offsetted = instant.atOffset(ZoneOffset.UTC);
		Assert.assertEquals(offsetted.get(ChronoField.MONTH_OF_YEAR), 12);
		Assert.assertEquals(offsetted.get(ChronoField.YEAR), 2023);
		String formated = DateTimeFormatter.ofPattern("YYYY-MM").format(offsetted);
		Assert.assertEquals(formated, ""
				+ offsetted.get(ChronoField.YEAR)
				+ "-"
				+ offsetted.get(ChronoField.MONTH_OF_YEAR),
				"offset=" + offsetted);
	}

}
