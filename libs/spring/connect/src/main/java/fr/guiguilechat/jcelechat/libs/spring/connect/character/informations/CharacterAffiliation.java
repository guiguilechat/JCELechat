package fr.guiguilechat.jcelechat.libs.spring.connect.character.informations;

import fr.guiguilechat.jcelechat.libs.spring.templates.model.ARemoteFetchedResource;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_post_characters_affiliation;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "EsiConnectCharacterAffiliation")
@Table(name = "esi_connect_characteraffiliation", indexes = {
    @Index(columnList = "allianceId"),
    @Index(columnList = "corporationId"),
    @Index(columnList = "factionId")
})
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class CharacterAffiliation extends ARemoteFetchedResource<Integer, R_post_characters_affiliation> {

	/** The character's alliance ID, if their corporation is in an alliance */
	private int allianceId;

	/** The character's corporation ID */
	private int corporationId;

	/** The character's faction ID, if their corporation is in a faction */
	private int factionId;

	@Override
	public void update(R_post_characters_affiliation data) {
		setAllianceId(data.alliance_id);
		setCorporationId(data.corporation_id);
		setFactionId(data.faction_id);
	}

}
