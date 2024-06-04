package fr.guiguilechat.jcelechat.libs.spring.remotefetching.list;

import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource.ARemoteFetchedResource;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Character data that is fetched as a list of items.
 * 
 * @param <Fetched>    type fetched in a array, from the remote.
 * @param <RecordType> entity to store the record as
 */

@Getter
@MappedSuperclass
@NoArgsConstructor
@Setter
public abstract class AFetchedList<Id, Fetched, RecordType extends AFetchedListElement<?, ?>>
    extends ARemoteFetchedResource<Id, Fetched[]> {

	@Override
	public void update(Fetched[] data) {
	}


}
