package fr.guiguilechat.jcelechat.libs.spring.sde.dogma.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Type;

public interface TypeRepository extends JpaRepository<Type, Integer> {

	public List<Type> findByGroupGroupId(int groupId);

	public List<Type> findByGroupNameEqualsIgnoreCase(String groupName);

	public List<Type> findByNameEqualsIgnoreCase(String name);

}
