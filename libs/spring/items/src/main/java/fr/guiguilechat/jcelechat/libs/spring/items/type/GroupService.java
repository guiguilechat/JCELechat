package fr.guiguilechat.jcelechat.libs.spring.items.type;

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
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_groups_group_id;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.items.group")
@Order(2)
public class GroupService
	extends ARemoteEntityService<
        Group,
        Integer,
        R_get_universe_groups_group_id,
        GroupRepository> {

	private final CategoryService categoryService;

	@Override
	protected Group create(Integer entityId) {
		Group ret = new Group();
		ret.setId(entityId);
		return ret;
	}

	@Override
	protected Requested<R_get_universe_groups_group_id> fetchData(Integer id, Map<String, String> properties) {
		return ESIRawPublic.INSTANCE.get_universe_groups((int) id, properties);
	}

	@Override
	protected Function<Map<String, String>, Requested<List<Integer>>> listFetcher() {
		return p -> ESIRawPublic.INSTANCE
		    .requestGetPages((page, props) -> ESIRawPublic.INSTANCE.get_universe_groups(page, props), p);
	}

	protected void updateResponseOk(Group data, R_get_universe_groups_group_id response, Map<Integer, Category> idToCat) {
		data.setCategory(idToCat.get(response.category_id));
	}

	@Override
	protected void updateResponseOk(Map<Group, R_get_universe_groups_group_id> responseOk) {
		super.updateResponseOk(responseOk);
		Map<Integer, Category> idToCat = categoryService
		    .createIfAbsent(responseOk.values().stream().map(r -> r.category_id).distinct().toList());
		responseOk.entrySet().stream()
		    .forEach(e -> updateResponseOk(e.getKey(), e.getValue(), idToCat));
	}

	public List<Group> byName(String nameIgnoreCase) {
		return repo().findByNameEqualsIgnoreCase(nameIgnoreCase);
	}

	public List<Group> byNameContains(String nameIgnoreCase) {
		return repo().findByNameContainsIgnoreCase(nameIgnoreCase);
	}

	public List<Group> byNameInIgnoreCase(Iterable<String> names) {
		return repo().findByNameInIgnoreCase(names);
	}

	public List<Group> byCatId(int catId) {
		return repo().findByCategoryId(catId);
	}

	public Group prevGroup(Group group) {
		return repo().findTop1ByCategoryAndNameLessThanOrderByNameDesc(group.getCategory(), group.getName());
	}

	public Group nextGroup(Group group) {
		return repo().findTop1ByCategoryAndNameGreaterThanOrderByNameAsc(group.getCategory(), group.getName());
	}

}
