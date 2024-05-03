package fr.guiguilechat.jcelechat.libs.spring.connect.repositories.templates;

import org.springframework.data.repository.NoRepositoryBean;

import fr.guiguilechat.jcelechat.libs.spring.connect.model.templates.ACorpData;

@NoRepositoryBean
public interface ICorpDataRepository<Entity extends ACorpData<?>>
    extends IRemoteFetchedResourceRepository<Entity, Integer> {

}
