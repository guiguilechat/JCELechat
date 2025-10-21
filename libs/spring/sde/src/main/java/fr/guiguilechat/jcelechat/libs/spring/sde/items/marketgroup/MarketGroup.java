package fr.guiguilechat.jcelechat.libs.spring.sde.items.marketgroup;

import java.util.List;
import java.util.function.Function;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmarketGroups;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "SdeItemsMarketGroup")
@Table(name = "sde_items_marketgroup", indexes = {
    @Index(columnList = "parent_id"),
    @Index(columnList = "name")
})
@Getter
@Setter
@NoArgsConstructor
public class MarketGroup extends SdeEntity<Integer> {

	@Lob
	private String description;
	private boolean hasTypes;
	private int iconId;
	private String name;
	@ManyToOne
	private MarketGroup parent;
	@OneToMany(mappedBy = "parent")
	private List<MarketGroup> subGroups;
	@OneToMany(mappedBy = "marketGroup")
	private List<Type> types;

	public String name() {
		if (name != null) {
			return name;
		}
		return "marketgroup:" + getId();
	}

	public void update(EmarketGroups source, Function<Integer, MarketGroup> groups) {
		receivedSource();
		setDescription(source.enDescription());
		setHasTypes(source.hasTypes);
		setIconId(source.iconID);
		setName(source.enName());
		if (source.parentGroupID > 0) {
			setParent(groups.apply(source.parentGroupID));
		}
	}

}
