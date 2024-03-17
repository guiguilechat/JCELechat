package fr.guiguilechat.jcelechat.libs.spring.sde.dogma.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Group;
import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Type;

public interface TypeRepository extends JpaRepository<Type, Integer> {

	public List<Type> findByGroupGroupId(int groupId);

	public List<Type> findByGroupGroupIdIn(Iterable<Integer> groupIds);

	public List<Type> findByGroupNameEqualsIgnoreCase(String groupName);

	public List<Type> findByNameEqualsIgnoreCase(String name);

	public Type findTop1ByGroupAndNameGreaterThanOrderByNameAsc(Group group, String name);

	public Type findTop1ByGroupAndNameLessThanOrderByNameDesc(Group group, String name);

}
