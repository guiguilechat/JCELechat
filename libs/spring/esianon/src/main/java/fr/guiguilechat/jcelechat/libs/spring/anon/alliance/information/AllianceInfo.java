package fr.guiguilechat.jcelechat.libs.spring.anon.alliance.information;

import java.time.Instant;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.guiguilechat.jcelechat.jcesi.ESIDateTools;
import fr.guiguilechat.jcelechat.libs.spring.anon.corporation.information.CorporationInfo;
import fr.guiguilechat.jcelechat.libs.spring.anon.faction.information.FactionInfo;
import fr.guiguilechat.jcelechat.libs.spring.update.entities.remote.RemoteEntity;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_alliances_alliance_id;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiAllianceInfo")
@Table(name = "esi_alliance_info", indexes = {
		@Index(columnList = "fetchActive,fetchPriority,expires"),
		@Index(columnList = "creatorCorporation_id"),
		@Index(columnList = "executorCorporation_id"),
		@Index(columnList = "faction_id"),
		@Index(columnList = "name")
})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AllianceInfo extends RemoteEntity<Integer, R_get_alliances_alliance_id> {

	/**
	 * ID of the corporation that created the alliance
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private CorporationInfo creatorCorporation;

	/**
	 * ID of the character that created the alliance
	 */
	private int creatorId;

	/**
	 * date_founded string
	 */
	private Instant founded;

	/**
	 * the executor corporation ID, if this alliance is not closed
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private CorporationInfo executorCorporation;

	/**
	 * Faction ID this alliance is fighting for, if this alliance is enlisted in
	 * factional warfare
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	private FactionInfo faction;

	/**
	 * the full name of the alliance
	 */
	private String name;

	/**
	 * the short name of the alliance
	 */
	private String ticker;

	@Override
	public void update(R_get_alliances_alliance_id data) {
		setCreatorId(data.creator_id);
		setFounded(ESIDateTools.fieldInstant(data.date_founded));
		setName(data.name);
		setTicker(data.ticker);
	}

}
