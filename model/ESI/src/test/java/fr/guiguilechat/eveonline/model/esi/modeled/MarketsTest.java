package fr.guiguilechat.eveonline.model.esi.modeled;

import java.text.ParseException;

import org.testng.annotations.Test;

import fr.guiguilechat.eveonline.model.esi.ESIConnection;
import is.ccp.tech.esi.responses.R_get_markets_region_id_orders;

public class MarketsTest {

	public static void main(String[] args) {
		ESIConnection con = ESIConnection.DISCONNECTED;
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
		Markets.formatter.parse("Tue, 30 Jan 2018 22:14:44 GMT");
	}

	@Test
	public void testGetPrices() {
	}

}
