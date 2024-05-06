package fr.guiguilechat.jcelechat.libs.spring.templates.model;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

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
@SuperBuilder
public abstract class ACharDataRecordList<Fetched, RecordType extends ACharDataRecord<?, ?>>
    extends ACharData<Fetched[]> {

	@Override
	public void update(Fetched[] data) {
	}


}
