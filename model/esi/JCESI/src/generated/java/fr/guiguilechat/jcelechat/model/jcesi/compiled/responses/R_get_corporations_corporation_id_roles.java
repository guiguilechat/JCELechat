package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_corporations_corporation_id_roles {
    /**
     * character_id integer
     */
    public int character_id;
    /**
     * grantable_roles array
     */
    public String[] grantable_roles;
    /**
     * grantable_roles_at_base array
     */
    public String[] grantable_roles_at_base;
    /**
     * grantable_roles_at_hq array
     */
    public String[] grantable_roles_at_hq;
    /**
     * grantable_roles_at_other array
     */
    public String[] grantable_roles_at_other;
    /**
     * roles array
     */
    public String[] roles;
    /**
     * roles_at_base array
     */
    public String[] roles_at_base;
    /**
     * roles_at_hq array
     */
    public String[] roles_at_hq;
    /**
     * roles_at_other array
     */
    public String[] roles_at_other;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_corporations_corporation_id_roles othersame = ((R_get_corporations_corporation_id_roles) other);
        if (character_id!= othersame.character_id) {
            return false;
        }
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
        return ((((((((character_id +((grantable_roles == null)? 0 :grantable_roles.hashCode()))+((grantable_roles_at_base == null)? 0 :grantable_roles_at_base.hashCode()))+((grantable_roles_at_hq == null)? 0 :grantable_roles_at_hq.hashCode()))+((grantable_roles_at_other == null)? 0 :grantable_roles_at_other.hashCode()))+((roles == null)? 0 :roles.hashCode()))+((roles_at_base == null)? 0 :roles_at_base.hashCode()))+((roles_at_hq == null)? 0 :roles_at_hq.hashCode()))+((roles_at_other == null)? 0 :roles_at_other.hashCode()));
    }
}
