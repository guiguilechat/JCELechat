package fr.guiguilechat.jcelechat.model.sde;

public class TypeRef<T extends EveType> {
    public int id;
    private transient T type;
    private transient String category;
    private transient String group;
    private transient String name;
    protected transient String toString;

    @SuppressWarnings("unchecked")
    public T type() {
        if (type == null) {
            type = ((T) TypeIndex.getType(id));
        }
        return type;
    }

    public String category() {
        if (category == null) {
            category = type().getCategory().getName();
        }
        return category;
    }

    public String group() {
        if (group == null) {
            group = type().getGroup().getName();
        }
        return group;
    }

    public String name() {
        if (name == null) {
            name = type().name;
        }
        return name;
    }

    @Override
    public String toString() {
        if (toString == null) {
            toString = (((name()+"(")+ id)+")");
        }
        return toString;
    }
}
