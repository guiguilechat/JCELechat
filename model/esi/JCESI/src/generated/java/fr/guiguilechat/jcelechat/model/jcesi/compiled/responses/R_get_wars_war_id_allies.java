package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_wars_war_id_allies {
    /**
     * Alliance ID if and only if this ally is an alliance
     */
    public int alliance_id;
    /**
     * Corporation ID if and only if this ally is a corporation
     */
    public int corporation_id;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_wars_war_id_allies othersame = ((R_get_wars_war_id_allies) other);
        if (alliance_id!= othersame.alliance_id) {
            return false;
        }
        if (corporation_id!= othersame.corporation_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (alliance_id + corporation_id);
    }
}
