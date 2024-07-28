package fr.guiguilechat.jcelechat.libs.spring.affiliations.character;

import java.time.Instant;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.guiguilechat.jcelechat.jcesi.ESITools;
import fr.guiguilechat.jcelechat.libs.spring.affiliations.alliance.AllianceInfo;
import fr.guiguilechat.jcelechat.libs.spring.affiliations.corporation.CorporationInfo;
import fr.guiguilechat.jcelechat.libs.spring.affiliations.faction.FactionInfo;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.ARemoteEntity;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiAffiliationsCharacterInformation")
@Table(name = "esi_affiliations_characterinformation", indexes = {
    @Index(columnList = "fetch_active,expires"),
    @Index(columnList = "name"),
    @Index(columnList = "alliance_id"),
    @Index(columnList = "corporation_id"),
    @Index(columnList = "faction_id")
})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CharacterInformation extends ARemoteEntity<Integer, R_get_characters_character_id> {

	@ManyToOne
	private AllianceInfo alliance;

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
	@ManyToOne
	private CorporationInfo corporation;

	/**
	 * description string
	 */
	@Column(columnDefinition = "TEXT")
	private String description;

	/**
	 * ID of the faction the character is fighting for, if the character is enlisted
	 * in Factional Warfare
	 */
	@ManyToOne
	private FactionInfo faction;

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
		setBirthday(ESITools.fieldInstant(data.birthday));
		setBloodlineId(data.bloodline_id);
		setDescription(data.description);
		setGender(data.gender);
		setName(data.name);
		setRaceId(data.race_id);
		setSecurityStatus(data.security_status);
		setTitle(data.title);
	}

}
