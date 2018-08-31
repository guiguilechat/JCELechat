package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

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

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_corporations_corporation_id_containers_logs othersame = ((R_get_corporations_corporation_id_containers_logs) other);
        if ((action!= othersame.action)&&((action == null)||(!action.equals(othersame.action)))) {
            return false;
        }
        if (character_id!= othersame.character_id) {
            return false;
        }
        if (container_id!= othersame.container_id) {
            return false;
        }
        if (container_type_id!= othersame.container_type_id) {
            return false;
        }
        if ((location_flag!= othersame.location_flag)&&((location_flag == null)||(!location_flag.equals(othersame.location_flag)))) {
            return false;
        }
        if (location_id!= othersame.location_id) {
            return false;
        }
        if ((logged_at!= othersame.logged_at)&&((logged_at == null)||(!logged_at.equals(othersame.logged_at)))) {
            return false;
        }
        if (new_config_bitmask!= othersame.new_config_bitmask) {
            return false;
        }
        if (old_config_bitmask!= othersame.old_config_bitmask) {
            return false;
        }
        if ((password_type!= othersame.password_type)&&((password_type == null)||(!password_type.equals(othersame.password_type)))) {
            return false;
        }
        if (quantity!= othersame.quantity) {
            return false;
        }
        if (type_id!= othersame.type_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((((((((action == null)? 0 :action.hashCode())+ character_id)+ Long.hashCode(container_id))+ container_type_id)+((location_flag == null)? 0 :location_flag.hashCode()))+ Long.hashCode(location_id))+((logged_at == null)? 0 :logged_at.hashCode()))+ new_config_bitmask)+ old_config_bitmask)+((password_type == null)? 0 :password_type.hashCode()))+ quantity)+ type_id);
    }
}
