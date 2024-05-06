package fr.guiguilechat.jcelechat.libs.spring.connect.templates.repositories;

import org.springframework.data.repository.NoRepositoryBean;

import fr.guiguilechat.jcelechat.libs.spring.connect.templates.model.ACorpData;

@NoRepositoryBean
public interface ICorpDataRepository<Entity extends ACorpData<?>>
    extends IRemoteFetchedResourceRepository<Entity, Integer> {

}
