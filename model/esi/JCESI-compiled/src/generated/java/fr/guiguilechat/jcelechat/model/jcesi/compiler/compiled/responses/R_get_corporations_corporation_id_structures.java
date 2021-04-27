package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_structures_state;

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
     * The structure name
     */
    public String name;
    /**
     * The date and time when the structure's newly requested reinforcement times (e.g. next_reinforce_hour and next_reinforce_day) will take effect
     */
    public String next_reinforce_apply;
    /**
     * The requested change to reinforce_hour that will take effect at the time shown by next_reinforce_apply
     */
    public int next_reinforce_hour;
    /**
     * The id of the ACL profile for this citadel
     */
    public int profile_id;
    /**
     * The hour of day that determines the four hour window when the structure will randomly exit its reinforcement periods and become vulnerable to attack against its armor and/or hull. The structure will become vulnerable at a random time that is +/- 2 hours centered on the value of this property
     */
    public int reinforce_hour;
    /**
     * Contains a list of service upgrades, and their state
     */
    public R_get_corporations_corporation_id_structures_services[] services;
    /**
     * state string
     */
    public get_corporations_corporation_id_structures_state state;
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

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_corporations_corporation_id_structures othersame = ((R_get_corporations_corporation_id_structures) other);
        if (corporation_id!= othersame.corporation_id) {
            return false;
        }
        if ((fuel_expires!= othersame.fuel_expires)&&((fuel_expires == null)||(!fuel_expires.equals(othersame.fuel_expires)))) {
            return false;
        }
        if ((name!= othersame.name)&&((name == null)||(!name.equals(othersame.name)))) {
            return false;
        }
        if ((next_reinforce_apply!= othersame.next_reinforce_apply)&&((next_reinforce_apply == null)||(!next_reinforce_apply.equals(othersame.next_reinforce_apply)))) {
            return false;
        }
        if (next_reinforce_hour!= othersame.next_reinforce_hour) {
            return false;
        }
        if (profile_id!= othersame.profile_id) {
            return false;
        }
        if (reinforce_hour!= othersame.reinforce_hour) {
            return false;
        }
        if ((services!= othersame.services)&&((services == null)||(!services.equals(othersame.services)))) {
            return false;
        }
        if ((state!= othersame.state)&&((state == null)||(!state.equals(othersame.state)))) {
            return false;
        }
        if ((state_timer_end!= othersame.state_timer_end)&&((state_timer_end == null)||(!state_timer_end.equals(othersame.state_timer_end)))) {
            return false;
        }
        if ((state_timer_start!= othersame.state_timer_start)&&((state_timer_start == null)||(!state_timer_start.equals(othersame.state_timer_start)))) {
            return false;
        }
        if (structure_id!= othersame.structure_id) {
            return false;
        }
        if (system_id!= othersame.system_id) {
            return false;
        }
        if (type_id!= othersame.type_id) {
            return false;
        }
        if ((unanchors_at!= othersame.unanchors_at)&&((unanchors_at == null)||(!unanchors_at.equals(othersame.unanchors_at)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((((((((((corporation_id +((fuel_expires == null)? 0 :fuel_expires.hashCode()))+((name == null)? 0 :name.hashCode()))+((next_reinforce_apply == null)? 0 :next_reinforce_apply.hashCode()))+ next_reinforce_hour)+ profile_id)+ reinforce_hour)+((services == null)? 0 :services.hashCode()))+((state == null)? 0 :state.hashCode()))+((state_timer_end == null)? 0 :state_timer_end.hashCode()))+((state_timer_start == null)? 0 :state_timer_start.hashCode()))+ Long.hashCode(structure_id))+ system_id)+ type_id)+((unanchors_at == null)? 0 :unanchors_at.hashCode()));
    }
}
