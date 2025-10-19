package fr.guiguilechat.jcelechat.libs.spring.sde.items.group;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic.SdeEntityService;

@Service
public class GroupService extends SdeEntityService<
        Group,
        Integer,
        GroupRepository> {

	public GroupService() {
		super(Group::new);
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
