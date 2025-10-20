package fr.guiguilechat.jcelechat.libs.spring.sde.items.dogma.attribute.category;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EdogmaAttributeCategories;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "SdeItemsDogmaAttributeCategory")
@Table(name = "sde_items_dogmaattributecategory", indexes = {
		@Index(columnList = "name") })
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
@NoArgsConstructor
public class AttributeCategory extends SdeEntity<Integer> {

	@Lob
	private String description;
	private String name;

	public void update(EdogmaAttributeCategories source) {
		receivedSource();
		description = source.description;
		name = source.name;
	}

}
