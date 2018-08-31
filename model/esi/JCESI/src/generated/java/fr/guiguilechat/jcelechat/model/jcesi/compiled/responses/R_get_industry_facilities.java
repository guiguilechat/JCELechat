package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_industry_facilities {
    /**
     * ID of the facility
     */
    public long facility_id;
    /**
     * Owner of the facility
     */
    public int owner_id;
    /**
     * Region ID where the facility is
     */
    public int region_id;
    /**
     * Solar system ID where the facility is
     */
    public int solar_system_id;
    /**
     * Tax imposed by the facility
     */
    public float tax;
    /**
     * Type ID of the facility
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
        R_get_industry_facilities othersame = ((R_get_industry_facilities) other);
        if (facility_id!= othersame.facility_id) {
            return false;
        }
        if (owner_id!= othersame.owner_id) {
            return false;
        }
        if (region_id!= othersame.region_id) {
            return false;
        }
        if (solar_system_id!= othersame.solar_system_id) {
            return false;
        }
        if (tax!= othersame.tax) {
            return false;
        }
        if (type_id!= othersame.type_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((Long.hashCode(facility_id)+ owner_id)+ region_id)+ solar_system_id)+ Double.hashCode(tax))+ type_id);
    }
}
