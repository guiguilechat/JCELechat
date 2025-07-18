package fr.guiguilechat.jcelechat.jcesi;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * header and field dates are from
 * https://esi.evetech.net/latest/markets/10000002/orders/?datasource=tranquility&order_type=all&page=1
 * <p>
 * header is expires <br />
 * field is first returned row's "issued".
 * </p>
 */
public class ESIDateToolsTest {

	@Test
	public void testHeaderInstant() {
		String header = "Sun, 23 Feb 2025 12:38:07 GMT";
		OffsetDateTime headerUTC = ESIDateTools.headerInstant(header).atOffset(ZoneOffset.UTC);
		Assert.assertEquals(headerUTC.getYear(), 2025);
		Assert.assertEquals(headerUTC.getMonthValue(), 2);
		Assert.assertEquals(headerUTC.getDayOfMonth(), 23);
		Assert.assertEquals(headerUTC.getHour(), 12);
		Assert.assertEquals(headerUTC.getMinute(), 38);
		Assert.assertEquals(headerUTC.getSecond(), 07);
		Assert.assertEquals(ESIDateTools.offsetDateTimeHeader(headerUTC), header);
	}

	@Test
	public void testFieldInstant() {
		String field = "2025-01-23T23:55:54Z";
		OffsetDateTime fieldUTC = ESIDateTools.fieldInstant(field).atOffset(ZoneOffset.UTC);
		Assert.assertEquals(fieldUTC.getYear(), 2025);
		Assert.assertEquals(fieldUTC.getMonthValue(), 1);
		Assert.assertEquals(fieldUTC.getDayOfMonth(), 23);
		Assert.assertEquals(fieldUTC.getHour(), 23);
		Assert.assertEquals(fieldUTC.getMinute(), 55);
		Assert.assertEquals(fieldUTC.getSecond(), 54);
		Assert.assertEquals(ESIDateTools.offsetDateTimeField(fieldUTC), field);
	}

	@Test
	public void testFormatCompatibilityDate() {
		Instant i = LocalDateTime.parse("2020-01-02T11:10").toInstant(ZoneOffset.UTC);
		Assert.assertEquals(ESIDateTools.toCompatibilityHeader(i), "2020-01-02");
		i = LocalDateTime.parse("2020-01-02T10:10").toInstant(ZoneOffset.UTC);
		Assert.assertEquals(ESIDateTools.toCompatibilityHeader(i), "2020-01-01");

		i = LocalDateTime.parse("2025-07-11T10:10").toInstant(ZoneOffset.UTC);
		Assert.assertEquals(ESIDateTools.toCompatibilityHeader(i), "2025-07-10");
		i = LocalDateTime.parse("2025-07-11T20:00").toInstant(ZoneOffset.UTC);
		Assert.assertEquals(ESIDateTools.toCompatibilityHeader(i), "2025-07-11");
	}

}
