package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_universe_schematics_schematic_id {
    /**
     * Time in seconds to process a run
     */
    public int cycle_time;
    /**
     * schematic_name string
     */
    public String schematic_name;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_universe_schematics_schematic_id othersame = ((R_get_universe_schematics_schematic_id) other);
        if (cycle_time!= othersame.cycle_time) {
            return false;
        }
        if ((schematic_name!= othersame.schematic_name)&&((schematic_name == null)||(!schematic_name.equals(othersame.schematic_name)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (cycle_time +((schematic_name == null)? 0 :schematic_name.hashCode()));
    }
}
