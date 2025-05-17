package fr.guiguilechat.jcelechat.libs.spring.items.marketgroup;

import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.ARemoteEntity;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_markets_groups_market_group_id;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiItemsMarketGroup")
@Table(name = "esi_items_marketgroup", indexes = {
    @Index(columnList = "fetch_active,expires"),
    @Index(columnList = "parent_id"),
    @Index(columnList = "name")
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MarketGroup extends ARemoteEntity<Integer, R_get_markets_groups_market_group_id> {
	/**
	 * parent_group_id integer
	 */
	@ManyToOne
	private MarketGroup parent;

	@OneToMany(mappedBy = "parent")
	private List<MarketGroup> subGroups;

	@OneToMany(mappedBy = "marketGroup")
	private List<Type> types;

	/**
	 * description string
	 */
	@Lob
	private String description;

	/**
	 * name string
	 */
	private String name;

	public String name() {
		if (name != null) {
			return name;
		}
		return "marketgroup:" + getId();
	}

	@Override
	public void update(R_get_markets_groups_market_group_id data) {
		setDescription(data.description);
		setName(data.name);
	}

}
