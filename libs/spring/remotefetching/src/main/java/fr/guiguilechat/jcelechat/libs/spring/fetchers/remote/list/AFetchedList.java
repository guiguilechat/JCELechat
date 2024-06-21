package fr.guiguilechat.jcelechat.libs.spring.fetchers.remote.list;

import fr.guiguilechat.jcelechat.libs.spring.fetchers.remote.resource.ARemoteResource;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Fetched resource that contains a list of elements
 * 
 * @param <Fetched>    type fetched in a array, from the remote.
 * @param <RecordType> entity to store the record as
 */

@Getter
@MappedSuperclass
@NoArgsConstructor
@Setter
public abstract class AFetchedList<Id, Fetched, RecordType extends AFetchedListElement<?, ?>>
    extends ARemoteResource<Id, Fetched[]> {

	@Override
	public void update(Fetched[] data) {
	}


}
