package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_get_corporation_corporation_id_mining_observers_observer_id {
    /**
     * The character that did the mining
     * 
     */
    public int character_id;
    /**
     * last_updated string
     */
    public String last_updated;
    /**
     * quantity integer
     */
    public long quantity;
    /**
     * The corporation id of the character at the time data was recorded.
     * 
     */
    public int recorded_corporation_id;
    /**
     * type_id integer
     */
    public int type_id;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_corporation_corporation_id_mining_observers_observer_id othersame = ((R_get_corporation_corporation_id_mining_observers_observer_id) other);
        if (character_id!= othersame.character_id) {
            return false;
        }
        if ((last_updated!= othersame.last_updated)&&((last_updated == null)||(!last_updated.equals(othersame.last_updated)))) {
            return false;
        }
        if (quantity!= othersame.quantity) {
            return false;
        }
        if (recorded_corporation_id!= othersame.recorded_corporation_id) {
            return false;
        }
        if (type_id!= othersame.type_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((character_id +((last_updated == null)? 0 :last_updated.hashCode()))+ Long.hashCode(quantity))+ recorded_corporation_id)+ type_id);
    }
}
