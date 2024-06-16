package fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model;

import java.io.Serializable;
import java.util.Map;

import fr.guiguilechat.jcelechat.model.sde.load.fsd.Ecategories;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Deprecated
@SuppressWarnings("serial")
@Entity(name = "SdeDogmaCategory")
@Table(name = "sde_dogma_category", indexes = {
		@Index(columnList = "name") })
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Category implements Serializable{

	@Id
	private int categoryId;

	private int iconID;
	// only save the english name. Maybe should make another table ? not sure.
	private String name;
	private boolean published;

	public static Category from(int id, Ecategories from) {
		return Category.builder()
				.categoryId(id)
				.build()
				.update(from);
	}

	public static Category from(Map.Entry<Integer, Ecategories> entry) {
		return from(entry.getKey(), entry.getValue());
	}

	public Category update(Ecategories c) {
		iconID = c.iconID;
		name = c.enName();
		published = c.published;
		return this;
	}

}
