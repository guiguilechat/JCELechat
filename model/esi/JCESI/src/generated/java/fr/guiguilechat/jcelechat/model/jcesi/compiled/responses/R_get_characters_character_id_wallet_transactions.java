package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_characters_character_id_wallet_transactions {
    /**
     * client_id integer
     */
    public int client_id;
    /**
     * Date and time of transaction
     */
    public String date;
    /**
     * is_buy boolean
     */
    public boolean is_buy;
    /**
     * is_personal boolean
     */
    public boolean is_personal;
    /**
     * journal_ref_id integer
     */
    public long journal_ref_id;
    /**
     * location_id integer
     */
    public long location_id;
    /**
     * quantity integer
     */
    public int quantity;
    /**
     * Unique transaction ID
     */
    public long transaction_id;
    /**
     * type_id integer
     */
    public int type_id;
    /**
     * Amount paid per unit
     */
    public double unit_price;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_characters_character_id_wallet_transactions othersame = ((R_get_characters_character_id_wallet_transactions) other);
        if (client_id!= othersame.client_id) {
            return false;
        }
        if ((date!= othersame.date)&&((date == null)||(!date.equals(othersame.date)))) {
            return false;
        }
        if (is_buy!= othersame.is_buy) {
            return false;
        }
        if (is_personal!= othersame.is_personal) {
            return false;
        }
        if (journal_ref_id!= othersame.journal_ref_id) {
            return false;
        }
        if (location_id!= othersame.location_id) {
            return false;
        }
        if (quantity!= othersame.quantity) {
            return false;
        }
        if (transaction_id!= othersame.transaction_id) {
            return false;
        }
        if (type_id!= othersame.type_id) {
            return false;
        }
        if (unit_price!= othersame.unit_price) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((((client_id +((date == null)? 0 :date.hashCode()))+ Boolean.hashCode(is_buy))+ Boolean.hashCode(is_personal))+ Long.hashCode(journal_ref_id))+ Long.hashCode(location_id))+ quantity)+ Long.hashCode(transaction_id))+ type_id)+ Double.hashCode(unit_price));
    }
}
