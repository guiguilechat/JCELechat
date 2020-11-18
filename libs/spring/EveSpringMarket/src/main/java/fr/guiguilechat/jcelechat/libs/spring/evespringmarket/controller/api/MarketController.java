package fr.guiguilechat.jcelechat.libs.spring.evespringmarket.controller.api;

import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.ESIAccess;

@RestController
@RequestMapping("/api")
public class MarketController {

	public MarketController() {
		// load the forge
		ESIAccess.INSTANCE.markets.getMarket(10000002);
		// load domain
		ESIAccess.INSTANCE.markets.getMarket(10000043);
	}

	@RequestMapping("/so/{regionid}/{typeid}")
	public double so(@PathVariable int regionid, @PathVariable int typeid, Optional<Integer> quantity) {
		return ESIAccess.INSTANCE.markets.getMarket(regionid).getSO(typeid, quantity.orElse(1)).get();
	}

	@RequestMapping("/so/{regionid}")
	public double[] so(@PathVariable int regionid, int[] typeids, Optional<Integer> quantity) {
		return IntStream.of(typeids)
				.mapToDouble(i -> ESIAccess.INSTANCE.markets.getMarket(regionid).getSO(i, quantity.orElse(1)).get())
				.toArray();
	}

	@RequestMapping("/bo/{regionid}/{typeid}")
	public double bo(@PathVariable int regionid, @PathVariable int typeid, Optional<Integer> quantity) {
		return ESIAccess.INSTANCE.markets.getMarket(regionid).getBO(typeid, quantity.orElse(1)).get();
	}

	@RequestMapping("/bo/{regionid}")
	public double[] bo(@PathVariable int regionid, int[] typeids, Optional<Integer> quantity) {
		return IntStream.of(typeids)
				.mapToDouble(i -> ESIAccess.INSTANCE.markets.getMarket(regionid).getBO(i, quantity.orElse(1)).get())
				.toArray();
	}
	
	

}
