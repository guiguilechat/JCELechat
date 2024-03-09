package fr.guiguilechat.jcelechat.libs.spring.sde.planetary.model;

import java.io.Serializable;

import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Type;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@SuppressWarnings("serial")
@Entity(name = "SdePlanetarySchemMaterial")
@Table(name = "sde_planetary_schematic_material")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class SchemMaterial implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	private Schematic schematic;

	@ManyToOne
	private Type type;

	private int quantity;

}
