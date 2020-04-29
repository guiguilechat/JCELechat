package fr.guiguilechat.jcelechat.model.sde.industry;

import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.TypeIndex;

/** reference to a type */
public class TypeRef<T extends EveType> {

	public int id;

	private transient T type;

	@SuppressWarnings("unchecked")
	public T type() {
		if (type == null) {
			type = (T) TypeIndex.getType(id);
		}
		return type;
	}

	private transient String name;

	public String name() {
		if (name == null) {
			name = type().name;
		}
		return name;
	}

	private transient String group;

	public String group() {
		if (group == null) {
			group = type().getGroup().getName();
		}
		return group;
	}

	private transient String category;

	public String category() {
		if (category == null) {
			category = type().getCategory().getName();
		}
		return category;
	}

	protected transient String toString = null;

	@Override
	public String toString() {
		if (toString == null) {
			toString = name() + "(" + id + ")";
		}
		return toString;
	}

}
