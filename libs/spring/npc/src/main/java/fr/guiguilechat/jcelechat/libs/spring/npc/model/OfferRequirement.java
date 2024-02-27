package fr.guiguilechat.jcelechat.libs.spring.npc.model;

import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Type;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity(name = "EsiLPOfferRequirement")
@Table(name = "esi_lp_offer_requirement")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class OfferRequirement {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	private CorporationOffer offer;

	private int quantity;

	private Type type;

}
