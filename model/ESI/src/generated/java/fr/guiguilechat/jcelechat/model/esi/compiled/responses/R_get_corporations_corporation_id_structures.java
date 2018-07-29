package fr.guiguilechat.jcelechat.model.esi.compiled.responses;

public class R_get_corporations_corporation_id_structures {
    /**
     * ID of the corporation that owns the structure
     */
    public int corporation_id;
    /**
     * Date on which the structure will run out of fuel
     */
    public String fuel_expires;
    /**
     * The date and time when the structure's newly requested reinforcement times (e.g. next_reinforce_hour and next_reinforce_day) will take effect.
     */
    public String next_reinforce_apply;
    /**
     * The requested change to reinforce_hour that will take effect at the time shown by next_reinforce_apply.
     */
    public int next_reinforce_hour;
    /**
     * The requested change to reinforce_weekday that will take effect at the time shown by next_reinforce_apply.
     */
    public int next_reinforce_weekday;
    /**
     * The id of the ACL profile for this citadel
     */
    public int profile_id;
    /**
     * The hour of day that determines the four hour window when the structure will randomly exit its reinforcement periods and become vulnerable to attack against its armor and/or hull. The structure will become vulnerable at a random time that is +/- 2 hours centered on the value of this property.
     */
    public int reinforce_hour;
    /**
     * The day of the week when the structure exits its final reinforcement period and becomes vulnerable to attack against its hull. Monday is 0 and Sunday is 6.
     */
    public int reinforce_weekday;
    /**
     * Contains a list of service upgrades, and their state
     */
    public R_get_corporations_corporation_id_structures_services[] services;
    /**
     * state string
     */
    public String state;
    /**
     * Date at which the structure will move to it's next state
     */
    public String state_timer_end;
    /**
     * Date at which the structure entered it's current state
     */
    public String state_timer_start;
    /**
     * The Item ID of the structure
     */
    public long structure_id;
    /**
     * The solar system the structure is in
     */
    public int system_id;
    /**
     * The type id of the structure
     */
    public int type_id;
    /**
     * Date at which the structure will unanchor
     */
    public String unanchors_at;
}
