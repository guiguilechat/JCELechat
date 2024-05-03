package fr.guiguilechat.jcelechat.libs.spring.connect.repositories.templates;

import org.springframework.data.repository.NoRepositoryBean;

import fr.guiguilechat.jcelechat.libs.spring.connect.model.templates.ACharData;

@NoRepositoryBean
public interface ICharDataRepository<Entity extends ACharData<?>> extends IRemoteFetchedResourceRepository<Entity, Integer> {

}
