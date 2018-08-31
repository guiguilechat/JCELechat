package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_characters_character_id_corporationhistory {
    /**
     * corporation_id integer
     */
    public int corporation_id;
    /**
     * True if the corporation has been deleted
     */
    public boolean is_deleted;
    /**
     * An incrementing ID that can be used to canonically establish order of records in cases where dates may be ambiguous
     */
    public int record_id;
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
        R_get_characters_character_id_corporationhistory othersame = ((R_get_characters_character_id_corporationhistory) other);
        if (corporation_id!= othersame.corporation_id) {
            return false;
        }
        if (is_deleted!= othersame.is_deleted) {
            return false;
        }
        if (record_id!= othersame.record_id) {
            return false;
        }
        if ((start_date!= othersame.start_date)&&((start_date == null)||(!start_date.equals(othersame.start_date)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((corporation_id + Boolean.hashCode(is_deleted))+ record_id)+((start_date == null)? 0 :start_date.hashCode()));
    }
}
