package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_fleet_role;

public class R_get_characters_character_id_fleet {
    /**
     * Character ID of the current fleet boss
     */
    public long fleet_boss_id;
    /**
     * The character's current fleet ID
     */
    public long fleet_id;
    /**
     * Member’s role in fleet
     */
    public get_characters_character_id_fleet_role role;
    /**
     * ID of the squad the member is in. If not applicable, will be set to -1
     */
    public long squad_id;
    /**
     * ID of the wing the member is in. If not applicable, will be set to -1
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
        R_get_characters_character_id_fleet othersame = ((R_get_characters_character_id_fleet) other);
        if (fleet_boss_id!= othersame.fleet_boss_id) {
            return false;
        }
        if (fleet_id!= othersame.fleet_id) {
            return false;
        }
        if ((role!= othersame.role)&&((role == null)||(!role.equals(othersame.role)))) {
            return false;
        }
        if (squad_id!= othersame.squad_id) {
            return false;
        }
        if (wing_id!= othersame.wing_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((Long.hashCode(fleet_boss_id)+ Long.hashCode(fleet_id))+((role == null)? 0 :role.hashCode()))+ Long.hashCode(squad_id))+ Long.hashCode(wing_id));
    }
}
