package fr.guiguilechat.jcelechat.libs.spring.sde.items.dynamic;

import java.util.HashSet;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.dynamic.mapping.MutaMapping;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.dynamic.multiplier.MutaMultiplier;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/** just a type id and fetch informations */
@Entity(name = "SdeItemsMutaplasmid")
@Table(name = "sde_items_mutaplasmid", indexes = {
})
@Getter
@Setter
public class Mutaplasmid  extends SdeEntity<Integer> {

//	@OneToOne
//	@JoinColumn(name = "id")
//	@MapsId
//	private Type type;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mutaplasmid")
	private Set<MutaMultiplier> multipliers = new HashSet<>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mutaplasmid")
	private Set<MutaMapping> mappings = new HashSet<>();

}
