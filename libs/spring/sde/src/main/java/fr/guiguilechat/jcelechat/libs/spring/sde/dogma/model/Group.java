package fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model;

import fr.guiguilechat.jcelechat.model.sde.load.fsd.EgroupIDs;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity(name = "SdeDogmaGroup")
@Table(name = "sde_dogma_group", indexes = {
		@Index(columnList = "name"),
		@Index(columnList = "category_category_id") })
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Group {

	@Id
	private int groupId;

	private boolean anchorable;
	private boolean anchored;
	@ManyToOne
	private Category category;
	private boolean fittableNonSingleton;
	private int iconID;
	private String name;
	private boolean published;
	private boolean useBasePrice;

	public static Group from(int id, EgroupIDs data, Category cat) {
		return Group.builder()
				.groupId(id)
				.anchorable(data.anchorable)
				.anchored(data.anchored)
				.category(cat)
				.fittableNonSingleton(data.fittableNonSingleton)
				.iconID(data.iconID)
				.name(data.enName())
				.published(data.published)
				.useBasePrice(data.useBasePrice)
				.build();
	}

}
