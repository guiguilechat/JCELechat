package fr.guiguilechat.eveonline.model.esi.compiled.responses;

public class R_get_killmails_killmail_id_killmail_hash_attackers {
    /**
     * alliance_id integer
     */
    public int alliance_id;
    /**
     * character_id integer
     */
    public int character_id;
    /**
     * corporation_id integer
     */
    public int corporation_id;
    /**
     * damage_done integer
     */
    public int damage_done;
    /**
     * faction_id integer
     */
    public int faction_id;
    /**
     * Was the attacker the one to achieve the final blow
     * 
     */
    public boolean final_blow;
    /**
     * Security status for the attacker
     * 
     */
    public float security_status;
    /**
     * What ship was the attacker flying
     * 
     */
    public int ship_type_id;
    /**
     * What weapon was used by the attacker for the kill
     * 
     */
    public int weapon_type_id;
}
