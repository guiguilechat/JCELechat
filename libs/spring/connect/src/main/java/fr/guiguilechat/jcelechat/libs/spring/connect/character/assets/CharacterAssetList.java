package fr.guiguilechat.jcelechat.libs.spring.connect.character.assets;

import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.list.AFetchedList;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id_assets;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "EsiConnectCharacterAssettList")
@Table(name = "esi_connect_characterassetlist", indexes = {
		@Index(columnList = "fetch_active,fetch_priority,expires")
})
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CharacterAssetList
		extends AFetchedList<Integer, R_get_characters_character_id_assets, CharacterAsset> {

}
