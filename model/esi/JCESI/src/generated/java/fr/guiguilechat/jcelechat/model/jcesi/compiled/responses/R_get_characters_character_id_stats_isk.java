package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_characters_character_id_stats_isk {
    /**
     * in integer
     */
    public long in;
    /**
     * out integer
     */
    public long out;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_characters_character_id_stats_isk othersame = ((R_get_characters_character_id_stats_isk) other);
        if (in!= othersame.in) {
            return false;
        }
        if (out!= othersame.out) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (Long.hashCode(in)+ Long.hashCode(out));
    }
}
