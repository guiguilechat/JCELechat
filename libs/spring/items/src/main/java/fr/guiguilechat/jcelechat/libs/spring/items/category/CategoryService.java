package fr.guiguilechat.jcelechat.libs.spring.items.category;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.update.fetched.remote.ARemoteEntityService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_categories_category_id;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.items.category")
@Order(1)
public class CategoryService
    extends ARemoteEntityService<
    	Category,
    	Integer,
    	R_get_universe_categories_category_id,
    	CategoryRepository> {

	@Override
	protected Category create(Integer entityId) {
		Category ret = new Category();
		ret.setId(entityId);
		return ret;
	}

	@Override
	protected Requested<R_get_universe_categories_category_id> fetchData(Integer id, Map<String, String> properties) {
		return ESIRawPublic.INSTANCE.get_universe_categories(id, properties);
	}

	@Override
	protected Function<Map<String, String>, Requested<List<Integer>>> listFetcher() {
		return p -> ESIRawPublic.INSTANCE.get_universe_categories(p).mapBody(List::of);
	}

	public Category prevGroup(Category c) {
		return repo().findTop1ByNameLessThanOrderByNameDesc(c.getName());
	}

	public Category nextGroup(Category c) {
		return repo().findTop1ByNameGreaterThanOrderByNameAsc(c.getName());
	}

}
