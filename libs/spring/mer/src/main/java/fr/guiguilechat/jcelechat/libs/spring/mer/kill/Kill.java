package fr.guiguilechat.jcelechat.libs.spring.mer.kill;

import java.time.Instant;
import java.util.Objects;

import fr.guiguilechat.jcelechat.libs.mer.files.KillDumpEntry;
import fr.guiguilechat.jcelechat.libs.spring.mer.updater.LoadedMer;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.space.solarsystem.SolarSystem;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity(name = "MerKill")
@Table(name = "mer_kill", indexes = {
    @Index(columnList = "destroyed_ship_id"),
		@Index(columnList = "killerCorporationId"),
		@Index(columnList = "killDate"),
    @Index(columnList = "solar_system_id"),
		@Index(columnList = "victimCorporationId"),
		@Index(columnList = "mer_id")
})
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Kill {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	private LoadedMer mer;

	@ManyToOne
	private Type destroyedShip;

	@ManyToOne
	private SolarSystem solarSystem;

	private double bountyClaimed;
	private double iskDestroyed;
	private double iskLost;
	private Instant killDate;
	private int killerCorporationId;
	private int victimCorporationId;

	public static Kill from(KillDumpEntry kde, LoadedMer mer, Type destroyedShipType, SolarSystem solarSystem) {
		return builder()
				.mer(mer)
				.destroyedShip(destroyedShipType)
				.solarSystem(solarSystem)

				.bountyClaimed(Objects.requireNonNullElse(kde.bountyClaimed(), 0.0))
				.iskDestroyed(Objects.requireNonNullElse(kde.iskDestroyed(), 0.0))
				.iskLost(Objects.requireNonNullElse(kde.iskLost(), 0.0))
				.killDate(kde.getKillDate())
				.killerCorporationId(Objects.requireNonNullElse(kde.killerCorporationID(), 0))
				.victimCorporationId(Objects.requireNonNullElse(kde.victimCorporationID(), 0))
				.build();
	}

}
