package fr.guiguilechat.jcelechat.libs.spring.connect.templates.repositories;

import org.springframework.data.repository.NoRepositoryBean;

import fr.guiguilechat.jcelechat.libs.spring.connect.templates.model.ACharData;

@NoRepositoryBean
public interface ICharDataRepository<Entity extends ACharData<?>> extends IRemoteFetchedResourceRepository<Entity, Integer> {

}
