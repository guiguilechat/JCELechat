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
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class TypeService
	extends ARemoteFetchedResourceService<
        Type,
        Integer,
        R_get_universe_types_type_id,
        TypeRepository> {

	private final GroupService groupService;

	@Override
	protected Type create(Integer entityId) {
		Type ret = new Type();
		ret.setId(entityId);
		return ret;
	}

	@Override
	protected Requested<R_get_universe_types_type_id> fetchData(Integer id, Map<String, String> properties) {
		return ESIRawPublic.INSTANCE.get_universe_types((int) id, properties);
	}

	@Override
	protected Function<Map<String, String>, Requested<List<Integer>>> listFetcher() {
		return p -> ESIRawPublic.INSTANCE
		    .requestGetPages((page, props) -> ESIRawPublic.INSTANCE.get_universe_types(page, props), p);
	}

	@Override
	protected void updateResponseOk(Type data, Requested<R_get_universe_types_type_id> response) {
		super.updateResponseOk(data, response);
		data.setGroup(groupService.createIfAbsent(response.getOK().group_id));
	}

	@Getter
	@Value("${esi.items.type.update.max:10000}")
	private int maxUpdates = 10000;

	@Getter
	@Value("${esi.items.type.update.skip:false}")
	private boolean skipUpdate = false;

}
