package fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.list;

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
 * @param <FetchResource> entity list that contains the elements
 */
@SuperBuilder(toBuilder = true)
@Getter
@MappedSuperclass
@NoArgsConstructor
@Setter
public abstract class AFetchedListElement<Self extends AFetchedListElement<?, ?>, FetchResource extends AFetchedList<?, ?, Self>> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	@ManyToOne
	// @JoinColumn(foreignKey = @ForeignKey(name = "none"))
	private FetchResource fetchResource;

}
