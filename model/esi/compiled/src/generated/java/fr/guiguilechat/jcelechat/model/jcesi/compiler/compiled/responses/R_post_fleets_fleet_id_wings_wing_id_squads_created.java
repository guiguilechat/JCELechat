package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_post_fleets_fleet_id_wings_wing_id_squads_created {
    /**
     * The squad_id of the newly created squad
     */
    public long squad_id;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_post_fleets_fleet_id_wings_wing_id_squads_created othersame = ((R_post_fleets_fleet_id_wings_wing_id_squads_created) other);
        if (squad_id!= othersame.squad_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Long.hashCode(squad_id);
    }
}
