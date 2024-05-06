package fr.guiguilechat.jcelechat.libs.spring.connect.character.affiliation;

import fr.guiguilechat.jcelechat.libs.spring.templates.model.ACharData;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_post_characters_affiliation;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity(name = "EsiConnectCharacterAffiliation")
@Table(name = "esi_connect_characteraffiliation")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class CharacterAffiliation extends ACharData<R_post_characters_affiliation> {

	/** The character's alliance ID, if their corporation is in an alliance */
	private int alliance_id;

	/** The character's corporation ID */
	private int corporation_id;

	/** The character's faction ID, if their corporation is in a faction */
	private int faction_id;

	@Override
	public void update(R_post_characters_affiliation data) {
		setAlliance_id(data.alliance_id);
		setCorporation_id(data.corporation_id);
		setFaction_id(data.faction_id);
	}

}
