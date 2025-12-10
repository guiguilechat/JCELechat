package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.market.dto;

import fr.guiguilechat.jcelechat.libs.spring.sde.items.category.Category;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.group.Group;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TypeData<T> {

	private final int typeId;
	private final String typeName;
	private final int groupId;
	private final String groupName;
	private final int categoryId;
	private final String categoryName;
	private final T data;

	public static <U> TypeData<U> of(Type type, U data) {
		Group group = type.getGroup();
		Category cat = group == null ? null : group.getCategory();
		return new TypeData<U>(
		    type.getId(), type.name(),
		    group == null ? 0 : group.getId(), group == null ? null : group.name(),
		    cat == null ? 0 : cat.getId(), cat == null ? null : cat.name(),
		    data);
	}
}
