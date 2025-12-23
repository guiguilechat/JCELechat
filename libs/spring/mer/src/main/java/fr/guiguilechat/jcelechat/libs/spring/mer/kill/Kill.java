package fr.guiguilechat.jcelechat.libs.spring.mer.kill;

import java.time.Instant;
import java.util.Objects;

import fr.guiguilechat.jcelechat.libs.mer.files.KillDumpEntry;
import fr.guiguilechat.jcelechat.libs.spring.mer.updater.LoadedMer;
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
		@Index(columnList = "killDate"),
		@Index(columnList = "solarSystemId"),
		@Index(columnList = "victimTypeId"),
		@Index(columnList = "killerCorporationId"),
		@Index(columnList = "killerTypeId"),
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

	private Integer solarSystemId;

	private Instant killDate;

	// victim

	/** type of the ship/structure detroyed */
	private Integer victimTypeId;

	private int victimCorporationId;

	private double bountyClaimed;

	/** value of the hull + items that were destroyed */
	private double iskDestroyed;

	/** total value of the fitting */
	private double iskLost;

	// killer

	/** last hit was made by a user controlling ship/structure */
	private Integer killerTypeId;

	private int killerCorporationId;

	// parsing

	public static Kill from(KillDumpEntry kde,
			LoadedMer mer) {
		// check for null values. If this fails, it means the parser is not up-to-date
		kde.getKillDate().toEpochMilli();
		kde.solarSystemId().toString();
		kde.victimShipTypeId().toString();
		return builder()
				.bountyClaimed(Objects.requireNonNullElse(kde.bountyClaimed(), 0.0))
				.victimTypeId(kde.victimShipTypeId())
				.iskDestroyed(Objects.requireNonNullElse(kde.iskDestroyed(), 0.0))
				.iskLost(Objects.requireNonNullElse(kde.iskLost(), 0.0))
				.killDate(kde.getKillDate())
				.killerCorporationId(Objects.requireNonNullElse(kde.killerCorporationID(), 0))
				.killerTypeId(kde.killerShipTypeId())
				.mer(mer)
				.solarSystemId(kde.solarSystemId())
				.victimCorporationId(Objects.requireNonNullElse(kde.victimCorporationID(), 0))
				.build();
	}

}
