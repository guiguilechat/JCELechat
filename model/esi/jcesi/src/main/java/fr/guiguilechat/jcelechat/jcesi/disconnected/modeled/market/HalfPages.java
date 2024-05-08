package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled.market;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import fr.guiguilechat.jcelechat.jcesi.ConnectedImpl;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_region_id_orders;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.order_type;
import fr.lelouet.tools.holders.impl.collections.ListHolderImpl;
import fr.lelouet.tools.holders.interfaces.collections.ListHolder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * filters either buy or sell orders, and updates with buy/sell specific
 * regional fetch between received full data.
 */
@Slf4j
@RequiredArgsConstructor
public class HalfPages {

	/**
	 * non-filtered entries.
	 */
	public final ListHolder<R_get_markets_region_id_orders> fullOrders;
	public final int regionId;
	public final boolean buyOrder;

	@Getter(lazy = true)
	private final ListHolder<R_get_markets_region_id_orders> forced = createForced();

	private Instant lastFullModified = null;
	private Instant previousFullModified = null;

	private Instant lastHalfModified = null;
	private Instant lastHalfExpires = null;

	ScheduledExecutorService executor = ConnectedImpl._exec();

	private ListHolder<R_get_markets_region_id_orders> createForced() {
		ListHolderImpl<R_get_markets_region_id_orders> ret = new ListHolderImpl<>();

		fullOrders.filter(order -> order.is_buy_order == buyOrder).follow(l -> {

			Requested<R_get_markets_region_id_orders[]> fullpage = ESIRawPublic.INSTANCE.get_markets_orders(null, null,
					regionId, null, null);
			if (fullpage.isOk()) {
				Instant fetchedModified = fullpage.getLastModifiedInstant();
				log.debug(
								"received full " + (buyOrder ? "bo" : "so") + " modified=" + fetchedModified + " lastHalf="
										+ lastHalfModified);
				if (lastFullModified == null || !fetchedModified.equals(lastFullModified)) {
					previousFullModified = lastFullModified;
					lastFullModified = fetchedModified;
					if (previousFullModified != null) {
						// it means we have received at least two data
						long fetchHalfEpoch = lastFullModified.getEpochSecond()
								+ (lastFullModified.getEpochSecond() - previousFullModified.getEpochSecond()) / 2;
						// no point fetching before expires
						if (lastHalfExpires != null) {
							fetchHalfEpoch = Math.max(fetchHalfEpoch, lastHalfExpires.getEpochSecond());
						}
						// no point fetching after 80% of time between last modified and expires
						long fetchHalfEpochMax = (fullpage.getExpiresInstant().getEpochSecond() * 4
								+ lastFullModified.getEpochSecond())
								/ 5;
						if (fetchHalfEpoch < fetchHalfEpochMax) {
							long delay_seconds = fetchHalfEpoch - Instant.now().getEpochSecond();
							log.debug(
									" schedule half " + (buyOrder ? "bo" : "so") + " fetch at " + Instant.ofEpochSecond(fetchHalfEpoch)
											+ " delay=" + delay_seconds + "s lastFullModified=" + lastFullModified
											+ " previousFullModified=" + previousFullModified + " lastHalfExpires=" + lastHalfExpires);
							executor.schedule(() -> fetchHalf(ret), delay_seconds,
									TimeUnit.SECONDS);
						} else {
							log.debug(
									" can't schedule half " + (buyOrder ? "bo" : "so") + " reason=too close not full expires");
						}
					}
					if (lastHalfModified == null || fetchedModified.isAfter(lastHalfModified)) {
						log.debug(" new full " + (buyOrder ? "bo" : "so") + " fetch replaces values");
						// only called when newer data and also newer than last half
						ret.set(l);
					} else {
						log.debug(" new full " + (buyOrder ? "bo" : "so") + " fetch kept back values");
					}
				}

			}
		});
		return ret;
	}

	protected void fetchHalf(ListHolderImpl<R_get_markets_region_id_orders> ret) {
		Requested<List<R_get_markets_region_id_orders>> halfPages = ESIRawPublic.INSTANCE
				.requestGetPages((p, h) -> ESIRawPublic.INSTANCE
						.get_markets_orders(buyOrder ? order_type.buy : order_type.sell, p, regionId, null, h), null);
		if (halfPages.isOk()) {
			Instant halfModified = halfPages.getLastModifiedInstant();
			log.debug("received new half " + (buyOrder ? "bo" : "so") + " modified=" + halfModified + " lastFull="
							+ lastFullModified);
			if (lastFullModified == null || halfModified.isAfter(lastFullModified)) {
				log.debug(" new half " + (buyOrder ? "bo" : "so") + " replaces full");
				lastHalfModified = halfModified;
				ret.set(halfPages.getOK());
			} else {
				log.debug(" new half " + (buyOrder ? "bo" : "so") + " older than full");
			}
			lastHalfExpires = halfPages.getExpiresInstant();
		} else {
			log.debug("failed to received half " + (buyOrder ? "bo" : "so"));
			log.warn("received response code " + halfPages.getResponseCode() + " error " + halfPages.getError()
					+ " for half pages of regionId=" + regionId + " buy=" + buyOrder);
		}
	}
}