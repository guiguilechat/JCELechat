package fr.guiguilechat.jcelechat.libs.spring.npc.corporation;

import fr.guiguilechat.jcelechat.libs.spring.affiliations.corporation.CorporationInfo;
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

/**
 * when using LP from one corporation instead of another, the rate of transfer.
 * Mostly
 * used for Concord LP rates, so sde/fsd/npcCorporations.yaml L21154
 */
@Entity(name = "SdeNpcLPExchange")
@Table(name = "sde_npc_lpexchange", indexes = {
    @Index(columnList = "owned_id"),
    @Index(columnList = "target_id")
})
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class LPExchange {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	/** the corporation you have LP for */
	@ManyToOne(optional = false)
	private CorporationInfo owned;

	/** the rate (mutliplier) applied for that conversion */
	private double rate;
	
	/** the corporation you use LP for */
	@ManyToOne(optional = false)
	private CorporationInfo target;

}
