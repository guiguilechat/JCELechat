package fr.guiguilechat.jcelechat.libs.spring.connect.corporation;

import java.time.Instant;

import fr.guiguilechat.jcelechat.jcesi.ESITools;
import fr.guiguilechat.jcelechat.libs.spring.templates.model.ARemoteFetchedResource;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiNpcCorporationInfo")
@Table(name = "esi_npc_corporationinfo")
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class CorporationInfo extends ARemoteFetchedResource<Integer, R_get_corporations_corporation_id> {

	public Integer getCorporationId() {
		return getRemoteId();
	}

	/**
	 * ID of the alliance that corporation is a member of, if any
	 */
	private int allianceId;
	/**
	 * ceo_id integer
	 */
	private int ceoId;
	/**
	 * creator_id integer
	 */
	private int creatorId;
	/**
	 * description string
	 */
	@Column(columnDefinition = "TEXT")
	private String description;
	/**
	 * faction_id integer
	 */
	private int factionId;
	/**
	 * date_founded string
	 */
	private Instant founded;
	/**
	 * home_station_id integer
	 */
	private int homeStationId;
	/**
	 * member_count integer
	 */
	private int memberCount;
	/**
	 * the full name of the corporation
	 */
	private String name;
	/**
	 * shares integer
	 */
	private long shares;
	/**
	 * tax_rate number
	 */
	private float taxRate;
	/**
	 * the short name of the corporation
	 */
	private String ticker;
	/**
	 * url string
	 */
	private String url;
	/**
	 * war_eligible boolean
	 */
	private boolean warEligible;

	@Override
	public void update(R_get_corporations_corporation_id data) {
		setAllianceId(data.alliance_id);
		setCeoId(data.ceo_id);
		setCreatorId(data.creator_id);
		setDescription(data.description);
		setFactionId(data.faction_id);
		setFounded(data.date_founded == null ? null : ESITools.fieldInstant(data.date_founded));
		setHomeStationId(data.home_station_id);
		setMemberCount(data.member_count);
		setName(data.name);
		setShares(data.shares);
		setTaxRate(data.tax_rate);
		setTicker(data.ticker);
		setUrl(data.url);
		setWarEligible(data.war_eligible);
	}

}
