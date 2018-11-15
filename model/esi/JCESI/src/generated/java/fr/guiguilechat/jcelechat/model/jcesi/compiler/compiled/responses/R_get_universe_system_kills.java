package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_get_universe_system_kills {
    /**
     * Number of NPC ships killed in this system
     */
    public int npc_kills;
    /**
     * Number of pods killed in this system
     */
    public int pod_kills;
    /**
     * Number of player ships killed in this system
     */
    public int ship_kills;
    /**
     * system_id integer
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
        R_get_universe_system_kills othersame = ((R_get_universe_system_kills) other);
        if (npc_kills!= othersame.npc_kills) {
            return false;
        }
        if (pod_kills!= othersame.pod_kills) {
            return false;
        }
        if (ship_kills!= othersame.ship_kills) {
            return false;
        }
        if (system_id!= othersame.system_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((npc_kills + pod_kills)+ ship_kills)+ system_id);
    }
}
