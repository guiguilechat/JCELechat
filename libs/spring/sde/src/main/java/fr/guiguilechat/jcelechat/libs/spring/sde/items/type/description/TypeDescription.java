package fr.guiguilechat.jcelechat.libs.spring.sde.items.type.description;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
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

@Entity(name = "SdeItemsTypeDescription")
@Table(name = "sde_items_typedescription", indexes = {
    @Index(columnList = "type_id")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TypeDescription {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne
	private Type type;

	@Lob
	private String description;

}
