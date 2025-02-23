package fr.guiguilechat.jcelechat.jcesi.disconnected.modeled;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.guiguilechat.jcelechat.jcesi.ESIDateTools;
import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_wars_war_id;
import fr.lelouet.tools.holders.interfaces.ObjHolder;
import fr.lelouet.tools.holders.interfaces.collections.ListHolder;
import lombok.Getter;

public class Wars {

	public final ESIRawPublic esiConnection;

	public Wars(ESIRawPublic esiConnection) {
		this.esiConnection = esiConnection;
	}

	@Getter(lazy = true)
	private final ListHolder<Integer> allWarsIds = esiConnection.cache().wars.wars(null).toList(this::expandWholeWars);


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

	@Getter(lazy = true)
	private final ListHolder<R_get_wars_war_id> allWars = getAllWarsIds().mapItems(id -> {
		return esiConnection.cache().wars.get(id);
	}).mapItems(holder -> holder.get());

	@Getter(lazy = true)
	private final ListHolder<Integer> monthWarsIds = esiConnection.cache().wars.wars(null)
	.toList(this::expandMonthWars);

	protected List<Integer> expandMonthWars(List<Integer> firstPage) {
		if (firstPage.size() == 0) {
			return firstPage;
		}
		LocalDate dateLimit = LocalDate.now().minusDays(31);
		List<Integer> ret = new ArrayList<>(firstPage);
		Integer firstId = ret.get(ret.size() - 1);
		do {
			// at the same time, fetch the first known war id and the next page.
			ObjHolder<R_get_wars_war_id> lastLimit = esiConnection.cache().wars.get(firstId);
			Requested<Integer[]> req = esiConnection.get_wars(firstId, null);
			// then check if the previous data was before the limit date.
			LocalDate previousStart = ESIDateTools.fieldLocalDate(lastLimit.get().started);
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

	@Getter(lazy = true)
	private final ListHolder<R_get_wars_war_id> monthWars = getMonthWarsIds().mapItems(id -> {
		return esiConnection.cache().wars.get(id);
	}).mapItems(holder -> holder.get());

}
