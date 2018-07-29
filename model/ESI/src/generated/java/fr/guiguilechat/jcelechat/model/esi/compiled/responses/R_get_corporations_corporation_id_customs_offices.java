package fr.guiguilechat.jcelechat.model.esi.compiled.responses;

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
}
