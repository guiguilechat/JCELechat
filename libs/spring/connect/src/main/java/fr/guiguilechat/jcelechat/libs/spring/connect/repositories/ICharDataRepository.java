package fr.guiguilechat.jcelechat.libs.spring.connect.repositories;

import org.springframework.data.repository.NoRepositoryBean;

import fr.guiguilechat.jcelechat.libs.spring.connect.model.ACharData;

@NoRepositoryBean
public interface ICharDataRepository<Entity extends ACharData<?>> extends IFetchedResourceRepository<Entity, Integer> {

}
