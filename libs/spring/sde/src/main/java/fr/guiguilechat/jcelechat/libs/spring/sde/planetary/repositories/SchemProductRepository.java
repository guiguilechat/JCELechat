package fr.guiguilechat.jcelechat.libs.spring.sde.planetary.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.guiguilechat.jcelechat.libs.spring.sde.dogma.model.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.planetary.model.SchemProduct;
import fr.guiguilechat.jcelechat.libs.spring.sde.planetary.model.Schematic;

public interface SchemProductRepository extends JpaRepository<SchemProduct, Long> {

	public List<Schematic> findSchematicByTypeIn(Iterable<Type> types);

}
