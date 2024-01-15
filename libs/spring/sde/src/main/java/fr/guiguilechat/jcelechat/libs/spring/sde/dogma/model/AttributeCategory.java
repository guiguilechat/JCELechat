package fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity(name = "SdeDogmaAttributeCategory")
@Table(name = "sde_dogma_attributecategory")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class AttributeCategory {

	@Id
	private int attributeCategoryId;

	private String description;

	private String name;

}
