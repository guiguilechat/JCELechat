package fr.guiguilechat.jcelechat.libs.spring.anon.lp.offer;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_corporations_corporation_id_starbases_starbase_id_fuels;
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

@Entity(name = "EsiNpcLPRequirement")
@Table(name = "esi_npc_lprequirement", indexes = {
    @Index(columnList = "type_id"),
    @Index(columnList = "offer_id")
})
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Requirement {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	private Offer offer;

	@ManyToOne
	private Type type;

	private int quantity;

	public static Requirement of(get_corporations_corporation_id_starbases_starbase_id_fuels item,
	    Offer offer, Type type) {
		return builder()
				.offer(offer)
		    .type(type)

				.quantity(item.quantity)
				.build();
	}

}
