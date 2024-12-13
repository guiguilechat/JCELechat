package fr.guiguilechat.jcelechat.libs.spring.items.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TypeDataDto<T> {

	private final int typeId;
	private final String typeName;
	private final int groupId;
	private final String groupName;
	private final int categoryId;
	private final String categoryName;
	private final T data;

	public static <U> TypeDataDto<U> of(Type type, U data) {
		Group group = type.getGroup();
		Category cat = group == null ? null : group.getCategory();
		return new TypeDataDto<U>(
		    type.getId(), type.name(),
		    group == null ? 0 : group.getId(), group == null ? null : group.name(),
		    cat == null ? 0 : cat.getId(), cat == null ? null : cat.name(),
		    data);
	}
}
