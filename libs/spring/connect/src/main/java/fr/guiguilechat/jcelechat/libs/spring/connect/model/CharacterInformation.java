package fr.guiguilechat.jcelechat.libs.spring.connect.model;

import java.time.Instant;

import fr.guiguilechat.jcelechat.jcesi.ESITools;
import fr.guiguilechat.jcelechat.libs.spring.connect.model.templates.ACharData;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity(name = "EsiConnectCharacterInformation")
@Table(name = "esi_connect_characterinformation")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CharacterInformation extends ACharData<R_get_characters_character_id> {

	private int allianceId;

	private Instant birthday;

	private int corporationId;

	private String description;

	@Override
	public void update(R_get_characters_character_id data) {
		setAllianceId(data.alliance_id);
		setBirthday(ESITools.fieldInstant(data.birthday));
		setCorporationId(data.corporation_id);
		setDescription(data.description);
	}

}