package fr.guiguilechat.jcelechat.libs.spring.conflicts.war;

import java.time.Instant;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.guiguilechat.jcelechat.jcesi.ESITools;
import fr.guiguilechat.jcelechat.libs.spring.affiliations.alliance.AllianceInfo;
import fr.guiguilechat.jcelechat.libs.spring.affiliations.corporation.CorporationInfo;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.ARemoteEntity;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_wars_war_id;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiConflictsWar")
@Table(name = "esi_conflicts_war", indexes = {
    @Index(columnList = "fetch_active,expires"),
    @Index(columnList = "aggressorAllianceId"),
    @Index(columnList = "aggressorCorporationId") })
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WarInformation extends ARemoteEntity<Integer, R_get_wars_war_id> {

	@ManyToOne
	private AllianceInfo aggressorAlliance;

	@ManyToOne
	private CorporationInfo aggressorCorporation;

	@ManyToOne
	private AllianceInfo defenderAlliance;

	@ManyToOne
	private CorporationInfo defenderCorporation;

	private float aggressorIskDestroyed;

	private int aggressorShipsKilled;

	private Instant declared;

	private float defenderIskDestroyed;

	private int defenderShipsKilled;

	private Instant finished;

	private boolean mutual;

	private boolean openForAllies;

	private Instant retracted;

	private Instant started;

	@Override
	public void update(R_get_wars_war_id data) {
		setAggressorIskDestroyed(data.aggressor.isk_destroyed);
		setAggressorShipsKilled(data.aggressor.ships_killed);
		setDeclared(data.declared == null ? null : ESITools.fieldInstant(data.declared));
		setDefenderIskDestroyed(data.defender.isk_destroyed);
		setDefenderShipsKilled(data.defender.ships_killed);
		setFinished(data.finished == null ? null : ESITools.fieldInstant(data.finished));
		setMutual(data.mutual);
		setOpenForAllies(data.open_for_allies);
		setRetracted(data.retracted == null ? null : ESITools.fieldInstant(data.retracted));
		setStarted(data.started == null ? null : ESITools.fieldInstant(data.started));
	}

}
