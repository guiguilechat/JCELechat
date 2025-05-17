package fr.guiguilechat.jcelechat.libs.spring.items.group;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.items.category.Category;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.ARemoteEntity;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_groups_group_id;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiItemsGroup")
@Table(name = "esi_items_group", indexes = {
    @Index(columnList = "fetch_active,expires"),
    @Index(columnList = "category_id"),
    @Index(columnList = "published"),
    @Index(columnList = "name") })
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Group extends ARemoteEntity<Integer, R_get_universe_groups_group_id> {

	@ManyToOne
	private Category category;

	@OneToMany(mappedBy = "group")
	private List<Type> types;

	/**
	 * name string
	 */
	private String name;
	/**
	 * published boolean
	 */
	private boolean published;

	public String name() {
		if (name != null) {
			return name;
		}
		return "group:" + getId();
	}

	@Override
	public String toString() {
		return name == null ? "group:" + getId() : name + "(" + getId() + ")";
	}

	@Override
	public void update(R_get_universe_groups_group_id data) {
		setName(data.name);
		setPublished(data.published);
	}
}
