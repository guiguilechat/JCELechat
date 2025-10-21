package fr.guiguilechat.jcelechat.libs.spring.sde.items.dogma.unit;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EdogmaUnits;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "SdeItemsDogmaUnit")
@Table(name = "sde_items_dogmaunit")
@Getter
@Setter
@NoArgsConstructor
public class Unit extends SdeEntity<Integer> {

	@Lob
	private String description;
	private String displayName;
	private String name;

	public void update(EdogmaUnits source) {
		receivedSource();
		setDescription(source.enDescription());
		setDisplayName(source.enDisplayName());
		setName(source.name);
	}

}
