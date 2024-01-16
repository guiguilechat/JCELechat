package fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model;

import java.util.Map;

import fr.guiguilechat.jcelechat.model.sde.load.fsd.EcategoryIDs;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity(name = "SdeDogmaCategory")
@Table(name = "sde_dogma_category", indexes = {
		@Index(columnList = "name") })
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Category {

	@Id
	private int categoryId;

	private int iconID;
	// only save the english name. Maybe should make another table ? not sure.
	private String name;
	private boolean published;

	public static Category from(int id, EcategoryIDs from) {
		return Category.builder()
				.categoryId(id)
				.iconID(from.iconID)
				.name(from.enName())
				.published(from.published)
				.build();
	}

	public static Category from(Map.Entry<Integer, EcategoryIDs> entry) {
		return from(entry.getKey(), entry.getValue());
	}

}
