package fr.guiguilechat.jcelechat.libs.spring.connect.character.standings;

import fr.guiguilechat.jcelechat.libs.spring.update.entities.remote.list.AFetchedListElementAutoId;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_standings_3;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_standings_from_type;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiConnectCharacterStanding")
@Table(name = "esi_connect_characterstanding", indexes = {
    @Index(columnList = "fetch_resource_id")
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CharacterStanding extends
		AFetchedListElementAutoId<CharacterStanding, CharacterStandingList> {

	private int fromId;

	@Enumerated(EnumType.STRING)
	private get_characters_character_id_standings_from_type fromType;

	private float standing;

	public static CharacterStanding from(M_get_standings_3 from) {
		return new CharacterStanding(from.from_id, from.from_type, from.standing);
	}


}
