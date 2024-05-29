package fr.guiguilechat.jcelechat.libs.spring.affiliations.alliance;

import java.time.Instant;

import fr.guiguilechat.jcelechat.jcesi.ESITools;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.model.ARemoteFetchedResource;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_alliances_alliance_id;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiAffiliationsAllianceInfo")
@Table(name = "esi_affiliations_allianceinfo")
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class AllianceInfo extends ARemoteFetchedResource<Integer, R_get_alliances_alliance_id> {

	public Integer getAllianceId() {
		return getRemoteId();
	}

	/**
	 * ID of the corporation that created the alliance
	 */
	private int creatorCorporationId;

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
	private int executorCorporationId;

	/**
	 * Faction ID this alliance is fighting for, if this alliance is enlisted in
	 * factional warfare
	 */
	private int factionId;

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
		setCreatorCorporationId(data.creator_corporation_id);
		setCreatorId(data.creator_id);
		setFounded(ESITools.fieldInstant(data.date_founded));
		setExecutorCorporationId(data.executor_corporation_id);
		setFactionId(data.faction_id);
		setName(data.name);
		setTicker(data.ticker);
	}

}
