package fr.guiguilechat.jcelechat.libs.spring.update.entities.number.remote.list;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IFetchedListElementRepositoryAutoId<
	ListType extends AFetchedList<?, ?, ?>,
		ListElement extends AFetchedListElementAutoId<?, ListType>>
		extends IFetchedListElementRepository<ListType, ListElement, Long> {

}
