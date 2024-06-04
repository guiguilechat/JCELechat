package fr.guiguilechat.jcelechat.libs.spring.remotefetching.list;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IFetchedListElementRepository<
		ListType extends AFetchedList<?, ?, ?>,
		ListElement extends AFetchedListElement<?, ListType>
	> extends JpaRepository<ListElement, Long> {

	public void deleteByFetchResource(ListType fetchResource);

	public List<ListElement> findAllByFetchResourceId(int id);

}
