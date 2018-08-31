package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_universe_groups_group_id {
    /**
     * category_id integer
     */
    public int category_id;
    /**
     * group_id integer
     */
    public int group_id;
    /**
     * name string
     */
    public String name;
    /**
     * published boolean
     */
    public boolean published;
    /**
     * types array
     */
    public int[] types;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_universe_groups_group_id othersame = ((R_get_universe_groups_group_id) other);
        if (category_id!= othersame.category_id) {
            return false;
        }
        if (group_id!= othersame.group_id) {
            return false;
        }
        if ((name!= othersame.name)&&((name == null)||(!name.equals(othersame.name)))) {
            return false;
        }
        if (published!= othersame.published) {
            return false;
        }
        if ((types!= othersame.types)&&((types == null)||(!types.equals(othersame.types)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((category_id + group_id)+((name == null)? 0 :name.hashCode()))+ Boolean.hashCode(published))+((types == null)? 0 :types.hashCode()));
    }
}
