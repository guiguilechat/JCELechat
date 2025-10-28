package fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.list;

import java.util.ArrayList;
import java.util.List;

import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.RemoteEntity;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToMany;
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
public abstract class AFetchedList<Id extends Number, Fetched, RecordType extends AFetchedListElement<?, ?>>
    extends RemoteEntity<Id, Fetched[]> {

	@OneToMany(mappedBy = "fetchResource")
	private List<RecordType> elements = new ArrayList<>();

	@Override
	public void update(Fetched[] data) {
	}


}
