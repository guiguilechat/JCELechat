package fr.guiguilechat.jcelechat.libs.spring.update.entities.number.remote.list;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@MappedSuperclass
@NoArgsConstructor
@Setter
public class AFetchedListElementAutoId<Self extends AFetchedListElementAutoId<?, ?>, FetchResource extends AFetchedList<?, ?, Self>>
		extends AFetchedListElement<Self, FetchResource> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

}
