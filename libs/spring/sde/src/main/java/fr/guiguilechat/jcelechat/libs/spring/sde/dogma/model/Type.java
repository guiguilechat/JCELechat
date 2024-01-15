package fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model;

import fr.guiguilechat.jcelechat.model.sde.load.fsd.EtypeIDs;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity(name = "SdeDogmaType")
@Table(name = "sde_dogma_type", indexes = {
		@Index(columnList = "name") })
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Type {

	@Id
	private int typeId;

	private float basePrice;
	private float capacity;
	private int factionID;
	private int graphicID;

	@ManyToOne
	private Group group;

	private int iconID;
	private int marketGroupID;
	private float mass;
	private int metaGroupID;
	private String name;
	private int portionSize;
	private boolean published;
	private int raceID;
	private float radius;
	private String sofFactionName;
	private int sofMaterialSetID;
	private int soundID;
	private int variationParentTypeID;

	public static Type from(int id, EtypeIDs data, Group grp) {
		return Type.builder()
				.typeId(id)
				.basePrice(data.basePrice)
				.capacity(data.capacity)
				.factionID(data.factionID)
				.graphicID(data.graphicID)
				.group(grp)
				.iconID(data.iconID)
				.marketGroupID(data.marketGroupID)
				.mass(data.mass)
				.name(data.enName())
				.portionSize(data.portionSize)
				.published(data.published)
				.raceID(data.raceID)
				.radius(data.radius)
				.sofFactionName(data.sofFactionName)
				.sofMaterialSetID(data.sofMaterialSetID)
				.soundID(data.soundID)
				.variationParentTypeID(data.variationParentTypeID)
				.build();
	}

}
