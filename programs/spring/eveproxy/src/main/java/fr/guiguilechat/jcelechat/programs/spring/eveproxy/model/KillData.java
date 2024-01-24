package fr.guiguilechat.jcelechat.programs.spring.eveproxy.model;

import fr.guiguilechat.jcelechat.libs.spring.mer.model.Kill;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.universe.model.SolarSystem;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity(name = "EveProxyKillData")
@Table(name = "eveproxy_killdata", indexes = {
		@Index(columnList = "kill_id"),
		@Index(columnList = "solar_system_solar_system_id"),
		@Index(columnList = "victim_type_type_id")
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

	@ManyToOne
	private SolarSystem solarSystem;

	@ManyToOne
	private Type victimType;

	private boolean victimNPC;

	public void updateValues() {
		setVictimNPC(victimType.getGroup().getCategory().getCategoryId() == 11);
	}

	public static KillData of(Kill kill, SolarSystem solarSystem, Type victimType) {
		return builder()
				.kill(kill)
				.solarSystem(solarSystem)
				.victimType(victimType)
				.build();
	}


}
