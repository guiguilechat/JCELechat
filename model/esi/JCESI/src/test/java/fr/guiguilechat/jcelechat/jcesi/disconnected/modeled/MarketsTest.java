package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled;

import java.text.ParseException;

import org.testng.annotations.Test;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIAccess;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;

public class MarketsTest {

	public static void main(String[] args) {
		ESIAccess con = ESIAccess.INSTANCE;
		System.out.println("buy");
		for (R_get_markets_region_id_orders o : con.markets.getOrders(true, 10000002, 34)) {
			System.out.println(" " + o.price);
		}
		System.out.println("sell");
		for (R_get_markets_region_id_orders o : con.markets.getOrders(false, 10000002, 34)) {
			System.out.println(" " + o.price);
		}
	}

	@Test
	public void testDateConversion() throws ParseException {
		ESIAccount.formatter.parse("Tue, 30 Jan 2018 22:14:44 GMT");
	}

}
