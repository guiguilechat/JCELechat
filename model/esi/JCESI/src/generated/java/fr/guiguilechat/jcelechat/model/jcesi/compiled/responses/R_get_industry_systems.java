package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_industry_systems {
    /**
     * cost_indices array
     */
    public R_get_industry_systems_cost_indices[] cost_indices;
    /**
     * solar_system_id integer
     */
    public int solar_system_id;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_industry_systems othersame = ((R_get_industry_systems) other);
        if ((cost_indices!= othersame.cost_indices)&&((cost_indices == null)||(!cost_indices.equals(othersame.cost_indices)))) {
            return false;
        }
        if (solar_system_id!= othersame.solar_system_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((cost_indices == null)? 0 :cost_indices.hashCode())+ solar_system_id);
    }
}
