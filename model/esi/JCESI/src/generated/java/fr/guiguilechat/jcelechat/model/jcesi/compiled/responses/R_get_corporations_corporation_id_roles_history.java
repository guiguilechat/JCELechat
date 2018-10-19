package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

import fr.guiguilechat.jcelechat.model.jcesi.compiled.structures.get_corporations_corporation_id_roles_history_new_roles;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.structures.get_corporations_corporation_id_roles_history_old_roles;
import fr.guiguilechat.jcelechat.model.jcesi.compiled.structures.get_corporations_corporation_id_roles_history_role_type;

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
    public get_corporations_corporation_id_roles_history_new_roles[] new_roles;
    /**
     * old_roles array
     */
    public get_corporations_corporation_id_roles_history_old_roles[] old_roles;
    /**
     * role_type string
     */
    public get_corporations_corporation_id_roles_history_role_type role_type;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_corporations_corporation_id_roles_history othersame = ((R_get_corporations_corporation_id_roles_history) other);
        if ((changed_at!= othersame.changed_at)&&((changed_at == null)||(!changed_at.equals(othersame.changed_at)))) {
            return false;
        }
        if (character_id!= othersame.character_id) {
            return false;
        }
        if (issuer_id!= othersame.issuer_id) {
            return false;
        }
        if ((new_roles!= othersame.new_roles)&&((new_roles == null)||(!new_roles.equals(othersame.new_roles)))) {
            return false;
        }
        if ((old_roles!= othersame.old_roles)&&((old_roles == null)||(!old_roles.equals(othersame.old_roles)))) {
            return false;
        }
        if ((role_type!= othersame.role_type)&&((role_type == null)||(!role_type.equals(othersame.role_type)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((changed_at == null)? 0 :changed_at.hashCode())+ character_id)+ issuer_id)+((new_roles == null)? 0 :new_roles.hashCode()))+((old_roles == null)? 0 :old_roles.hashCode()))+((role_type == null)? 0 :role_type.hashCode()));
    }
}
