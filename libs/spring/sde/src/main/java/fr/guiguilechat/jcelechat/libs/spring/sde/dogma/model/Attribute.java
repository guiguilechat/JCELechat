package fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model;

import fr.guiguilechat.jcelechat.model.sde.load.fsd.EdogmaAttributes;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity(name = "SdeDogmaAttribute")
@Table(name = "sde_dogma_attribute", indexes = {
		@Index(columnList = "name") })
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Attribute {

	@Id
	private int attributeId;

	private int categoryID;
	private int chargeRechargeTimeID;
	private int dataType;
	private float defaultValue;

	@Column(length = 5000)
	private String description;
	private boolean displayWhenZero;
	private boolean highIsGood;
	private int iconID;
	private int maxAttributeID;
	private String name;
	private boolean published;
	private boolean stackable;
	private Integer unitID;

	public static Attribute from(int id, EdogmaAttributes data) {
		return builder()
				.attributeId(id)
				.categoryID(data.categoryID)
				.chargeRechargeTimeID(data.chargeRechargeTimeID)
				.dataType(data.dataType)
				.defaultValue(data.defaultValue)
				.description(data.description)
				.displayWhenZero(data.displayWhenZero)
				.highIsGood(data.highIsGood)
				.iconID(data.iconID)
				.maxAttributeID(data.maxAttributeID)
				.name(data.name)
				.published(data.published)
				.stackable(data.stackable)
				.unitID(data.unitID)
				.build();
	}

}
