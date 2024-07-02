package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled;

import java.util.stream.IntStream;

public class MarketGetEmpire {

	private static final int[] TYPE_IDS = { 37, 25612, 25613, 25617, 25619 };

	public static void main(String[] args) {
		IntStream.of(TYPE_IDS).parallel().forEach(typeId -> System.out.println(
		    "price of type " + typeId + " is " + ESIAccess.INSTANCE.markets.getEmpireAvgPrice().get(typeId).get()));
	}

}
