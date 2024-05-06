package fr.guiguilechat.jcelechat.libs.spring.templates.repositories;

import org.springframework.data.repository.NoRepositoryBean;

import fr.guiguilechat.jcelechat.libs.spring.templates.model.ACharData;

@NoRepositoryBean
public interface ICharDataRepository<Entity extends ACharData<?>> extends IRemoteFetchedResourceRepository<Entity, Integer> {

}
