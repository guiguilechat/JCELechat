package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_corporations_corporation_id_starbases {
    /**
     * The moon this starbase (POS) is anchored on, unanchored POSes do not have this information
     */
    public int moon_id;
    /**
     * When the POS onlined, for starbases (POSes) in online state
     */
    public String onlined_since;
    /**
     * When the POS will be out of reinforcement, for starbases (POSes) in reinforced state
     */
    public String reinforced_until;
    /**
     * Unique ID for this starbase (POS)
     */
    public long starbase_id;
    /**
     * state string
     */
    public String state;
    /**
     * The solar system this starbase (POS) is in, unanchored POSes have this information
     */
    public int system_id;
    /**
     * Starbase (POS) type
     */
    public int type_id;
    /**
     * When the POS started unanchoring, for starbases (POSes) in unanchoring state
     */
    public String unanchor_at;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_corporations_corporation_id_starbases othersame = ((R_get_corporations_corporation_id_starbases) other);
        if (moon_id!= othersame.moon_id) {
            return false;
        }
        if ((onlined_since!= othersame.onlined_since)&&((onlined_since == null)||(!onlined_since.equals(othersame.onlined_since)))) {
            return false;
        }
        if ((reinforced_until!= othersame.reinforced_until)&&((reinforced_until == null)||(!reinforced_until.equals(othersame.reinforced_until)))) {
            return false;
        }
        if (starbase_id!= othersame.starbase_id) {
            return false;
        }
        if ((state!= othersame.state)&&((state == null)||(!state.equals(othersame.state)))) {
            return false;
        }
        if (system_id!= othersame.system_id) {
            return false;
        }
        if (type_id!= othersame.type_id) {
            return false;
        }
        if ((unanchor_at!= othersame.unanchor_at)&&((unanchor_at == null)||(!unanchor_at.equals(othersame.unanchor_at)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((moon_id +((onlined_since == null)? 0 :onlined_since.hashCode()))+((reinforced_until == null)? 0 :reinforced_until.hashCode()))+ Long.hashCode(starbase_id))+((state == null)? 0 :state.hashCode()))+ system_id)+ type_id)+((unanchor_at == null)? 0 :unanchor_at.hashCode()));
    }
}
