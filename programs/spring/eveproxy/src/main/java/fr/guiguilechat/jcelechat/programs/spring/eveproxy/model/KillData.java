package fr.guiguilechat.jcelechat.programs.spring.eveproxy.model;

import fr.guiguilechat.jcelechat.libs.spring.mer.model.Kill;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.SolarSystem;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.SolarSystem.SysSpace;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity(name = "EveProxyKillData")
@Table(name = "eveproxy_killdata", indexes = {
		@Index(columnList = "kill_id"),
		@Index(columnList = "constellationId"),
		@Index(columnList = "regionId"),
		@Index(columnList = "solarSystemId"),
		@Index(columnList = "spaceType"),
		@Index(columnList = "victimCategoryId"),
		@Index(columnList = "victimGroupId"),
		@Index(columnList = "victimTypeId")
})
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class KillData {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@OneToOne
	private Kill kill;

	private int constellationId;
	private int regionId;
	private double security;
	private int solarSystemId;
	@Enumerated(EnumType.STRING)
	private SysSpace spaceType;
	private int victimCategoryId;
	private int victimGroupId;
	private int victimTypeId;

	private boolean victimNPC;

	public void updateValues() {
		setVictimNPC(getVictimCategoryId() == 11);
	}

	public static KillData of(Kill kill, SolarSystem solarSystem, Type victimType) {
		return builder()
				.kill(kill)

				.constellationId(solarSystem.getConstellation().getConstellationId())
				.regionId(solarSystem.getConstellation().getRegion().getRegionId())
				.security(solarSystem.getSecurity())
				.solarSystemId(solarSystem.getSolarSystemId())
				.spaceType(solarSystem.getSpace())
				.victimCategoryId(victimType.getGroup().getCategory().getCategoryId())
				.victimGroupId(victimType.getGroup().getGroupId())
				.victimTypeId(victimType.getTypeId())
				.build();
	}


}
