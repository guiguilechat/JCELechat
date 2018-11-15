package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_get_markets_groups_market_group_id {
    /**
     * description string
     */
    public String description;
    /**
     * market_group_id integer
     */
    public int market_group_id;
    /**
     * name string
     */
    public String name;
    /**
     * parent_group_id integer
     */
    public int parent_group_id;
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
        R_get_markets_groups_market_group_id othersame = ((R_get_markets_groups_market_group_id) other);
        if ((description!= othersame.description)&&((description == null)||(!description.equals(othersame.description)))) {
            return false;
        }
        if (market_group_id!= othersame.market_group_id) {
            return false;
        }
        if ((name!= othersame.name)&&((name == null)||(!name.equals(othersame.name)))) {
            return false;
        }
        if (parent_group_id!= othersame.parent_group_id) {
            return false;
        }
        if ((types!= othersame.types)&&((types == null)||(!types.equals(othersame.types)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((description == null)? 0 :description.hashCode())+ market_group_id)+((name == null)? 0 :name.hashCode()))+ parent_group_id)+((types == null)? 0 :types.hashCode()));
    }
}
