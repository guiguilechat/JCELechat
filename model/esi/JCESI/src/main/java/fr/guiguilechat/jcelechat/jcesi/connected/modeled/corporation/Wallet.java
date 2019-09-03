package fr.guiguilechat.jcelechat.jcesi.connected.modeled.corporation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.Corporation;
import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_orders_history;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_wallets_division_journal;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_wallets_division_transactions;
import fr.lelouet.collectionholders.impl.collections.ObsMapHolderImpl;
import fr.lelouet.collectionholders.interfaces.collections.ObsListHolder;
import fr.lelouet.collectionholders.interfaces.collections.ObsMapHolder;
import fr.lelouet.collectionholders.interfaces.numbers.ObsDoubleHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;

public class Wallet {

	private final Corporation corporation;

	public Wallet(Corporation corporation) {
		this.corporation = corporation;
	}

	public int getId() {
		return corporation.getId();
	}

	public ESIAccount getAcc() {
		return corporation.con;
	}

	//
	// total isk value of all divisions
	//

	private ObsDoubleHolder cachedTotal = null;

	/** get the total sum of all the divisions' wallets */
	public ObsDoubleHolder getTotal() {
		if (cachedTotal == null) {
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (cachedTotal == null) {
					cachedTotal = getAcc().raw.cache.corporations.wallets(getId())
							.reduceDouble(wal -> wal.stream().mapToDouble(wa -> wa.balance).sum());
				}
			});
		}
		return cachedTotal;
	}

	private ObsMapHolder<Long, R_get_corporations_corporation_id_wallets_division_transactions> cachedWholeTransactions = null;

	/**
	 * get all divisions' transactions history.<br />
	 * The key is String because a transaction can appear in the corporation and
	 * character wallets, with same id.<br />
	 * This is effectively a merge of the wallets of the several divisions.
	 *
	 */
	public ObsMapHolder<Long, R_get_corporations_corporation_id_wallets_division_transactions> getTransactions() {
		if (cachedWholeTransactions == null) {
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (cachedWholeTransactions == null) {
					@SuppressWarnings("unchecked")
					ObsMapHolderImpl<Long, R_get_corporations_corporation_id_wallets_division_transactions>[] wallets = Stream
					.of(corporation.getDivisions().get().wallet).map(div -> getTransactions(div.division))
					.map(l -> ObsMapHolderImpl.toMap(l, k -> getId() + k.transaction_id)).collect(Collectors.toList())
					.toArray(new ObsMapHolderImpl[] {});
					cachedWholeTransactions = wallets[0].merge(Arrays.copyOfRange(wallets, 1, wallets.length));
				}
			});
		}
		return cachedWholeTransactions;
	}

	private Map<Integer, ObsListHolder<R_get_corporations_corporation_id_wallets_division_transactions>> cachedDivisionTransactions = new HashMap<>();

	/**
	 * get the transactions for a division.
	 * <p>
	 * This uses a walking back fetch method. The transactions call is partial but
	 * does not provide a paginated access so we must fetch back all the previous
	 * orders BEFORE the last of the array we retrieved.for example if on first
	 * page we get the ids 10,9,8 then we need to call after that the same method
	 * but limit to those before id 8 (actual specify from_id=7 )< /p>
	 *
	 * @return a new or cached object that contains all the transactions in the
	 *         history of the corpo of a given division.
	 */
	protected ObsListHolder<R_get_corporations_corporation_id_wallets_division_transactions> getTransactions(
			int division_id) {
		ObsListHolder<R_get_corporations_corporation_id_wallets_division_transactions> ret = cachedDivisionTransactions
				.get(division_id);
		if (ret == null) {
			ret = LockWatchDog.BARKER.syncExecute(cachedDivisionTransactions, () -> {
				ObsListHolder<R_get_corporations_corporation_id_wallets_division_transactions> ret2 = cachedDivisionTransactions
						.get(division_id);
				if (ret2 == null) {
					ret2 = getAcc().raw.cache.corporations.wallets_transactions(getId(), division_id, null)
							.toList(l -> expandWholeTransactions(division_id, l));
				}
				return ret2;
			});
		}
		return ret;
	}

	protected List<R_get_corporations_corporation_id_wallets_division_transactions> expandWholeTransactions(int divid,
			List<R_get_corporations_corporation_id_wallets_division_transactions> firstPage) {
		if (firstPage.size() == 0) {
			return firstPage;
		}
		List<R_get_corporations_corporation_id_wallets_division_transactions> ret = new ArrayList<>(firstPage);
		Long firstId = ret.get(ret.size() - 1).transaction_id - 1;
		do {
			// System.err.println("call corp:" + getId() + " division:" + divid + "
			// transaction up to id " + firstId);
			Requested<R_get_corporations_corporation_id_wallets_division_transactions[]> req = getAcc().raw
					.get_corporations_wallets_transactions(getId(), divid, firstId, null);
			// System.err.println(" response code is " + req.getResponseCode());
			if (req.getResponseCode() == 200) {
				R_get_corporations_corporation_id_wallets_division_transactions[] added = req.getOK();
				if (added != null && added.length > 0) {
					// System.err.println("received " + added.length + " new
					// transactions");
					long newfirstId = added[added.length - 1].transaction_id - 1;
					ret.addAll(Arrays.asList(added));
					if (newfirstId != firstId) {
						firstId = newfirstId;
					} else {
						// System.err.println(" new first id is same as sold, stop
						// walking");
						firstId = null;
					}
				} else {
					// System.err.println("no more transaction");
					firstId = null;
				}
			} else {
				// System.err.println("received response " + req.getResponseCode() + ",
				// stop walking");
				firstId = null;
			}
		} while (firstId != null);
		return ret;
	}

	private ObsMapHolder<Object, R_get_corporations_corporation_id_orders_history> cachedOrdersHistory = null;

	public ObsMapHolder<Object, R_get_corporations_corporation_id_orders_history> getOrdersHistory() {
		if (cachedOrdersHistory == null) {
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (cachedOrdersHistory == null) {
					cachedOrdersHistory = getAcc().raw.cache.corporations.orders_history(getId()).toMap(order -> order.order_id);
				}
			});
		}
		return cachedOrdersHistory;
	}

	public ObsListHolder<R_get_corporations_corporation_id_wallets_division_journal> getJournal(int division) {
		return getAcc().raw.cache.corporations.wallets_journal(getId(), division);
	}

	private ObsMapHolder<Long, R_get_corporations_corporation_id_wallets_division_journal> cachedWholeJournal = null;

	public ObsMapHolder<Long, R_get_corporations_corporation_id_wallets_division_journal> getWholeJournal() {
		if (cachedWholeJournal == null) {
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (cachedWholeJournal == null) {
					@SuppressWarnings("unchecked")
					ObsMapHolder<Long, R_get_corporations_corporation_id_wallets_division_journal>[] journals = Stream
							.of(corporation.getDivisions().get().wallet).map(div -> getJournal(div.division).toMap(je -> je.id))
					.collect(Collectors.toList())
							.toArray(new ObsMapHolder[] {});
					cachedWholeJournal = journals[0].merge(Arrays.copyOfRange(journals, 1, journals.length));
				}
			});
		}
		return cachedWholeJournal;
	}

}
