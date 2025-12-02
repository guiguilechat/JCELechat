package fr.guiguilechat.jcelechat.libs.spring.sde.npc.corporation.lpexchange;

import java.math.BigDecimal;

import fr.guiguilechat.jcelechat.libs.spring.sde.npc.corporation.NpcCorporation;
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
 * when using LP between corporations, the rate of transfer.
 * Mostly used for Concord LP rates, so SDE npcCorporations.yaml L20653
 *
 * @see
 */
@Entity(name = "SdeNpcLPExchange")
@Table(name = "sde_npc_lpexchange", indexes = {
		@Index(columnList = "source_corporation_id"),
		@Index(columnList = "target_corporation_id")
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
	@ManyToOne
	private NpcCorporation sourceCorporation;

	/** the rate (mutliplier) applied for that conversion */
	private BigDecimal rate;

	/** the corporation you use LP for, generally concord */
	@ManyToOne
	private NpcCorporation targetCorporation;

}
