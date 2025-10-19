package fr.guiguilechat.jcelechat.libs.spring.sde.items.category;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.Ecategories;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.group.Group;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "SdeItemsCategory")
@Table(name = "sde_items_category", indexes = {
    @Index(columnList = "published"),
    @Index(columnList = "name") })
@Getter
@Setter
@NoArgsConstructor
public class Category extends SdeEntity<Integer> {

	@OneToMany(mappedBy = "category")
	private List<Group> groups;
	private int iconId;
	private String name;
	private boolean published;

	public String name() {
		if (name != null) {
			return name;
		}
		return "category:" + getId();
	}

	@Override
	public String toString() {
		return name == null ? "category:" + getId() : name + "(" + getId() + ")";
	}

	public void update(Ecategories source) {
		super.receivedSource();
		setIconId(source.iconID);
		setName(source.enName());
		setPublished(source.published);
	}

}
