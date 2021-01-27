package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.guiguilechat.jcelechat.jcesi.ESITools;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_wars_war_id;
import fr.lelouet.collectionholders.interfaces.ObsObjHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;

public class Wars {

	public final ESIStatic esiConnection;

	public Wars(ESIStatic esiConnection) {
		this.esiConnection = esiConnection;
	}

	ObsListHolder<Integer> cachedIds = null;

	/**
	 *
	 * @return a (cached) observable list of all the war ids
	 */
	public ObsListHolder<Integer> ids() {
		if (cachedIds == null) {
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (cachedIds == null) {
					cachedIds = esiConnection.cache().wars.wars(null).toList(this::expandWholeWars);
				}
			});
		}
		return cachedIds;
	}

	/**
	 * expand the list of wars ids from the first page
	 *
	 * @param firstPage
	 * @return
	 */
	protected List<Integer> expandWholeWars(List<Integer> firstPage) {
		if (firstPage.size() == 0) {
			return firstPage;
		}
		List<Integer> ret = new ArrayList<>(firstPage);
		Integer firstId = ret.get(ret.size() - 1);

		do {
			Requested<Integer[]> req = esiConnection.get_wars(firstId, null);
			if (req.getResponseCode() == 200) {
				Integer[] added = req.getOK();
				if (added != null && added.length > 0) {
					Integer newfirstId = added[added.length - 1];
					ret.addAll(Arrays.asList(added));
					if (newfirstId != firstId) {
						firstId = newfirstId;
					} else {
						firstId = null;
					}
				} else {
					firstId = null;
				}
			} else {
				firstId = null;
			}
		} while (firstId != null);
		return ret;
	}

	ObsListHolder<Integer> cachedMonthIds = null;

	protected ObsListHolder<Integer> getMonthIds() {
		if (cachedMonthIds == null) {
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (cachedMonthIds == null) {
					cachedMonthIds = esiConnection.cache().wars.wars(null).toList(this::expandMonthWars);
				}
			});
		}
		return cachedMonthIds;
	}

	protected List<Integer> expandMonthWars(List<Integer> firstPage) {
		if (firstPage.size() == 0) {
			return firstPage;
		}
		LocalDate dateLimit = LocalDate.now().minusDays(31);
		List<Integer> ret = new ArrayList<>(firstPage);
		Integer firstId = ret.get(ret.size() - 1);
		do {
			// at the same time, fetch the first known war id and the next page.
			ObsObjHolder<R_get_wars_war_id> lastLimit = esiConnection.cache().wars.get(firstId);
			Requested<Integer[]> req = esiConnection.get_wars(firstId, null);
			// then check if the previous data was before the limit date.
			LocalDate previousStart = ESITools.convertLocalDate(lastLimit.get().started);
			if (previousStart.isBefore(dateLimit)) {
				firstId = null;
			} else {
				if (req.getResponseCode() == 200) {
					Integer[] added = req.getOK();
					if (added != null && added.length > 0) {
						Integer newfirstId = added[added.length - 1];
						ret.addAll(Arrays.asList(added));
						if (newfirstId != firstId) {
							firstId = newfirstId;
						} else {
							firstId = null;
						}
					} else {
						firstId = null;
					}
				} else {
					firstId = null;
				}
			}
		} while (firstId != null);
		return ret;
	}

	ObsListHolder<R_get_wars_war_id> cachedMonthWars = null;

	public ObsListHolder<R_get_wars_war_id> getMonthWars() {
		if (cachedMonthWars == null) {
			ObsListHolder<Integer> monthIds = getMonthIds();
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (cachedMonthWars == null) {
					cachedMonthWars = monthIds.mapItems(id -> {
						return esiConnection.cache().wars.get(id);
					}).mapItems(holder -> holder.get());
				}
			});
		}
		return cachedMonthWars;
	}

}
