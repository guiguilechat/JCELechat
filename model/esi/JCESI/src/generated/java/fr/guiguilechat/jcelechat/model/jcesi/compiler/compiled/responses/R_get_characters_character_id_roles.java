package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_roles_roles;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_roles_roles_at_base;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_roles_roles_at_hq;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_roles_roles_at_other;

public class R_get_characters_character_id_roles {
    /**
     * roles array
     */
    public get_characters_character_id_roles_roles[] roles;
    /**
     * roles_at_base array
     */
    public get_characters_character_id_roles_roles_at_base[] roles_at_base;
    /**
     * roles_at_hq array
     */
    public get_characters_character_id_roles_roles_at_hq[] roles_at_hq;
    /**
     * roles_at_other array
     */
    public get_characters_character_id_roles_roles_at_other[] roles_at_other;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_characters_character_id_roles othersame = ((R_get_characters_character_id_roles) other);
        if ((roles!= othersame.roles)&&((roles == null)||(!roles.equals(othersame.roles)))) {
            return false;
        }
        if ((roles_at_base!= othersame.roles_at_base)&&((roles_at_base == null)||(!roles_at_base.equals(othersame.roles_at_base)))) {
            return false;
        }
        if ((roles_at_hq!= othersame.roles_at_hq)&&((roles_at_hq == null)||(!roles_at_hq.equals(othersame.roles_at_hq)))) {
            return false;
        }
        if ((roles_at_other!= othersame.roles_at_other)&&((roles_at_other == null)||(!roles_at_other.equals(othersame.roles_at_other)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((roles == null)? 0 :roles.hashCode())+((roles_at_base == null)? 0 :roles_at_base.hashCode()))+((roles_at_hq == null)? 0 :roles_at_hq.hashCode()))+((roles_at_other == null)? 0 :roles_at_other.hashCode()));
    }
}
