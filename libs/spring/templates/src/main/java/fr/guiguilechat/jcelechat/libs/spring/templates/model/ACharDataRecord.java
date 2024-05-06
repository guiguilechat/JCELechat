package fr.guiguilechat.jcelechat.libs.spring.templates.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @param <Self>          self implementation
 * @param <FetchResource> actual entity that hold the fetch parameters.
 */
@SuperBuilder
@Getter
@MappedSuperclass
@NoArgsConstructor
@Setter
public abstract class ACharDataRecord<Self extends ACharDataRecord<?, ?>, FetchResource extends ACharDataRecordList<?, Self>> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	@ManyToOne
	private FetchResource fetchResource;

}
