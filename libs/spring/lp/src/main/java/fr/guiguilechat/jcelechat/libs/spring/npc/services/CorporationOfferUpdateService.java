package fr.guiguilechat.jcelechat.libs.spring.npc.services;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.ConnectedImpl;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.npc.model.CorporationOffer;
import fr.guiguilechat.jcelechat.libs.spring.npc.model.LPStoreCorporation;
import fr.guiguilechat.jcelechat.libs.spring.npc.model.OfferRequirement;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.services.TypeService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_loyalty_stores_corporation_id_offers;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CorporationOfferUpdateService {

	final private CacheManager cacheManager;

	final private CorporationOfferService corporationOfferService;

	final private LPStoreCorporationService lpStoreCorporationService;

	final private OfferRequirementService offerRequirementService;

	final private TypeService typeService;

	@Async
	@Transactional
	public CompletableFuture<Void> updateOffers(LPStoreCorporation lsc) {

		Map<String, String> properties = new HashMap<>();
		if (lsc.getLastEtag() != null) {
			properties.put(ConnectedImpl.IFNONEMATCH, lsc.getLastEtag());
		}
		Requested<R_get_loyalty_stores_corporation_id_offers[]> offers = ESIRawPublic.INSTANCE
				.get_loyalty_stores_offers(lsc.getCorporationId(), properties);
		if (offers.isOk()) {
			log.debug("  fetched " + offers.getOK().length + " lp offers for corporation " + lsc.getCorporationId());
			offerRequirementService.clearForCorporation(lsc);
			corporationOfferService.clearFor(lsc);
			if (offers.getOK() != null && offers.getOK().length > 0) {
				List<CorporationOffer> created = corporationOfferService.saveAll(Stream.of(offers.getOK())
						.map(offer -> convert(lsc, offer))
						.toList());

				log.debug("  created " + created.size() + " lp offers for corporation " + lsc.getCorporationId());
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
