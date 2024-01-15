package fr.guiguilechat.jcelechat.libs.spring.sde.dogma.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Group;

public interface GroupRepository extends JpaRepository<Group, Integer> {

}
