package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_sovereignty_structures {
    /**
     * The alliance that owns the structure.
     * 
     */
    public int alliance_id;
    /**
     * Solar system in which the structure is located.
     * 
     */
    public int solar_system_id;
    /**
     * Unique item ID for this structure.
     */
    public long structure_id;
    /**
     * A reference to the type of structure this is.
     * 
     */
    public int structure_type_id;
    /**
     * The occupancy level for the next or current vulnerability window. This takes into account all development indexes and capital system bonuses. Also known as Activity Defense Multiplier from in the client. It increases the time that attackers must spend using their entosis links on the structure.
     * 
     */
    public float vulnerability_occupancy_level;
    /**
     * The time at which the next or current vulnerability window ends. At the end of a vulnerability window the next window is recalculated and locked in along with the vulnerabilityOccupancyLevel. If the structure is not in 100% entosis control of the defender, it will go in to 'overtime' and stay vulnerable for as long as that situation persists. Only once the defenders have 100% entosis control and has the vulnerableEndTime passed does the vulnerability interval expire and a new one is calculated.
     * 
     */
    public String vulnerable_end_time;
    /**
     * The next time at which the structure will become vulnerable. Or the start time of the current window if current time is between this and vulnerableEndTime.
     * 
     */
    public String vulnerable_start_time;
}
