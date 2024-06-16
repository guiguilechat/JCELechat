package fr.guiguilechat.jcelechat.libs.spring.industry.planetary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;

public interface SchemProductRepository extends JpaRepository<SchemProduct, Long> {

	public List<SchemProduct> findAllByTypeIn(Iterable<Type> types);

}
