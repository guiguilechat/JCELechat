package fr.guiguilechat.jcelechat.libs.spring.sde.items.group;

import java.util.List;
import java.util.function.Function;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Egroups;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.category.Category;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "SdeItemsGroup")
@Table(name = "sde_items_group", indexes = {
    @Index(columnList = "category_id"),
    @Index(columnList = "published"),
    @Index(columnList = "name") })
@Getter
@Setter
@NoArgsConstructor
public class Group extends SdeEntity<Integer> {

	private boolean anchorable;
	private boolean anchored;
	@ManyToOne
	private Category category;
	private boolean fittableNonSingleton;
	private String name;
	private boolean published;
	@OneToMany(mappedBy = "group")
	private List<Type> types;
	private boolean useBasePrice;

	public String name() {
		if (name != null) {
			return name;
		}
		return "group:" + getId();
	}

	@Override
	public String toString() {
		return name == null ? "group:" + getId() : name + " (" + getId() + ")";
	}

	public void update(Egroups source, Function<Integer, Category> categories) {
		receivedSource();
		setAnchorable(source.anchorable);
		setAnchored(source.anchored);
		setCategory(categories.apply(source.categoryID));
		setFittableNonSingleton(source.fittableNonSingleton);
		setName(source.enName());
		setPublished(source.published);
		setUseBasePrice(source.useBasePrice);
	}
}
