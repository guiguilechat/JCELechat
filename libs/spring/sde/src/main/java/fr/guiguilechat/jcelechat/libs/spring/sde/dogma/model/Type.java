package fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model;

import java.io.Serializable;
import java.util.List;

import fr.guiguilechat.jcelechat.model.sde.load.fsd.EtypeIDs;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@SuppressWarnings("serial")
@Entity(name = "SdeDogmaType")
@Table(name = "sde_dogma_type", indexes = {
		@Index(columnList = "name"),
		@Index(columnList = "group_group_id") })
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Type implements Serializable {

	@Id
	private int typeId;

	@ManyToOne
	private Group group;

	@ToString.Exclude
	@OneToMany(mappedBy = "type", cascade = { CascadeType.ALL })
	private List<TypeAttribute> attributes;

	private float basePrice;
	private float capacity;
	private int factionID;
	private int graphicID;
	private int iconID;
	private int marketGroupId;
	private float mass;
	private int metaGroupId;
	private String name;
	private int portionSize;
	private boolean published;
	private int raceID;
	private float radius;
	private String sofFactionName;
	private int sofMaterialSetId;
	private int soundID;
	private int variationParentTypeId;
	private float volume;

	public static Type from(int id, EtypeIDs data, Group grp) {
		return Type.builder()
				.typeId(id)
				.build()
				.update(data, grp);
	}

	public Type update(EtypeIDs data, Group parent) {
		basePrice = data.basePrice;
		capacity = data.capacity;
		factionID = data.factionID;
		graphicID = data.graphicID;
		group = parent;
		iconID = data.iconID;
		marketGroupId = data.marketGroupID;
		mass = data.mass;
		metaGroupId = data.metaGroupID;
		name = data.enName();
		portionSize = data.portionSize;
		published = data.published;
		raceID = data.raceID;
		radius = data.radius;
		sofFactionName = data.sofFactionName;
		sofMaterialSetId = data.sofMaterialSetID;
		soundID = data.soundID;
		variationParentTypeId = data.variationParentTypeID;
		volume = data.volume;
		return this;
	}

}
