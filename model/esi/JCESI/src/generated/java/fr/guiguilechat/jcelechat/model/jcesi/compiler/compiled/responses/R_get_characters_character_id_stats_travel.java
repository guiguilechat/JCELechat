package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_get_characters_character_id_stats_travel {
    /**
     * acceleration_gate_activations integer
     */
    public long acceleration_gate_activations;
    /**
     * align_to integer
     */
    public long align_to;
    /**
     * distance_warped_high_sec integer
     */
    public long distance_warped_high_sec;
    /**
     * distance_warped_low_sec integer
     */
    public long distance_warped_low_sec;
    /**
     * distance_warped_null_sec integer
     */
    public long distance_warped_null_sec;
    /**
     * distance_warped_wormhole integer
     */
    public long distance_warped_wormhole;
    /**
     * docks_high_sec integer
     */
    public long docks_high_sec;
    /**
     * docks_low_sec integer
     */
    public long docks_low_sec;
    /**
     * docks_null_sec integer
     */
    public long docks_null_sec;
    /**
     * jumps_stargate_high_sec integer
     */
    public long jumps_stargate_high_sec;
    /**
     * jumps_stargate_low_sec integer
     */
    public long jumps_stargate_low_sec;
    /**
     * jumps_stargate_null_sec integer
     */
    public long jumps_stargate_null_sec;
    /**
     * jumps_wormhole integer
     */
    public long jumps_wormhole;
    /**
     * warps_high_sec integer
     */
    public long warps_high_sec;
    /**
     * warps_low_sec integer
     */
    public long warps_low_sec;
    /**
     * warps_null_sec integer
     */
    public long warps_null_sec;
    /**
     * warps_to_bookmark integer
     */
    public long warps_to_bookmark;
    /**
     * warps_to_celestial integer
     */
    public long warps_to_celestial;
    /**
     * warps_to_fleet_member integer
     */
    public long warps_to_fleet_member;
    /**
     * warps_to_scan_result integer
     */
    public long warps_to_scan_result;
    /**
     * warps_wormhole integer
     */
    public long warps_wormhole;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_characters_character_id_stats_travel othersame = ((R_get_characters_character_id_stats_travel) other);
        if (acceleration_gate_activations!= othersame.acceleration_gate_activations) {
            return false;
        }
        if (align_to!= othersame.align_to) {
            return false;
        }
        if (distance_warped_high_sec!= othersame.distance_warped_high_sec) {
            return false;
        }
        if (distance_warped_low_sec!= othersame.distance_warped_low_sec) {
            return false;
        }
        if (distance_warped_null_sec!= othersame.distance_warped_null_sec) {
            return false;
        }
        if (distance_warped_wormhole!= othersame.distance_warped_wormhole) {
            return false;
        }
        if (docks_high_sec!= othersame.docks_high_sec) {
            return false;
        }
        if (docks_low_sec!= othersame.docks_low_sec) {
            return false;
        }
        if (docks_null_sec!= othersame.docks_null_sec) {
            return false;
        }
        if (jumps_stargate_high_sec!= othersame.jumps_stargate_high_sec) {
            return false;
        }
        if (jumps_stargate_low_sec!= othersame.jumps_stargate_low_sec) {
            return false;
        }
        if (jumps_stargate_null_sec!= othersame.jumps_stargate_null_sec) {
            return false;
        }
        if (jumps_wormhole!= othersame.jumps_wormhole) {
            return false;
        }
        if (warps_high_sec!= othersame.warps_high_sec) {
            return false;
        }
        if (warps_low_sec!= othersame.warps_low_sec) {
            return false;
        }
        if (warps_null_sec!= othersame.warps_null_sec) {
            return false;
        }
        if (warps_to_bookmark!= othersame.warps_to_bookmark) {
            return false;
        }
        if (warps_to_celestial!= othersame.warps_to_celestial) {
            return false;
        }
        if (warps_to_fleet_member!= othersame.warps_to_fleet_member) {
            return false;
        }
        if (warps_to_scan_result!= othersame.warps_to_scan_result) {
            return false;
        }
        if (warps_wormhole!= othersame.warps_wormhole) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((((((((((((((((Long.hashCode(acceleration_gate_activations)+ Long.hashCode(align_to))+ Long.hashCode(distance_warped_high_sec))+ Long.hashCode(distance_warped_low_sec))+ Long.hashCode(distance_warped_null_sec))+ Long.hashCode(distance_warped_wormhole))+ Long.hashCode(docks_high_sec))+ Long.hashCode(docks_low_sec))+ Long.hashCode(docks_null_sec))+ Long.hashCode(jumps_stargate_high_sec))+ Long.hashCode(jumps_stargate_low_sec))+ Long.hashCode(jumps_stargate_null_sec))+ Long.hashCode(jumps_wormhole))+ Long.hashCode(warps_high_sec))+ Long.hashCode(warps_low_sec))+ Long.hashCode(warps_null_sec))+ Long.hashCode(warps_to_bookmark))+ Long.hashCode(warps_to_celestial))+ Long.hashCode(warps_to_fleet_member))+ Long.hashCode(warps_to_scan_result))+ Long.hashCode(warps_wormhole));
    }
}
