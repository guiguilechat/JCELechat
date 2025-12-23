package fr.guiguilechat.jcelechat.libs.spring.anon.industry.planetary;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SchemProductService {

	final private SchemProductRepository repo;

	public void clear() {
		repo.deleteAllInBatch();
	}

	public SchemProduct save(SchemProduct entity) {
		return repo.save(entity);
	}

	public List<SchemProduct> saveAll(Iterable<SchemProduct> entities) {
		return repo.saveAll(entities);
	}

	@Transactional
	public List<Schematic> producers(List<Type> products) {
		List<Schematic> ret = repo.findAllByTypeIn(products).stream().map(SchemProduct::getSchematic).toList();
// System.err.println(
// "fetched " + ret.size() + " schematic producing " +
// products.stream().map(Type::getTypeId).toList() + " types");
		return ret;
	}

}
