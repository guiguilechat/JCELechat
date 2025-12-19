package fr.guiguilechat.jcelechat.libs.spring.affiliations.corporation;

import java.time.Instant;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.guiguilechat.jcelechat.jcesi.ESIDateTools;
import fr.guiguilechat.jcelechat.libs.spring.affiliations.alliance.AllianceInfo;
import fr.guiguilechat.jcelechat.libs.spring.affiliations.faction.FactionInfo;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.RemoteEntity;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_corporations_corporation_id;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiAffiliationsCorporationInfo")
@Table(name = "esi_affiliations_corporationinfo", indexes = {
		@Index(columnList = "fetch_active,fetch_priority,expires"),
		@Index(columnList = "alliance_id"),
		@Index(columnList = "faction_id"),
		@Index(columnList = "name")
})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CorporationInfo extends RemoteEntity<Integer, R_get_corporations_corporation_id> {

	public Integer getCorporationId() {
		return getId();
	}

	/**
	 * ID of the alliance that corporation is a member of, if any
	 */
	@ManyToOne
	private AllianceInfo alliance;
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
	@Lob
	private String description;
	/**
	 * faction_id integer
	 */
	@ManyToOne
	private FactionInfo faction;
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

	/** npc flag, set to true when the id is retrieved from npc list */
	private boolean npc = false;
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
		setCeoId(data.ceo_id);
		setCreatorId(data.creator_id);
		setDescription(data.description);
		setFounded(data.date_founded == null ? null : ESIDateTools.fieldInstant(data.date_founded));
		setHomeStationId(data.home_station_id);
		setMemberCount(data.member_count);
		setName(data.name);
		setShares(data.shares);
		setTaxRate(data.tax_rate);
		setTicker(data.ticker);
		setUrl(data.url);
		setWarEligible(data.war_eligible);
	}

	public String nameOrId() {
		return name != null ? name : "unknown_" + getId();
	}

}
