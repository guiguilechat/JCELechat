package fr.guiguilechat.jcelechat.libs.spring.templates.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import fr.guiguilechat.jcelechat.libs.spring.templates.model.AFetchedList;
import fr.guiguilechat.jcelechat.libs.spring.templates.model.AFetchedListElement;

@NoRepositoryBean
public interface IFetchedListElementRepository<
		ListType extends AFetchedList<?, ?, ?>,
		ListElement extends AFetchedListElement<?, ListType>
	> extends JpaRepository<ListElement, Long> {

	public void deleteByFetchResource(ListType fetchResource);

	public List<ListElement> findAllByFetchResourceRemoteId(int RemoteId);

}
