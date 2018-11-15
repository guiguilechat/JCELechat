package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class M_post_assets_names_2 {
    /**
     * item_id integer
     */
    public long item_id;
    /**
     * name string
     */
    public String name;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        M_post_assets_names_2 othersame = ((M_post_assets_names_2) other);
        if (item_id!= othersame.item_id) {
            return false;
        }
        if ((name!= othersame.name)&&((name == null)||(!name.equals(othersame.name)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (Long.hashCode(item_id)+((name == null)? 0 :name.hashCode()));
    }
}
