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
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_journal_13;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_orders_history;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_wallets;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id_wallets_division_transactions;
import fr.lelouet.tools.holders.interfaces.collections.ListHolder;
import fr.lelouet.tools.holders.interfaces.collections.MapHolder;
import fr.lelouet.tools.holders.interfaces.numbers.DoubleHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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

	/** the total sum of all the divisions' balance */
	@Getter(lazy = true)
	private final DoubleHolder total = getAcc().connection().cache().corporations.wallets(getId())
			.mapDouble(wal -> wal.stream().mapToDouble(wa -> wa.balance).sum());

	/**
	 * first division balance
	 */
	@Getter(lazy = true)
	private final DoubleHolder firstDivision = getAcc().connection().cache().corporations.wallets(getId())
	.mapDouble(l -> l.stream().filter(div -> div.division == 1).findFirst()
			.orElseGet(() -> new R_get_corporations_corporation_id_wallets()).balance);


	@Getter(lazy = true)
	private final ListHolder<R_get_corporations_corporation_id_wallets_division_transactions> transactionsList = corporation
	    .getDivisions()
	    .toList(div -> Stream.of(div.wallet)
	        .map(wallet -> getTransactions(wallet.division))
	        .collect(Collectors.toList()))
	    .flatten(obs -> obs);

	/**
	 * get all divisions' transactions history.<br />
	 * The key is String because a transaction can appear in the corporation and
	 * character wallets, with same id.<br />
	 * This is effectively a merge of the wallets of the several divisions.
	 *
	 */
	@Getter(lazy = true)
	private final MapHolder<Long, R_get_corporations_corporation_id_wallets_division_transactions> transactionsByID = getTransactionsList()
	.toMap(k -> getId() + k.transaction_id);

	private Map<Integer, ListHolder<R_get_corporations_corporation_id_wallets_division_transactions>> cachedDivisionTransactions = new HashMap<>();

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
	public ListHolder<R_get_corporations_corporation_id_wallets_division_transactions> getTransactions(
			int division_id) {
		ListHolder<R_get_corporations_corporation_id_wallets_division_transactions> ret = cachedDivisionTransactions
				.get(division_id);
		if (ret == null) {
			ret = LockWatchDog.BARKER.syncExecute(cachedDivisionTransactions, () -> {
				ListHolder<R_get_corporations_corporation_id_wallets_division_transactions> ret2 = cachedDivisionTransactions
						.get(division_id);
				if (ret2 == null) {
					ret2 = getAcc().connection().cache().corporations.wallets_transactions(getId(), division_id, null)
					    .toList(l -> expandTransactions(division_id, l));
					cachedDivisionTransactions.put(division_id, ret2);
				}
				return ret2;
			});
		}
		return ret;
	}

	protected List<R_get_corporations_corporation_id_wallets_division_transactions> expandTransactions(int divid,
			List<R_get_corporations_corporation_id_wallets_division_transactions> firstPage) {
		if (firstPage == null || firstPage.isEmpty()) {
			return List.of();
		}
		List<R_get_corporations_corporation_id_wallets_division_transactions> ret = new ArrayList<>(firstPage);
		Long from_id = ret.get(ret.size() - 1).transaction_id - 1;
		int pages = 1;
		do {
			Requested<R_get_corporations_corporation_id_wallets_division_transactions[]> req = getAcc().connection()
			    .get_corporations_wallets_transactions(getId(), divid, from_id, null);
			from_id = null;
			if (req.isOk()) {
				R_get_corporations_corporation_id_wallets_division_transactions[] added = req.getOK();
				if (added != null && added.length > 0) {
					from_id = added[added.length - 1].transaction_id - 1;
					ret.addAll(Arrays.asList(added));
					pages++;
				}
			} else {
				log.error("requesting transactions before {} for corp {} division {}, got error [{}:{}]", from_id, getId(),
				    divid, req.getResponseCode(), req.getError());
			}
		} while (from_id != null);
		log.debug("retrieved {} transactions in {} pages for corporation [{}:{}]/{}", ret.size(), pages, getId(),
		    corporation.getName(), divid);
		return ret;
	}

	@Getter(lazy = true)
	private final MapHolder<Object, R_get_corporations_corporation_id_orders_history> ordersHistory = getAcc()
	.connection().cache().corporations.orders_history(getId()).toMap(order -> order.order_id);

	public ListHolder<M_get_journal_13> getJournal(int division) {
		return getAcc().connection().cache().corporations.wallets_journal(getId(), division);
	}

	@Getter(lazy = true)
	private final ListHolder<M_get_journal_13> journalList = corporation.getDivisions()
	.toList(div -> Stream.of(div.wallet).map(wallet -> getJournal(wallet.division)).collect(Collectors.toList()))
	.flatten(o -> o);

	@Getter(lazy = true)
	private final MapHolder<Long, M_get_journal_13> journalByID = getJournalList().toMap(je -> je.id);


}
