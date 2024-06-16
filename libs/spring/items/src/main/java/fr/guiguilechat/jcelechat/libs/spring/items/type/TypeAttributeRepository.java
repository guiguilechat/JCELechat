package fr.guiguilechat.jcelechat.libs.spring.items.type;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.items.attribute.Attribute;

public interface TypeAttributeRepository extends JpaRepository<TypeAttribute, Long> {

	void deleteByType(Type type);

	List<TypeAttribute> findAllByAttributeId(int attributeId);

	List<TypeAttribute> findAllByAttribute(Attribute attribute);

}
