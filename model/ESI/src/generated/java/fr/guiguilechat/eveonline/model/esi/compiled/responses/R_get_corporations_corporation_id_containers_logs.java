package fr.guiguilechat.eveonline.model.esi.compiled.responses;

public class R_get_corporations_corporation_id_containers_logs {
    /**
     * action string
     */
    public String action;
    /**
     * ID of the character who performed the action.
     */
    public int character_id;
    /**
     * ID of the container
     */
    public long container_id;
    /**
     * Type ID of the container
     */
    public int container_type_id;
    /**
     * location_flag string
     */
    public String location_flag;
    /**
     * location_id integer
     */
    public long location_id;
    /**
     * Timestamp when this log was created
     */
    public String logged_at;
    /**
     * new_config_bitmask integer
     */
    public int new_config_bitmask;
    /**
     * old_config_bitmask integer
     */
    public int old_config_bitmask;
    /**
     * Type of password set if action is of type SetPassword or EnterPassword
     */
    public String password_type;
    /**
     * Quantity of the item being acted upon
     */
    public int quantity;
    /**
     * Type ID of the item being acted upon
     */
    public int type_id;
}
