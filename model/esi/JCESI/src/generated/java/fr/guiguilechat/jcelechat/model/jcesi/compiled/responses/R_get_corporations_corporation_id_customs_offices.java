package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_corporations_corporation_id_customs_offices {
    /**
     * Only present if alliance access is allowed
     */
    public float alliance_tax_rate;
    /**
     * standing_level and any standing related tax rate only present when this is true
     */
    public boolean allow_access_with_standings;
    /**
     * allow_alliance_access boolean
     */
    public boolean allow_alliance_access;
    /**
     * bad_standing_tax_rate number
     */
    public float bad_standing_tax_rate;
    /**
     * corporation_tax_rate number
     */
    public float corporation_tax_rate;
    /**
     * Tax rate for entities with excellent level of standing, only present if this level is allowed, same for all other standing related tax rates
     */
    public float excellent_standing_tax_rate;
    /**
     * good_standing_tax_rate number
     */
    public float good_standing_tax_rate;
    /**
     * neutral_standing_tax_rate number
     */
    public float neutral_standing_tax_rate;
    /**
     * unique ID of this customs office
     */
    public long office_id;
    /**
     * reinforce_exit_end integer
     */
    public int reinforce_exit_end;
    /**
     * Together with reinforce_exit_end, marks a 2-hour period where this customs office could exit reinforcement mode during the day after initial attack
     */
    public int reinforce_exit_start;
    /**
     * Access is allowed only for entities with this level of standing or better
     */
    public String standing_level;
    /**
     * ID of the solar system this customs office is located in
     */
    public int system_id;
    /**
     * terrible_standing_tax_rate number
     */
    public float terrible_standing_tax_rate;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_corporations_corporation_id_customs_offices othersame = ((R_get_corporations_corporation_id_customs_offices) other);
        if (alliance_tax_rate!= othersame.alliance_tax_rate) {
            return false;
        }
        if (allow_access_with_standings!= othersame.allow_access_with_standings) {
            return false;
        }
        if (allow_alliance_access!= othersame.allow_alliance_access) {
            return false;
        }
        if (bad_standing_tax_rate!= othersame.bad_standing_tax_rate) {
            return false;
        }
        if (corporation_tax_rate!= othersame.corporation_tax_rate) {
            return false;
        }
        if (excellent_standing_tax_rate!= othersame.excellent_standing_tax_rate) {
            return false;
        }
        if (good_standing_tax_rate!= othersame.good_standing_tax_rate) {
            return false;
        }
        if (neutral_standing_tax_rate!= othersame.neutral_standing_tax_rate) {
            return false;
        }
        if (office_id!= othersame.office_id) {
            return false;
        }
        if (reinforce_exit_end!= othersame.reinforce_exit_end) {
            return false;
        }
        if (reinforce_exit_start!= othersame.reinforce_exit_start) {
            return false;
        }
        if ((standing_level!= othersame.standing_level)&&((standing_level == null)||(!standing_level.equals(othersame.standing_level)))) {
            return false;
        }
        if (system_id!= othersame.system_id) {
            return false;
        }
        if (terrible_standing_tax_rate!= othersame.terrible_standing_tax_rate) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((((((((Double.hashCode(alliance_tax_rate)+ Boolean.hashCode(allow_access_with_standings))+ Boolean.hashCode(allow_alliance_access))+ Double.hashCode(bad_standing_tax_rate))+ Double.hashCode(corporation_tax_rate))+ Double.hashCode(excellent_standing_tax_rate))+ Double.hashCode(good_standing_tax_rate))+ Double.hashCode(neutral_standing_tax_rate))+ Long.hashCode(office_id))+ reinforce_exit_end)+ reinforce_exit_start)+((standing_level == null)? 0 :standing_level.hashCode()))+ system_id)+ Double.hashCode(terrible_standing_tax_rate));
    }
}
