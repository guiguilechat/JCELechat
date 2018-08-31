package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_fw_wars {
    /**
     * The faction ID of the enemy faction.
     */
    public int against_id;
    /**
     * faction_id integer
     */
    public int faction_id;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_fw_wars othersame = ((R_get_fw_wars) other);
        if (against_id!= othersame.against_id) {
            return false;
        }
        if (faction_id!= othersame.faction_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (against_id + faction_id);
    }
}
