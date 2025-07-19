package fr.guiguilechat.jcelechat.jcesi.connected.modeled.character;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.jcesi.request.interfaces.Requested;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_journal_13;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_wallet_transactions;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_wallets_division_journal_ref_type;
import fr.lelouet.tools.holders.interfaces.ObjHolder;
import fr.lelouet.tools.holders.interfaces.collections.ListHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Wallet {

	private final ESIAccount con;

	public Wallet(ESIAccount con) {
		this.con = con;
	}

	/** get total isk balance */
	public ObjHolder<Double> get() {
		return con.connection().cache().characters.wallet(con.characterId());
	}

	/**
	 * @return the observable list of transactions for this character
	 */
	@Getter(lazy = true)
	private final ListHolder<R_get_characters_character_id_wallet_transactions> transactionsFirstPage = con.connection()
	    .cache().characters.wallet_transactions(con.characterId(), null);

	@Getter(lazy = true)
	private final ListHolder<R_get_characters_character_id_wallet_transactions> transactionsList = getTransactionsFirstPage()
	    .mapList(this::expandTransactions);

	protected List<R_get_characters_character_id_wallet_transactions> expandTransactions(
	    List<R_get_characters_character_id_wallet_transactions> firstPage) {
		if (firstPage == null || firstPage.isEmpty()) {
			return List.of();
		}
		List<R_get_characters_character_id_wallet_transactions> ret = new ArrayList<>(firstPage);
		List<R_get_characters_character_id_wallet_transactions> lastRetrievedPage = firstPage;
		int pages = 1;
		while (lastRetrievedPage != null && !lastRetrievedPage.isEmpty()) {
			long from_id = lastRetrievedPage.get(lastRetrievedPage.size() - 1).transaction_id - 1;
			Requested<R_get_characters_character_id_wallet_transactions[]> nextPage = con.connection()
			    .get_characters_wallet_transactions(con.characterId(), from_id, null);
			lastRetrievedPage = null;
			if (nextPage.isOk() && nextPage.getOK() != null) {
				lastRetrievedPage = Arrays.asList(nextPage.getOK());
				if (!lastRetrievedPage.isEmpty()) {
					ret.addAll(lastRetrievedPage);
					pages++;
				}
			} else {
				log.error("requesting transactions before {} for char {}, got error [{}:{}]", from_id, con.characterId(),
				    nextPage.getResponseCode(), nextPage.getError());
			}
		}
		log.debug("retrieved {} transactions in {} pages for character [{}:{}]", ret.size(), pages, con.characterId(),
		    con.character.getName());
		return ret;
	}

	public ListHolder<M_get_journal_13> getJournal() {
		return con.connection().cache().characters.wallet_journal(con.characterId());
	}

	private ListHolder<M_get_journal_13> pveJournal = null;

	public ListHolder<M_get_journal_13> getPVEJournal() {
		if (pveJournal == null) {
			ListHolder<M_get_journal_13> journal = getJournal();
			LockWatchDog.BARKER.syncExecute(journal, () -> {
				if (pveJournal == null) {
					pveJournal = journal
					    .filter(
					        entry -> entry.ref_type == get_corporations_corporation_id_wallets_division_journal_ref_type.bounty_prizes
					            || entry.ref_type == get_corporations_corporation_id_wallets_division_journal_ref_type.agent_mission_reward
					            || entry.ref_type == get_corporations_corporation_id_wallets_division_journal_ref_type.agent_mission_time_bonus_reward);
				}
			});
		}
		return pveJournal;
	}

}
