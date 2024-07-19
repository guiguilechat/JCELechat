package fr.guiguilechat.jcelechat.libs.spring.industry.reprocess;

import java.io.Serializable;

import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
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
import lombok.Setter;

@SuppressWarnings("serial")
@Entity(name = "SdeReprocess")
@Table(name = "sde_reprocess", indexes = {
    @Index(columnList = "reprocessed_id"),
    @Index(columnList = "product_id") })
@Getter
@Setter
@Builder
@AllArgsConstructor
public class Reprocess implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@ManyToOne
	private Type reprocessed;

	@ManyToOne
	private Type product;

	private int quantity;

}
