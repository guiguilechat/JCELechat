package fr.guiguilechat.jcelechat.libs.spring.npc.corporation;

import fr.guiguilechat.jcelechat.libs.spring.affiliations.corporation.CorporationInfo;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EnpcCorporations;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * basically the flat data of {@link EnpcCorporations}
 */
@Entity(name = "SdeNpcCorporation")
@Table(name = "sde_npc_corporation", indexes = {
// @Index(columnList = "activity_id,type_id")
})
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class NPCCorp {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@OneToOne(optional = false)
	private CorporationInfo corporation;

	public int factionId;

	private int solarSystemId;

	private int stationId;

	private double taxRate;

	public static NPCCorp of(CorporationInfo ci, EnpcCorporations entry) {
		return builder()
		    .corporation(ci)
		    .factionId(entry.factionID)
		    .solarSystemId(entry.solarSystemID)
		    .stationId(entry.stationID)
		    .taxRate(entry.taxRate)
		    .build();
	}

}
