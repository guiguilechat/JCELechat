package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

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

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_corporations_corporation_id_starbases_starbase_id othersame = ((R_get_corporations_corporation_id_starbases_starbase_id) other);
        if (allow_alliance_members!= othersame.allow_alliance_members) {
            return false;
        }
        if (allow_corporation_members!= othersame.allow_corporation_members) {
            return false;
        }
        if ((anchor!= othersame.anchor)&&((anchor == null)||(!anchor.equals(othersame.anchor)))) {
            return false;
        }
        if (attack_if_at_war!= othersame.attack_if_at_war) {
            return false;
        }
        if (attack_if_other_security_status_dropping!= othersame.attack_if_other_security_status_dropping) {
            return false;
        }
        if (attack_security_status_threshold!= othersame.attack_security_status_threshold) {
            return false;
        }
        if (attack_standing_threshold!= othersame.attack_standing_threshold) {
            return false;
        }
        if ((fuel_bay_take!= othersame.fuel_bay_take)&&((fuel_bay_take == null)||(!fuel_bay_take.equals(othersame.fuel_bay_take)))) {
            return false;
        }
        if ((fuel_bay_view!= othersame.fuel_bay_view)&&((fuel_bay_view == null)||(!fuel_bay_view.equals(othersame.fuel_bay_view)))) {
            return false;
        }
        if ((fuels!= othersame.fuels)&&((fuels == null)||(!fuels.equals(othersame.fuels)))) {
            return false;
        }
        if ((offline!= othersame.offline)&&((offline == null)||(!offline.equals(othersame.offline)))) {
            return false;
        }
        if ((online!= othersame.online)&&((online == null)||(!online.equals(othersame.online)))) {
            return false;
        }
        if ((unanchor!= othersame.unanchor)&&((unanchor == null)||(!unanchor.equals(othersame.unanchor)))) {
            return false;
        }
        if (use_alliance_standings!= othersame.use_alliance_standings) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((((((((Boolean.hashCode(allow_alliance_members)+ Boolean.hashCode(allow_corporation_members))+((anchor == null)? 0 :anchor.hashCode()))+ Boolean.hashCode(attack_if_at_war))+ Boolean.hashCode(attack_if_other_security_status_dropping))+ Double.hashCode(attack_security_status_threshold))+ Double.hashCode(attack_standing_threshold))+((fuel_bay_take == null)? 0 :fuel_bay_take.hashCode()))+((fuel_bay_view == null)? 0 :fuel_bay_view.hashCode()))+((fuels == null)? 0 :fuels.hashCode()))+((offline == null)? 0 :offline.hashCode()))+((online == null)? 0 :online.hashCode()))+((unanchor == null)? 0 :unanchor.hashCode()))+ Boolean.hashCode(use_alliance_standings));
    }
}
