package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_titles_grantable_roles;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_titles_grantable_roles_at_base;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_titles_grantable_roles_at_hq;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_titles_grantable_roles_at_other;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_titles_roles;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_titles_roles_at_base;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_titles_roles_at_hq;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_titles_roles_at_other;

public class R_get_corporations_corporation_id_titles {
    /**
     * grantable_roles array
     */
    public get_corporations_corporation_id_titles_grantable_roles[] grantable_roles;
    /**
     * grantable_roles_at_base array
     */
    public get_corporations_corporation_id_titles_grantable_roles_at_base[] grantable_roles_at_base;
    /**
     * grantable_roles_at_hq array
     */
    public get_corporations_corporation_id_titles_grantable_roles_at_hq[] grantable_roles_at_hq;
    /**
     * grantable_roles_at_other array
     */
    public get_corporations_corporation_id_titles_grantable_roles_at_other[] grantable_roles_at_other;
    /**
     * name string
     */
    public String name;
    /**
     * roles array
     */
    public get_corporations_corporation_id_titles_roles[] roles;
    /**
     * roles_at_base array
     */
    public get_corporations_corporation_id_titles_roles_at_base[] roles_at_base;
    /**
     * roles_at_hq array
     */
    public get_corporations_corporation_id_titles_roles_at_hq[] roles_at_hq;
    /**
     * roles_at_other array
     */
    public get_corporations_corporation_id_titles_roles_at_other[] roles_at_other;
    /**
     * title_id integer
     */
    public int title_id;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_corporations_corporation_id_titles othersame = ((R_get_corporations_corporation_id_titles) other);
        if ((grantable_roles!= othersame.grantable_roles)&&((grantable_roles == null)||(!grantable_roles.equals(othersame.grantable_roles)))) {
            return false;
        }
        if ((grantable_roles_at_base!= othersame.grantable_roles_at_base)&&((grantable_roles_at_base == null)||(!grantable_roles_at_base.equals(othersame.grantable_roles_at_base)))) {
            return false;
        }
        if ((grantable_roles_at_hq!= othersame.grantable_roles_at_hq)&&((grantable_roles_at_hq == null)||(!grantable_roles_at_hq.equals(othersame.grantable_roles_at_hq)))) {
            return false;
        }
        if ((grantable_roles_at_other!= othersame.grantable_roles_at_other)&&((grantable_roles_at_other == null)||(!grantable_roles_at_other.equals(othersame.grantable_roles_at_other)))) {
            return false;
        }
        if ((name!= othersame.name)&&((name == null)||(!name.equals(othersame.name)))) {
            return false;
        }
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
        if (title_id!= othersame.title_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((((((grantable_roles == null)? 0 :grantable_roles.hashCode())+((grantable_roles_at_base == null)? 0 :grantable_roles_at_base.hashCode()))+((grantable_roles_at_hq == null)? 0 :grantable_roles_at_hq.hashCode()))+((grantable_roles_at_other == null)? 0 :grantable_roles_at_other.hashCode()))+((name == null)? 0 :name.hashCode()))+((roles == null)? 0 :roles.hashCode()))+((roles_at_base == null)? 0 :roles_at_base.hashCode()))+((roles_at_hq == null)? 0 :roles_at_hq.hashCode()))+((roles_at_other == null)? 0 :roles_at_other.hashCode()))+ title_id);
    }
}
