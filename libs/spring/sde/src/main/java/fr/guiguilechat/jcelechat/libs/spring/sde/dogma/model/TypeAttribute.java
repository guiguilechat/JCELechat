package fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model;

import java.math.BigDecimal;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Deprecated
@Entity(name = "SdeDogmaTypeAttribute")
@Table(name = "sde_dogma_typeattribute", indexes = {
		@Index(columnList = "attribute_attribute_id"),
		@Index(columnList = "type_type_id")
})
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class TypeAttribute {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne(cascade = { CascadeType.ALL })
	private Attribute attribute;

	@ManyToOne(cascade = { CascadeType.ALL })
	private Type type;

	private BigDecimal attValue;

	public static TypeAttribute from(Type type, Attribute attribute, Number value) {
		return builder()
				.attribute(attribute)
				.type(type)
				.attValue(BigDecimal.valueOf(value.doubleValue()))
				.build();
	}

}
