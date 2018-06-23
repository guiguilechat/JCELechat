package fr.guiguilechat.eveonline.model.esi.compiled.responses;

public class R_get_fleets_fleet_id {
    /**
     * Is free-move enabled
     */
    public boolean is_free_move;
    /**
     * Does the fleet have an active fleet advertisement
     */
    public boolean is_registered;
    /**
     * Is EVE Voice enabled
     */
    public boolean is_voice_enabled;
    /**
     * Fleet MOTD in CCP flavoured HTML
     */
    public String motd;
}
