package fr.guiguilechat.jcelechat.libs.spring.update.batch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import fr.guiguilechat.jcelechat.libs.spring.update.batch.BatchFetch.STATUS;

@NoRepositoryBean
public interface BatchFetchRepository<Fetch extends BatchFetch<?>> extends JpaRepository<Fetch, Long> {
	
	public Fetch findTop1ByStatusOrderByLastModifiedDesc(STATUS status);

}
