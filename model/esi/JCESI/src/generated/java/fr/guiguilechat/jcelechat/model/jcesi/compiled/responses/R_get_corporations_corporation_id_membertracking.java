package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_corporations_corporation_id_membertracking {
    /**
     * base_id integer
     */
    public int base_id;
    /**
     * character_id integer
     */
    public int character_id;
    /**
     * location_id integer
     */
    public long location_id;
    /**
     * logoff_date string
     */
    public String logoff_date;
    /**
     * logon_date string
     */
    public String logon_date;
    /**
     * ship_type_id integer
     */
    public int ship_type_id;
    /**
     * start_date string
     */
    public String start_date;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_corporations_corporation_id_membertracking othersame = ((R_get_corporations_corporation_id_membertracking) other);
        if (base_id!= othersame.base_id) {
            return false;
        }
        if (character_id!= othersame.character_id) {
            return false;
        }
        if (location_id!= othersame.location_id) {
            return false;
        }
        if ((logoff_date!= othersame.logoff_date)&&((logoff_date == null)||(!logoff_date.equals(othersame.logoff_date)))) {
            return false;
        }
        if ((logon_date!= othersame.logon_date)&&((logon_date == null)||(!logon_date.equals(othersame.logon_date)))) {
            return false;
        }
        if (ship_type_id!= othersame.ship_type_id) {
            return false;
        }
        if ((start_date!= othersame.start_date)&&((start_date == null)||(!start_date.equals(othersame.start_date)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((base_id + character_id)+ Long.hashCode(location_id))+((logoff_date == null)? 0 :logoff_date.hashCode()))+((logon_date == null)? 0 :logon_date.hashCode()))+ ship_type_id)+((start_date == null)? 0 :start_date.hashCode()));
    }
}
