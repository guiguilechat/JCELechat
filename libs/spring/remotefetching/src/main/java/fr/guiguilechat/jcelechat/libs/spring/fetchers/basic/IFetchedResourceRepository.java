package fr.guiguilechat.jcelechat.libs.spring.fetchers.basic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IFetchedResourceRepository<Entity extends AFetchedResource<Id>, Id extends Number>
    extends JpaRepository<Entity, Id> {

}
