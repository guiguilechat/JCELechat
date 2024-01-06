package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_fleets_fleet_id_members_role;

public class R_get_fleets_fleet_id_members {
    /**
     * character_id integer
     */
    public int character_id;
    /**
     * join_time string
     */
    public String join_time;
    /**
     * Member’s role in fleet
     */
    public get_fleets_fleet_id_members_role role;
    /**
     * Localized role names
     */
    public String role_name;
    /**
     * ship_type_id integer
     */
    public int ship_type_id;
    /**
     * Solar system the member is located in
     */
    public int solar_system_id;
    /**
     * ID of the squad the member is in. If not applicable, will be set to -1
     */
    public long squad_id;
    /**
     * Station in which the member is docked in, if applicable
     */
    public long station_id;
    /**
     * Whether the member take fleet warps
     */
    public boolean takes_fleet_warp;
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
        R_get_fleets_fleet_id_members othersame = ((R_get_fleets_fleet_id_members) other);
        if (character_id!= othersame.character_id) {
            return false;
        }
        if ((join_time!= othersame.join_time)&&((join_time == null)||(!join_time.equals(othersame.join_time)))) {
            return false;
        }
        if ((role!= othersame.role)&&((role == null)||(!role.equals(othersame.role)))) {
            return false;
        }
        if ((role_name!= othersame.role_name)&&((role_name == null)||(!role_name.equals(othersame.role_name)))) {
            return false;
        }
        if (ship_type_id!= othersame.ship_type_id) {
            return false;
        }
        if (solar_system_id!= othersame.solar_system_id) {
            return false;
        }
        if (squad_id!= othersame.squad_id) {
            return false;
        }
        if (station_id!= othersame.station_id) {
            return false;
        }
        if (takes_fleet_warp!= othersame.takes_fleet_warp) {
            return false;
        }
        if (wing_id!= othersame.wing_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((((character_id +((join_time == null)? 0 :join_time.hashCode()))+((role == null)? 0 :role.hashCode()))+((role_name == null)? 0 :role_name.hashCode()))+ ship_type_id)+ solar_system_id)+ Long.hashCode(squad_id))+ Long.hashCode(station_id))+ Boolean.hashCode(takes_fleet_warp))+ Long.hashCode(wing_id));
    }
}
