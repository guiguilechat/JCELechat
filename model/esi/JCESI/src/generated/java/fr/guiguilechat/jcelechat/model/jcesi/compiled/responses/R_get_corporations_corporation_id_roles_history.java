package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_corporations_corporation_id_roles_history {
    /**
     * changed_at string
     */
    public String changed_at;
    /**
     * The character whose roles are changed
     */
    public int character_id;
    /**
     * ID of the character who issued this change
     */
    public int issuer_id;
    /**
     * new_roles array
     */
    public String[] new_roles;
    /**
     * old_roles array
     */
    public String[] old_roles;
    /**
     * role_type string
     */
    public String role_type;
}
