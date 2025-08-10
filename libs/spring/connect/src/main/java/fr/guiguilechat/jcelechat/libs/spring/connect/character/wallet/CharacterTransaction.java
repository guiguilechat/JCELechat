package fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet;

import java.time.Instant;

import fr.guiguilechat.jcelechat.jcesi.ESIDateTools;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet.CharacterTransaction.CharacterTransactionList;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.list.AFetchedList;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.list.AFetchedListElementAutoId;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_wallet_transactions;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "EsiConnectCharacterTransaction")
@Table(name = "esi_connect_charactertransaction", indexes = {
    @Index(columnList = "fetch_resource_id")
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CharacterTransaction extends
		AFetchedListElementAutoId<CharacterTransaction, CharacterTransactionList> {

	@Entity(name = "EsiConnectCharacterTransactionList")
	@Table(name = "esi_connect_charactertransactionlist", indexes = {
	    @Index(columnList = "fetch_active,expires")
	})
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
		    source.journal_ref_id,
		    source.location_id,
		    source.is_personal,
		    source.quantity,
		    ESIDateTools.fieldInstant(source.date),
		    source.transaction_id,
		    source.type_id,
		    source.unit_price);
	}

}
