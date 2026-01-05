package fr.guiguilechat.jcelechat.libs.spring.sde.items.dynamic.mapping;

import java.util.ArrayList;
import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.dynamic.Mutaplasmid;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "SdeItemsMutaMapping")
@Table(name = "sde_items_mutamapping", indexes = {
		@Index(columnList = "mutaplasmid_id"),
		@Index(columnList = "product_id")
})
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MutaMapping {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	private Mutaplasmid mutaplasmid;

	@ManyToOne(fetch = FetchType.LAZY)
	private Type product;

	@Builder.Default
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Type> applicable = new ArrayList<>();


}
