package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_get_insurance_prices_levels {
    /**
     * cost number
     */
    public float cost;
    /**
     * Localized insurance level
     */
    public String name;
    /**
     * payout number
     */
    public float payout;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_insurance_prices_levels othersame = ((R_get_insurance_prices_levels) other);
        if (cost!= othersame.cost) {
            return false;
        }
        if ((name!= othersame.name)&&((name == null)||(!name.equals(othersame.name)))) {
            return false;
        }
        if (payout!= othersame.payout) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((Double.hashCode(cost)+((name == null)? 0 :name.hashCode()))+ Double.hashCode(payout));
    }
}
