package fr.guiguilechat.jcelechat.libs.spring.update.entities.number.remote.list;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public abstract class FetchedListElementService<
	ListElement extends AFetchedListElement<?, ?>,
	ElementRepo extends IFetchedListElementRepository<?, ListElement, ?>
> {

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private ElementRepo repo;

	public List<ListElement> list(int id) {
		return repo().findAllByFetchResourceId(id);
	}

}
