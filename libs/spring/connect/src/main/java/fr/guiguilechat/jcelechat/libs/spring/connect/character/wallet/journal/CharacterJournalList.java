package fr.guiguilechat.jcelechat.libs.spring.connect.character.wallet.journal;

import fr.guiguilechat.jcelechat.libs.spring.update.entities.number.remote.list.AFetchedList;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_journal_13;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "EsiConnectCharacterJournalList")
@Table(name = "esi_connect_characterjournallist", indexes = {
		@Index(columnList = "fetch_active,fetch_priority,expires")
})
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CharacterJournalList
    extends AFetchedList<Integer, M_get_journal_13, CharacterJournal> {

}