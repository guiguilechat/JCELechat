package fr.guiguilechat.jcelechat.jcesi.connected.modeled.character;

import fr.guiguilechat.jcelechat.jcesi.connected.modeled.ESIAccount;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_journal_13;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_wallet_transactions;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_wallets_division_journal_ref_type;
import fr.lelouet.tools.holders.interfaces.ObjHolder;
import fr.lelouet.tools.holders.interfaces.collections.ListHolder;
import fr.lelouet.tools.holders.interfaces.collections.MapHolder;
import fr.lelouet.tools.synchronization.LockWatchDog;

public class Wallet {

	private final ESIAccount con;

	public Wallet(ESIAccount con) {
		this.con = con;
	}

	/** get total isk balance */
	public ObjHolder<Double> get() {
		return con.connection().cache().characters.wallet(con.characterId());
	}

	private MapHolder<String, R_get_characters_character_id_wallet_transactions> transactions;

	/**
	 * get wallet history.<br />
	 * The key is String because a transaction can appear in the corporation and
	 * character wallets, with same id. so we concatenate char id with transaction
	 * id.
	 */
	public MapHolder<String, R_get_characters_character_id_wallet_transactions> getTransactions() {
		if (transactions == null) {
			LockWatchDog.BARKER.syncExecute(this, () -> {
				if (transactions == null) {
					transactions = con.connection().cache().characters.wallet_transactions(con.characterId(), null)
							.toMap(h -> "" + con.characterId() + h.transaction_id);
				}
			});
		}
		return transactions;
	}

	/**
	 *
	 * @return the observable list of transactions for this character
	 */
	public ListHolder<R_get_characters_character_id_wallet_transactions> getTransactionsList() {
		return con.connection().cache().characters.wallet_transactions(con.characterId(), null);
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
