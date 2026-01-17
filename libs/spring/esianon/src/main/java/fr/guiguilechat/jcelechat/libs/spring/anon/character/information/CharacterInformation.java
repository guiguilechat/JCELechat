package fr.guiguilechat.jcelechat.libs.spring.anon.character.information;

import java.time.Instant;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.guiguilechat.jcelechat.jcesi.ESIDateTools;
import fr.guiguilechat.jcelechat.libs.spring.anon.alliance.information.AllianceInfo;
import fr.guiguilechat.jcelechat.libs.spring.anon.character.affiliation.CharacterAffiliation;
import fr.guiguilechat.jcelechat.libs.spring.anon.corporation.information.CorporationInfo;
import fr.guiguilechat.jcelechat.libs.spring.anon.faction.information.FactionInfo;
import fr.guiguilechat.jcelechat.libs.spring.update.entities.remote.RemoteEntity;
import fr.guiguilechat.jcelechat.libs.spring.update.resolve.id.IdResolution;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_characters_character_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiCharacterInfo")
@Table(name = "esi_character_info", indexes = {
    @Index(columnList = "fetch_active,fetch_priority,expires"),
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
public class CharacterInformation extends RemoteEntity<Integer, R_get_characters_character_id> {

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private CharacterAffiliation affiliation = null;

	@ManyToOne(fetch = FetchType.LAZY)
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
	@ManyToOne(fetch = FetchType.LAZY)
	private CorporationInfo corporation;

	/**
	 * description string
	 */
	@Lob
	private String description;

	/**
	 * ID of the faction the character is fighting for, if the character is enlisted
	 * in Factional Warfare
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private FactionInfo faction;

	/**
	 * gender string
	 */
	@Enumerated(EnumType.STRING)
	private get_characters_character_id_gender gender;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private IdResolution idResolution = null;

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
		setBirthday(ESIDateTools.fieldInstant(data.birthday));
		setBloodlineId(data.bloodline_id);
		setDescription(data.description);
		setGender(data.gender);
		setName(data.name);
		setRaceId(data.race_id);
		setSecurityStatus(data.security_status);
		setTitle(data.title);
	}

}
