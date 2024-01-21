package fr.guiguilechat.jcelechat.libs.spring.mer.model;

import java.time.Instant;
import java.util.Objects;

import fr.guiguilechat.jcelechat.libs.mer.files.KillDumpEntry;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity(name = "MerKill")
@Table(name = "mer_kill", indexes = {
		@Index(columnList = "destroyedShipTypeId"),
		@Index(columnList = "killerCorporationId"),
		@Index(columnList = "killDate"),
		@Index(columnList = "regionId"),
		@Index(columnList = "solarSystemId"),
		@Index(columnList = "victimCorporationId")
})
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Kill {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	@ManyToOne
	private LoadedMer mer;

	private double bountyClaimed;
	private int destroyedShipTypeId;
	private double iskDestroyed;
	private double iskLost;
	private Instant killDate;
	private int killerCorporationId;
	private int regionId;
	private int solarSystemId;
	private int victimCorporationId;

	public static Kill from(KillDumpEntry kde, LoadedMer mer) {
		return builder()
				.mer(mer)

				.bountyClaimed(Objects.requireNonNullElse(kde.bountyClaimed(), 0.0))
				.destroyedShipTypeId(Objects.requireNonNullElse(kde.destroyedShipTypeID(), 0))
				.iskDestroyed(Objects.requireNonNullElse(kde.iskDestroyed(), 0.0))
				.iskLost(Objects.requireNonNullElse(kde.iskLost(), 0.0))
				.killDate(kde.getKillDate())
				.killerCorporationId(Objects.requireNonNullElse(kde.killerCorporationID(), 0))
				.regionId(Objects.requireNonNullElse(kde.regionID(), 0))
				.solarSystemId(Objects.requireNonNullElse(kde.solarSystemID(), 0))
				.victimCorporationId(Objects.requireNonNullElse(kde.victimCorporationID(), 0))
				.build();
	}

}
