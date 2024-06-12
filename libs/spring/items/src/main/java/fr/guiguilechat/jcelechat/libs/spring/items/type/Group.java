package fr.guiguilechat.jcelechat.libs.spring.items.type;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource.ARemoteFetchedResource;
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
public class Group extends ARemoteFetchedResource<Integer, R_get_universe_groups_group_id> {

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

	@Override
	public void update(R_get_universe_groups_group_id data) {
		setName(data.name);
		setPublished(data.published);
	}
}
