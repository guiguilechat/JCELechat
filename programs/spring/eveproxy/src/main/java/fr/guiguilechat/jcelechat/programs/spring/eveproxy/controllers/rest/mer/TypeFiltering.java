package fr.guiguilechat.jcelechat.programs.spring.eveproxy.controllers.rest.mer;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

import fr.guiguilechat.jcelechat.libs.spring.items.group.Group;
import fr.guiguilechat.jcelechat.libs.spring.items.group.GroupService;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.items.type.dto.NamedTypelist;

/**
 * request to filter the typed by
 */
public enum TypeFiltering {
	TYPE_ID {
		@Override
		public NamedTypelist resolve(String filterparam, TypeService typeService, GroupService groupService) {
			int typeId = Integer.parseInt(filterparam);
			Type type = typeService.byId(typeId);
			return type == null ? new NamedTypelist("unknown t" + typeId, Collections.emptyList())
			    : new NamedTypelist(type.name(), List.of(typeId));
		}
	},
	GROUP_ID {
		@Override
		public NamedTypelist resolve(String filterparam, TypeService typeService, GroupService groupService) {
			int groupId = Integer.parseInt(filterparam);
			Group group = groupService.byId(groupId);
			return group == null ? new NamedTypelist("unknown g" + groupId, Collections.emptyList())
					: new NamedTypelist(group.getName(),
			        typeService.byGroupId(groupId).stream().map(Type::getId).distinct().sorted().toList());
		}
	},
	TYPE_NAME {
		@Override
		public NamedTypelist resolve(String filterparam, TypeService typeService, GroupService groupService) {
			List<Type> list = typeService.searchByName(filterparam);
			if (list.isEmpty()) {
				return new NamedTypelist("unmatched type name " + filterparam, Collections.emptyList());
			}
			String name = list.size() == 1 ? list.get(0).name()
					: "matched " + list.size() + " type names " + filterparam;
			return new NamedTypelist(name, list.stream().map(Type::getId).sorted().toList());
		}
	},
	GROUP_NAME {
		@Override
		public NamedTypelist resolve(String filterparam, TypeService typeService, GroupService groupService) {
			List<Group> list = groupService.byName(filterparam);
			if (list.isEmpty()) {
				return new NamedTypelist("unmatched group name " + filterparam, Collections.emptyList());
			}
			String name = list.size() == 1 ? list.get(0).getName()
					: "matched " + list.size() + " group names " + filterparam;
			return new NamedTypelist(name,
			    list.stream().flatMap(g -> typeService.byGroupId(g.getId()).stream()).map(Type::getId)
							.distinct().sorted().toList());
		}
	},
	ERROR {
		@Override
		public NamedTypelist resolve(String filterparam, TypeService typeService, GroupService groupService) {
			return new NamedTypelist("invalid selector", Collections.emptyList());
		}
	};

	public abstract NamedTypelist resolve(String filterparam, TypeService typeService, GroupService groupService);

	public static TypeFiltering of(String filterBy) {
		return switch (Objects.requireNonNullElse(filterBy, "").toLowerCase()) {
			case "gi", "gid", "groupid" -> GROUP_ID;
			case "gn", "gname", "groupname" -> GROUP_NAME;
			case "ti", "tid", "typeid" -> TYPE_ID;
			case "tn", "tname", "typename" -> TYPE_NAME;
			default -> ERROR;
		};
	}
}