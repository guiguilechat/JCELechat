package fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet;

import java.time.Instant;

import fr.guiguilechat.jcelechat.jcesi.ESIDateTools;
import fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet.CharacterJournal.CharacterJournalList;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.list.AFetchedList;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.list.AFetchedListElementAutoId;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_journal_13;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_wallets_division_journal_context_id_type;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_wallets_division_journal_ref_type;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "EsiConnectCharacterJournal")
@Table(name = "esi_connect_characterjournal", indexes = {
    @Index(columnList = "fetch_resource_id")
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CharacterJournal extends
		AFetchedListElementAutoId<CharacterJournal, CharacterJournalList> {

	@Entity(name = "EsiConnectCharacterJournalList")
	@Table(name = "esi_connect_characterjournallist", indexes = {
	    @Index(columnList = "fetch_active,expires")
	})
	@NoArgsConstructor
	@Getter
	@Setter
	@ToString
	public static class CharacterJournalList
	    extends AFetchedList<Integer, M_get_journal_13, CharacterJournal> {

	}

	/**
	 * The amount of ISK given or taken from the wallet as a result of the given
	 * transaction. Positive when ISK is deposited into the wallet and negative when
	 * ISK is withdrawn
	 */
	private double amount;

	/**
	 * Wallet balance after transaction occurred
	 */
	private double balance;

	/**
	 * An ID that gives extra context to the particular transaction. Because of
	 * legacy reasons the context is completely different per ref_type and means
	 * different things. It is also possible to not have a context_id
	 */
	private long contextId;

	/**
	 * The type of the given context_id if present
	 */
	@Enumerated(EnumType.STRING)
	private get_corporations_corporation_id_wallets_division_journal_context_id_type contextIdType;

	/**
	 * The reason for the transaction, mirrors what is seen in the client
	 */
	private String description;

	/**
	 * The id of the first party involved in the transaction. This attribute has no
	 * consistency and is different or non existant for particular ref_types. The
	 * description attribute will help make sense of what this attribute means. For
	 * more info about the given ID it can be dropped into the /universe/names/ ESI
	 * route to determine its type and name
	 */
	private int firstPartyId;

	/**
	 * The user stated reason for the transaction. Only applies to some ref_types
	 */
	private String reason;

	/**
	 * "The transaction type for the given. transaction. Different transaction types
	 * will populate different attributes. Note: If you have an existing XML API
	 * application that is using ref_types, you will need to know which string ESI
	 * ref_type maps to which integer. You can look at the following file to see
	 * string->int mappings:
	 * https://github.com/ccpgames/eve-glue/blob/master/eve_glue/wallet_journal_ref.py"
	 */
	@Enumerated(EnumType.STRING)
	private get_corporations_corporation_id_wallets_division_journal_ref_type refType;

	/**
	 * The id of the second party involved in the transaction. This attribute has no
	 * consistency and is different or non existant for particular ref_types. The
	 * description attribute will help make sense of what this attribute means. For
	 * more info about the given ID it can be dropped into the /universe/names/ ESI
	 * route to determine its type and name
	 */
	private int secondPartyId;

	/**
	 * Tax amount received. Only applies to tax related transactions
	 */
	private double tax;

	/**
	 * The corporation ID receiving any tax paid. Only applies to tax related
	 * transactions
	 */
	private int taxReceiverId;

	/**
	 * Date and time of transaction
	 */
	private Instant transactionDate;

	/**
	 * Unique journal reference ID
	 */
	private long transactionId;

	public static CharacterJournal from(M_get_journal_13 source) {
		return new CharacterJournal(
		    source.amount,
		    source.balance,
		    source.context_id,
		    source.context_id_type,
		    source.description,
		    source.first_party_id,
		    source.reason,
		    source.ref_type,
		    source.second_party_id,
		    source.tax,
		    source.tax_receiver_id,
		    ESIDateTools.fieldInstant(source.date),
		    source.id);
	}

}
