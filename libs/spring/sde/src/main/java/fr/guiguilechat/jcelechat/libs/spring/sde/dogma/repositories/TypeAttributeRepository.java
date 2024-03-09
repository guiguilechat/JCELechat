package fr.guiguilechat.jcelechat.libs.spring.sde.dogma.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.TypeAttribute;

public interface TypeAttributeRepository extends JpaRepository<TypeAttribute, Long> {

	public List<TypeAttribute> findAllByAttributeAttributeId(int attributeId);

}
