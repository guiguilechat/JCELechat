package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_universe_stargates_stargate_id_destination {
    /**
     * The stargate this stargate connects to
     */
    public int stargate_id;
    /**
     * The solar system this stargate connects to
     */
    public int system_id;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_universe_stargates_stargate_id_destination othersame = ((R_get_universe_stargates_stargate_id_destination) other);
        if (stargate_id!= othersame.stargate_id) {
            return false;
        }
        if (system_id!= othersame.system_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (stargate_id + system_id);
    }
}
