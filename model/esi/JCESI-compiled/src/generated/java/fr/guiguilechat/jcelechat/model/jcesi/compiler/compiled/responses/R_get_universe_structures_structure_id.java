package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_get_universe_structures_structure_id {
    /**
     * The full name of the structure
     */
    public String name;
    /**
     * The ID of the corporation who owns this particular structure
     */
    public int owner_id;
    /**
     * Coordinates of the structure in Cartesian space relative to the Sun, in metres.
     * 
     */
    public M_3_xnumber_ynumber_znumber position;
    /**
     * solar_system_id integer
     */
    public int solar_system_id;
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
        R_get_universe_structures_structure_id othersame = ((R_get_universe_structures_structure_id) other);
        if ((name!= othersame.name)&&((name == null)||(!name.equals(othersame.name)))) {
            return false;
        }
        if (owner_id!= othersame.owner_id) {
            return false;
        }
        if ((position!= othersame.position)&&((position == null)||(!position.equals(othersame.position)))) {
            return false;
        }
        if (solar_system_id!= othersame.solar_system_id) {
            return false;
        }
        if (type_id!= othersame.type_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((name == null)? 0 :name.hashCode())+ owner_id)+((position == null)? 0 :position.hashCode()))+ solar_system_id)+ type_id);
    }
}
