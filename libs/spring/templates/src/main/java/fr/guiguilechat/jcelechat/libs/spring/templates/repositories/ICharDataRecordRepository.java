package fr.guiguilechat.jcelechat.libs.spring.templates.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import fr.guiguilechat.jcelechat.libs.spring.templates.model.ACharDataRecord;
import fr.guiguilechat.jcelechat.libs.spring.templates.model.ACharDataRecordList;

@NoRepositoryBean
public interface ICharDataRecordRepository<ListType extends ACharDataRecordList<?, ?>, ListElement extends ACharDataRecord<?, ListType>>
    extends JpaRepository<ListElement, Long> {

	public void deleteByFetchResource(ListType fetchResource);

	public List<ListElement> findAllByFetchResourceCharacterId(int characterId);

}
