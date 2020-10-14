package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_get_characters_character_id_stats_pve {
    /**
     * dungeons_completed_agent integer
     */
    public long dungeons_completed_agent;
    /**
     * dungeons_completed_distribution integer
     */
    public long dungeons_completed_distribution;
    /**
     * missions_succeeded integer
     */
    public long missions_succeeded;
    /**
     * missions_succeeded_epic_arc integer
     */
    public long missions_succeeded_epic_arc;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_characters_character_id_stats_pve othersame = ((R_get_characters_character_id_stats_pve) other);
        if (dungeons_completed_agent!= othersame.dungeons_completed_agent) {
            return false;
        }
        if (dungeons_completed_distribution!= othersame.dungeons_completed_distribution) {
            return false;
        }
        if (missions_succeeded!= othersame.missions_succeeded) {
            return false;
        }
        if (missions_succeeded_epic_arc!= othersame.missions_succeeded_epic_arc) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((Long.hashCode(dungeons_completed_agent)+ Long.hashCode(dungeons_completed_distribution))+ Long.hashCode(missions_succeeded))+ Long.hashCode(missions_succeeded_epic_arc));
    }
}
