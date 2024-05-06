package fr.guiguilechat.jcelechat.libs.spring.templates.repositories;

import org.springframework.data.repository.NoRepositoryBean;

import fr.guiguilechat.jcelechat.libs.spring.templates.model.ACorpData;

@NoRepositoryBean
public interface ICorpDataRepository<Entity extends ACorpData<?>>
    extends IRemoteFetchedResourceRepository<Entity, Integer> {

}
