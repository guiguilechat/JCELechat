package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_industry_systems_cost_indices {
    /**
     * activity string
     */
    public String activity;
    /**
     * cost_index number
     */
    public float cost_index;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_industry_systems_cost_indices othersame = ((R_get_industry_systems_cost_indices) other);
        if ((activity!= othersame.activity)&&((activity == null)||(!activity.equals(othersame.activity)))) {
            return false;
        }
        if (cost_index!= othersame.cost_index) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((activity == null)? 0 :activity.hashCode())+ Double.hashCode(cost_index));
    }
}
