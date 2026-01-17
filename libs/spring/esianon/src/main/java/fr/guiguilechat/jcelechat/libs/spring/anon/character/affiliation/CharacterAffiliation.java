package fr.guiguilechat.jcelechat.libs.spring.anon.character.affiliation;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.guiguilechat.jcelechat.libs.spring.anon.alliance.information.AllianceInfo;
import fr.guiguilechat.jcelechat.libs.spring.anon.corporation.information.CorporationInfo;
import fr.guiguilechat.jcelechat.libs.spring.anon.faction.information.FactionInfo;
import fr.guiguilechat.jcelechat.libs.spring.update.entities.remote.RemoteEntity;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_post_characters_affiliation;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiCharacterAffiliation")
@Table(name = "esi_character_affiliation", indexes = {
		@Index(columnList = "fetch_active,fetch_priority,expires"),
    @Index(columnList = "alliance_id"),
    @Index(columnList = "corporation_id"),
    @Index(columnList = "faction_id")
})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CharacterAffiliation extends RemoteEntity<Integer, R_post_characters_affiliation> {

	/** The character's alliance ID, if their corporation is in an alliance */
	@ManyToOne(fetch = FetchType.LAZY)
	private AllianceInfo alliance;

	/** The character's corporation ID */
	@ManyToOne(fetch = FetchType.LAZY)
	private CorporationInfo corporation;

	/** The character's faction ID, if their corporation is in a faction */
	@ManyToOne(fetch = FetchType.LAZY)
	private FactionInfo faction;

	@Override
	public void update(R_post_characters_affiliation data) {

	}

}
