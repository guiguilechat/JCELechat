package fr.guiguilechat.jcelechat.libs.spring.affiliations.character;

import java.time.Instant;

import fr.guiguilechat.jcelechat.jcesi.ESITools;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource.ARemoteFetchedResource;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiAffiliationsCharacterInformation")
@Table(name = "esi_affiliations_characterinformation")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CharacterInformation extends ARemoteFetchedResource<Integer, R_get_characters_character_id> {

	private int allianceId;

	/**
	 * Creation date of the character
	 */
	private Instant birthday;

	/**
	 * bloodline_id integer
	 */
	private int bloodlineId;

	/**
	 * The character's corporation ID
	 */
	private int corporationId;

	/**
	 * description string
	 */
	@Column(columnDefinition = "TEXT")
	private String description;

	/**
	 * ID of the faction the character is fighting for, if the character is enlisted
	 * in Factional Warfare
	 */
	private int factionIdd;

	/**
	 * gender string
	 */
	@Enumerated(EnumType.STRING)
	private get_characters_character_id_gender gender;

	/**
	 * name string
	 */
	private String name;
	/**
	 * race_id integer
	 */
	private int raceId;

	/**
	 * security_status number
	 */
	private float securityStatus;

	/**
	 * The individual title of the character
	 */
	private String title;

	@Override
	public void update(R_get_characters_character_id data) {
		setAllianceId(data.alliance_id);
		setBirthday(ESITools.fieldInstant(data.birthday));
		setBloodlineId(data.bloodline_id);
		setCorporationId(data.corporation_id);
		setDescription(data.description);
		setFactionIdd(data.faction_id);
		setGender(data.gender);
		setName(data.name);
		setRaceId(data.race_id);
		setSecurityStatus(data.security_status);
		setTitle(data.title);
	}

}
