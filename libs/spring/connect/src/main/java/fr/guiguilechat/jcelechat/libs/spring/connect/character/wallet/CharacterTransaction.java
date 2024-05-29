package fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet;

import java.time.Instant;

import fr.guiguilechat.jcelechat.jcesi.ESITools;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet.CharacterTransaction.CharacterTransactionList;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.model.AFetchedList;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.model.AFetchedListElement;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_wallet_transactions;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "EsiConnectCharacterTransaction")
@Table(name = "esi_connect_charactertransaction")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CharacterTransaction extends
    AFetchedListElement<CharacterTransaction, CharacterTransactionList> {

	@Entity(name = "EsiConnectCharacterTransactionList")
	@Table(name = "esi_connect_charactertransactionlist")
	@NoArgsConstructor
	@Getter
	@Setter
	@ToString
	public static class CharacterTransactionList
	    extends AFetchedList<Integer, R_get_characters_character_id_wallet_transactions, CharacterTransaction> {

	}

	/**
	 * is_buy boolean
	 */
	private boolean buy;

	/**
	 * client_id integer
	 */
	private int clientId;

	private boolean clientNpcCorp = false;

	/**
	 * journal_ref_id integer
	 */
	private long journalRefId;

	/**
	 * location_id integer
	 */
	private long locationId;

	/**
	 * is_personal boolean
	 */
	private boolean personal;

	/**
	 * quantity integer
	 */
	private int quantity;

	/**
	 * Date and time of transaction
	 */
	private Instant transactionDate;

	/**
	 * Unique transaction ID
	 */
	private long transactionId;

	/**
	 * type_id integer
	 */
	private int typeId;

	/**
	 * Amount paid per unit
	 */
	private double unitPrice;

	public static CharacterTransaction from(R_get_characters_character_id_wallet_transactions source) {
		return new CharacterTransaction(
		    source.is_buy,
		    source.client_id,
		    false,
		    source.journal_ref_id,
		    source.location_id,
		    source.is_personal,
		    source.quantity,
		    ESITools.fieldInstant(source.date),
		    source.transaction_id,
		    source.type_id,
		    source.unit_price);
	}

}
