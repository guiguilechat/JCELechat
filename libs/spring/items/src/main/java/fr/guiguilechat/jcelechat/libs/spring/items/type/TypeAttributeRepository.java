package fr.guiguilechat.jcelechat.libs.spring.items.type;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import fr.guiguilechat.jcelechat.libs.spring.items.attribute.Attribute;

public interface TypeAttributeRepository extends JpaRepository<TypeAttribute, Long> {

	@Modifying
	@Query("delete from EsiItemsTypeAttribute where type.id in :typeIds")
	void deleteByTypeId(Iterable<Integer> typeIds);

	List<TypeAttribute> findAllByAttributeId(int attributeId);

	List<TypeAttribute> findAllByAttribute(Attribute attribute);

}
