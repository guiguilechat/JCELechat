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
import fr.guiguilechat.jcelechat.libs.spring.remotefetching.resource.ARemoteFetchedResourceService;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_groups_group_id;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@ConfigurationProperties(prefix = "esi.items.group")
@Order(2)
public class GroupService
	extends ARemoteFetchedResourceService<
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

	@Override
	protected void updateResponseOk(Group data, Requested<R_get_universe_groups_group_id> response) {
		super.updateResponseOk(data, response);
		data.setCategory(categoryService.createIfAbsent(response.getOK().category_id));
	}

	public List<Group> byName(String nameIgnoreCase) {
		return repo().findByNameEqualsIgnoreCase(nameIgnoreCase);
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
