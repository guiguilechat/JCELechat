package fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet.transaction;

import fr.guiguilechat.jcelechat.libs.spring.update.entities.number.remote.list.AFetchedList;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_wallet_transactions;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "EsiConnectCharacterTransactionList")
@Table(name = "esi_connect_charactertransactionlist", indexes = {
		@Index(columnList = "fetch_active,fetch_priority,expires")
})
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CharacterTransactionList
    extends AFetchedList<Integer, R_get_characters_character_id_wallet_transactions, CharacterTransaction> {

}