package fr.guiguilechat.jcelechat.libs.spring.connect.character.standings;

import fr.guiguilechat.jcelechat.libs.spring.update.entities.remote.list.AFetchedList;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.M_get_standings_3;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiConnectCharacterStandingList")
@Table(name = "esi_connect_characterstandinglist", indexes = {
		@Index(columnList = "fetch_active,fetch_priority,expires")
})
@NoArgsConstructor
@Getter
@Setter
public class CharacterStandingList extends AFetchedList<Integer, M_get_standings_3, CharacterStanding> {

}
