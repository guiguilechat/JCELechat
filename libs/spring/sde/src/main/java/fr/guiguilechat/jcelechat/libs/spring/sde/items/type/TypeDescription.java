package fr.guiguilechat.jcelechat.libs.spring.sde.items.type;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "EsiItemsTypeDescription")
@Table(name = "esi_items_typedescription", indexes = {
    @Index(columnList = "type_id")
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TypeDescription {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne
	private Type type;

	@Lob
	private String description;

}
