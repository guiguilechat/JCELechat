package fr.guiguilechat.jcelechat.libs.spring.items.type;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIRawPublic;
import fr.guiguilechat.jcelechat.jcesi.interfaces.Requested;
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource.ARemoteFetchedResourceService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_categories_category_id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CategoryService
    extends ARemoteFetchedResourceService<
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

	@Value("${esi.items.category.update.skip:false}")
	@Getter
	private boolean skipUpdate = false;

}
