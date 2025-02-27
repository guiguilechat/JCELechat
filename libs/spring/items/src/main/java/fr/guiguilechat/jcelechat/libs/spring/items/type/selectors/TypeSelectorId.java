package fr.guiguilechat.jcelechat.libs.spring.items.type.selectors;

import java.util.List;
import java.util.Optional;

import fr.guiguilechat.jcelechat.libs.spring.items.type.NamedTypelist;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.items.type.TypeRepository;
import fr.guiguilechat.jcelechat.libs.spring.items.type.TypeSelector;

public enum TypeSelectorId implements TypeSelector<Integer> {

	tid {
		@Override
		public NamedTypelist list(TypeRepository repo, Integer filter) {
			Optional<Type> found = repo.findById(filter);
			if (found.isEmpty()) {
				return new NamedTypelist("missing tid:" + filter, List.of());
			} else {
				return new NamedTypelist(found.get().toString(), List.of(found.get().getId()));
			}
		}
	},
	gid {
		@Override
		public NamedTypelist list(TypeRepository repo, Integer filter) {
			List<Type> found = repo.findByGroupId(filter);
			String name = found.isEmpty() ? "empty gid:" + filter : found.get(0).getGroup().toString();
			List<Integer> ids = found.stream().map(Type::getId).toList();
			return new NamedTypelist(name, ids);
		}
	},
	cid {
		@Override
		public NamedTypelist list(TypeRepository repo, Integer filter) {
			List<Type> found = repo.findByGroupCategoryId(filter);
			String name = found.isEmpty() ? "empty cid :" + filter : found.get(0).getGroup().getCategory().toString();
			List<Integer> ids = found.stream().map(Type::getId).toList();
			return new NamedTypelist(name, ids);
		}
	}
	;

}
