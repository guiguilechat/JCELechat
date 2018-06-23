package fr.guiguilechat.eveonline.model.esi.compiled.responses;

public class R_get_corporations_corporation_id_starbases_starbase_id {
    /**
     * allow_alliance_members boolean
     */
    public boolean allow_alliance_members;
    /**
     * allow_corporation_members boolean
     */
    public boolean allow_corporation_members;
    /**
     * Who can anchor starbase (POS) and its structures
     */
    public String anchor;
    /**
     * attack_if_at_war boolean
     */
    public boolean attack_if_at_war;
    /**
     * attack_if_other_security_status_dropping boolean
     */
    public boolean attack_if_other_security_status_dropping;
    /**
     * Starbase (POS) will attack if target's security standing is lower than this value
     */
    public float attack_security_status_threshold;
    /**
     * Starbase (POS) will attack if target's standing is lower than this value
     */
    public float attack_standing_threshold;
    /**
     * Who can take fuel blocks out of the starbase (POS)'s fuel bay
     */
    public String fuel_bay_take;
    /**
     * Who can view the starbase (POS)'s fule bay. Characters either need to have required role or belong to the starbase (POS) owner's corporation or alliance, as described by the enum, all other access settings follows the same scheme
     */
    public String fuel_bay_view;
    /**
     * Fuel blocks and other things that will be consumed when operating a starbase (POS)
     */
    public M_get_corporation_2 [] fuels;
    /**
     * Who can offline starbase (POS) and its structures
     */
    public String offline;
    /**
     * Who can online starbase (POS) and its structures
     */
    public String online;
    /**
     * Who can unanchor starbase (POS) and its structures
     */
    public String unanchor;
    /**
     * True if the starbase (POS) is using alliance standings, otherwise using corporation's
     */
    public boolean use_alliance_standings;
}
