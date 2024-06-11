package fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model;

import java.io.Serializable;

import fr.guiguilechat.jcelechat.model.sde.load.fsd.Egroups;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@SuppressWarnings("serial")
@Entity(name = "SdeDogmaGroup")
@Table(name = "sde_dogma_group", indexes = {
		@Index(columnList = "name"),
		@Index(columnList = "category_category_id") })
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Group implements Serializable {

	@Id
	private int groupId;
	@ManyToOne
	private Category category;

	private boolean anchorable;
	private boolean anchored;
	private boolean fittableNonSingleton;
	private int iconID;
	private String name;
	private boolean published;
	private boolean useBasePrice;

	public static Group from(int id, Egroups data, Category cat) {
		return Group.builder()
				.groupId(id)
				.build()
				.update(data, cat);
	}

	public Group update(Egroups data, Category cat) {
		category = cat;
		anchorable = data.anchorable;
		anchored = data.anchored;
		fittableNonSingleton = data.fittableNonSingleton;
		iconID = data.iconID;
		name = data.enName();
		published = data.published;
		useBasePrice = data.useBasePrice;
		return this;
	}

}
