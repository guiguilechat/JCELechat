package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class M_get_bookmarks_item_2 {
    /**
     * item_id integer
     */
    public long item_id;
    /**
     * type_id integer
     */
    public int type_id;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        M_get_bookmarks_item_2 othersame = ((M_get_bookmarks_item_2) other);
        if (item_id!= othersame.item_id) {
            return false;
        }
        if (type_id!= othersame.type_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (Long.hashCode(item_id)+ type_id);
    }
}
