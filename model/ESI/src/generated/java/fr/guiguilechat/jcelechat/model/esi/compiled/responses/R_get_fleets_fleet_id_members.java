package fr.guiguilechat.jcelechat.model.esi.compiled.responses;

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
    public String role;
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
}
