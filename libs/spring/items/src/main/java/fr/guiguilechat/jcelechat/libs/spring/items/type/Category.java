package fr.guiguilechat.jcelechat.libs.spring.items.type;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.ARemoteEntity;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_categories_category_id;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiItemsCategory")
@Table(name = "esi_items_category", indexes = {
    @Index(columnList = "fetch_active,expires"),
    @Index(columnList = "published"),
    @Index(columnList = "name") })
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Category extends ARemoteEntity<Integer, R_get_universe_categories_category_id> {

	@OneToMany(mappedBy = "category")
	private List<Group> groups;

	/**
	 * name string
	 */
	private String name;

	/**
	 * published boolean
	 */
	private boolean published;

	@Override
	public void update(R_get_universe_categories_category_id data) {
		setName(data.name);
		setPublished(data.published);
	}

}
