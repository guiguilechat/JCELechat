package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_contracts_availability;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_contracts_status;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_contracts_type;

public class M_get_contracts_22 {
    /**
     * Who will accept the contract
     */
    public int acceptor_id;
    /**
     * ID to whom the contract is assigned, can be alliance, corporation or character ID
     */
    public int assignee_id;
    /**
     * To whom the contract is available
     */
    public get_characters_character_id_contracts_availability availability;
    /**
     * Buyout price (for Auctions only)
     */
    public double buyout;
    /**
     * Collateral price (for Couriers only)
     */
    public double collateral;
    /**
     * contract_id integer
     */
    public int contract_id;
    /**
     * Date of confirmation of contract
     */
    public String date_accepted;
    /**
     * Date of completed of contract
     */
    public String date_completed;
    /**
     * Expiration date of the contract
     */
    public String date_expired;
    /**
     * Сreation date of the contract
     */
    public String date_issued;
    /**
     * Number of days to perform the contract
     */
    public int days_to_complete;
    /**
     * End location ID (for Couriers contract)
     */
    public long end_location_id;
    /**
     * true if the contract was issued on behalf of the issuer's corporation
     */
    public boolean for_corporation;
    /**
     * Character's corporation ID for the issuer
     */
    public int issuer_corporation_id;
    /**
     * Character ID for the issuer
     */
    public int issuer_id;
    /**
     * Price of contract (for ItemsExchange and Auctions)
     */
    public double price;
    /**
     * Remuneration for contract (for Couriers only)
     */
    public double reward;
    /**
     * Start location ID (for Couriers contract)
     */
    public long start_location_id;
    /**
     * Status of the the contract
     */
    public get_characters_character_id_contracts_status status;
    /**
     * Title of the contract
     */
    public String title;
    /**
     * Type of the contract
     */
    public get_characters_character_id_contracts_type type;
    /**
     * Volume of items in the contract
     */
    public double volume;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        M_get_contracts_22 othersame = ((M_get_contracts_22) other);
        if (acceptor_id!= othersame.acceptor_id) {
            return false;
        }
        if (assignee_id!= othersame.assignee_id) {
            return false;
        }
        if ((availability!= othersame.availability)&&((availability == null)||(!availability.equals(othersame.availability)))) {
            return false;
        }
        if (buyout!= othersame.buyout) {
            return false;
        }
        if (collateral!= othersame.collateral) {
            return false;
        }
        if (contract_id!= othersame.contract_id) {
            return false;
        }
        if ((date_accepted!= othersame.date_accepted)&&((date_accepted == null)||(!date_accepted.equals(othersame.date_accepted)))) {
            return false;
        }
        if ((date_completed!= othersame.date_completed)&&((date_completed == null)||(!date_completed.equals(othersame.date_completed)))) {
            return false;
        }
        if ((date_expired!= othersame.date_expired)&&((date_expired == null)||(!date_expired.equals(othersame.date_expired)))) {
            return false;
        }
        if ((date_issued!= othersame.date_issued)&&((date_issued == null)||(!date_issued.equals(othersame.date_issued)))) {
            return false;
        }
        if (days_to_complete!= othersame.days_to_complete) {
            return false;
        }
        if (end_location_id!= othersame.end_location_id) {
            return false;
        }
        if (for_corporation!= othersame.for_corporation) {
            return false;
        }
        if (issuer_corporation_id!= othersame.issuer_corporation_id) {
            return false;
        }
        if (issuer_id!= othersame.issuer_id) {
            return false;
        }
        if (price!= othersame.price) {
            return false;
        }
        if (reward!= othersame.reward) {
            return false;
        }
        if (start_location_id!= othersame.start_location_id) {
            return false;
        }
        if ((status!= othersame.status)&&((status == null)||(!status.equals(othersame.status)))) {
            return false;
        }
        if ((title!= othersame.title)&&((title == null)||(!title.equals(othersame.title)))) {
            return false;
        }
        if ((type!= othersame.type)&&((type == null)||(!type.equals(othersame.type)))) {
            return false;
        }
        if (volume!= othersame.volume) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((((((((((((((((acceptor_id + assignee_id)+((availability == null)? 0 :availability.hashCode()))+ Double.hashCode(buyout))+ Double.hashCode(collateral))+ contract_id)+((date_accepted == null)? 0 :date_accepted.hashCode()))+((date_completed == null)? 0 :date_completed.hashCode()))+((date_expired == null)? 0 :date_expired.hashCode()))+((date_issued == null)? 0 :date_issued.hashCode()))+ days_to_complete)+ Long.hashCode(end_location_id))+ Boolean.hashCode(for_corporation))+ issuer_corporation_id)+ issuer_id)+ Double.hashCode(price))+ Double.hashCode(reward))+ Long.hashCode(start_location_id))+((status == null)? 0 :status.hashCode()))+((title == null)? 0 :title.hashCode()))+((type == null)? 0 :type.hashCode()))+ Double.hashCode(volume));
    }
}
