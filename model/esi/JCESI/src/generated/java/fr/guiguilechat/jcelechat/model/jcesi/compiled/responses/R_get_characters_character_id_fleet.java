package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_characters_character_id_fleet {
    /**
     * The character's current fleet ID
     */
    public long fleet_id;
    /**
     * Member’s role in fleet
     */
    public String role;
    /**
     * ID of the squad the member is in. If not applicable, will be set to -1
     */
    public long squad_id;
    /**
     * ID of the wing the member is in. If not applicable, will be set to -1
     */
    public long wing_id;
}
