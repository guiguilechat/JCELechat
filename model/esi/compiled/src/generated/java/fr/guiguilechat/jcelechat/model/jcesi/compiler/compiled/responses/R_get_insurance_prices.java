package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_get_insurance_prices {
    /**
     * A list of a available insurance levels for this ship type
     */
    public R_get_insurance_prices_levels[] levels;
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
        R_get_insurance_prices othersame = ((R_get_insurance_prices) other);
        if ((levels!= othersame.levels)&&((levels == null)||(!levels.equals(othersame.levels)))) {
            return false;
        }
        if (type_id!= othersame.type_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((levels == null)? 0 :levels.hashCode())+ type_id);
    }
}
