package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market;

import java.text.ParseException;

import org.testng.annotations.Test;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;

public class MarketsTest {



	@Test
	public void testDateConversion() throws ParseException {
		ESIAccount.formatter.parse("Tue, 30 Jan 2018 22:14:44 GMT");
	}

}
