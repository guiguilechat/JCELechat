package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_post_fleets_fleet_id_wings_created {
    /**
     * The wing_id of the newly created wing
     */
    public long wing_id;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_post_fleets_fleet_id_wings_created othersame = ((R_post_fleets_fleet_id_wings_created) other);
        if (wing_id!= othersame.wing_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Long.hashCode(wing_id);
    }
}
