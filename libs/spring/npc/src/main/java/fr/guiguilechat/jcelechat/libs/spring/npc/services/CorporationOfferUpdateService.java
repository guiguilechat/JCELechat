package fr.guiguilechat.jcelechat.libs.spring.npc.services;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.ConnectedImpl;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.npc.model.CorporationOffer;
import fr.guiguilechat.jcelechat.libs.spring.npc.model.LPStoreCorporation;
import fr.guiguilechat.jcelechat.libs.spring.npc.model.OfferRequirement;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services.TypeService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_loyalty_stores_corporation_id_offers;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CorporationOfferUpdateService {

	@Autowired
	private CacheManager cacheManager;

	@Autowired
	private CorporationOfferService corporationOfferService;

	@Autowired
	private LPStoreCorporationService lpStoreCorporationService;

	@Autowired
	private TypeService typeService;

	@Async
	@Transactional
	public CompletableFuture<Void> updateOffers(LPStoreCorporation lsc) {

		Map<String, String> properties = new HashMap<>();
		if (lsc.getLastEtag() != null) {
			properties.put(ConnectedImpl.IFNONEMATCH, lsc.getLastEtag());
		}
		Requested<R_get_loyalty_stores_corporation_id_offers[]> offers = ESIStatic.INSTANCE
				.get_loyalty_stores_offers(lsc.getCorporationId(), properties);
		if (offers.isOk()) {
			corporationOfferService.clearFor(lsc);
			if (offers.getOK() != null && offers.getOK().length > 0) {
				corporationOfferService.saveAll(Stream.of(offers.getOK())
						.map(offer -> convert(lsc, offer))
						.toList());
			}
			lsc.setLastEtag(offers.getETag());
			lsc.setLastError(null);
			lsc.setLastFetch(Instant.now());
			lsc.setNextFetch(offers.getExpiresInstant());
			lpStoreCorporationService.save(lsc);
			for (String cacheName : LPStoreCorporationService.CORPORATIONS_CACHES) {
				cacheManager.getCache(cacheName).clear();
			}
		} else if (offers.getResponseCode() == 304) {
			// no change
		} else {
			lsc.setLastError(offers.getError());
			lsc.setLastFetch(Instant.now());
			// change the next fetch ?
			lsc.setLastFetch(Instant.now().plusSeconds(3600));
			lpStoreCorporationService.save(lsc);
			log.error("while fetching orders for corporation " + lsc.getCorporationId() + " : " + offers.getResponseCode()
					+ " " + offers.getError());
		}
		return CompletableFuture.completedFuture(null);
	}

	CorporationOffer convert(LPStoreCorporation lsc, R_get_loyalty_stores_corporation_id_offers offer) {
		CorporationOffer ret = CorporationOffer.of(offer, lsc, typeService.byId(offer.type_id).orElse(null));
		ret.setRequirements(Stream.of(offer.required_items)
				.map(req -> OfferRequirement.of(req, ret, typeService.byId(req.type_id).orElse(null))).toList());
		return ret;
	}

}
