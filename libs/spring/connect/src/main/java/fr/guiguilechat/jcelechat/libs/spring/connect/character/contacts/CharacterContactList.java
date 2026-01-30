package fr.guiguilechat.jcelechat.libs.spring.connect.character.contacts;

import fr.guiguilechat.jcelechat.libs.spring.update.entities.number.remote.list.AFetchedList;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_contacts;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "EsiConnectCharacterContactList")
@Table(name = "esi_connect_charactercontactlist", indexes = {
		@Index(columnList = "fetch_active,fetch_priority,expires")
})
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CharacterContactList
		extends AFetchedList<Integer, R_get_characters_character_id_contacts, CharacterContact> {

}
