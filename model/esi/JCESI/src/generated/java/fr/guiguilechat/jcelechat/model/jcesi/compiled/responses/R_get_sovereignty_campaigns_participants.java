package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_sovereignty_campaigns_participants {
    /**
     * alliance_id integer
     */
    public int alliance_id;
    /**
     * score number
     */
    public float score;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_sovereignty_campaigns_participants othersame = ((R_get_sovereignty_campaigns_participants) other);
        if (alliance_id!= othersame.alliance_id) {
            return false;
        }
        if (score!= othersame.score) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (alliance_id + Double.hashCode(score));
    }
}
