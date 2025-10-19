package fr.guiguilechat.jcelechat.libs.spring.sde.items.metagroup;

import java.math.BigDecimal;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmetaGroups;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "SdeItemsMetaGroup")
@Table(name = "sde_items_metagroup", indexes = {
		@Index(columnList = "name")
})
@Getter
@Setter
@NoArgsConstructor
public class MetaGroup extends SdeEntity<Integer> {

	private BigDecimal color_b, color_g, color_r;
	private int iconId;
	private String iconSuffix;
	private String name;

	public void update(EmetaGroups source) {
		receivedSource();
		if (source.color != null) {
			setColor_b(source.color.b);
			setColor_g(source.color.g);
			setColor_r(source.color.r);
		}
		setIconId(source.iconID);
		setIconSuffix(source.iconSuffix);
		setName(source.enName());
	}

}
