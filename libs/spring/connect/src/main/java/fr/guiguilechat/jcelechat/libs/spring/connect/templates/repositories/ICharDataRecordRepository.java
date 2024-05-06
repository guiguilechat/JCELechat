package fr.guiguilechat.jcelechat.libs.spring.connect.templates.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import fr.guiguilechat.jcelechat.libs.spring.connect.templates.model.ACharDataRecord;
import fr.guiguilechat.jcelechat.libs.spring.connect.templates.model.ACharDataRecordList;

@NoRepositoryBean
public interface ICharDataRecordRepository<ListType extends ACharDataRecordList<?, ?>, Entry extends ACharDataRecord<?, ListType>>
    extends JpaRepository<Entry, Long> {

	public void deleteByFetchResource(ListType fetchResource);

	public List<Entry> findAllByFetchResourceCharacterId(int characterId);

}
