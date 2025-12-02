package fr.guiguilechat.jcelechat.libs.spring.sde.npc.corporation.trade;

import java.math.BigDecimal;

import org.hibernate.annotations.ColumnDefault;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
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
 * The item and price a corporation seeds sell offer at.
 */
@Entity(name = "SdeNpcCorpTrade")
@Table(name = "sde_npc_corporationtrade", indexes = {
		@Index(columnList = "corporation_id"),
    @Index(columnList = "type_id")
})
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CorporationTrade {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	/** the corporation that seeds the type */
	@ManyToOne(optional = false)
	private NpcCorporation corporation;

	@ManyToOne(optional = false)
	private Type type;

	/** no idea what this is */
	@ColumnDefault("0")
	private BigDecimal value;

}
