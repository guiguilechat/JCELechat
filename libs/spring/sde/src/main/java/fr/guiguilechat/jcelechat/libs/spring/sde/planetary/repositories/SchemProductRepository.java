package fr.guiguilechat.jcelechat.libs.spring.sde.planetary.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.planetary.model.SchemProduct;

public interface SchemProductRepository extends JpaRepository<SchemProduct, Long> {

	public List<SchemProduct> findAllByTypeIn(Iterable<Type> types);

}
