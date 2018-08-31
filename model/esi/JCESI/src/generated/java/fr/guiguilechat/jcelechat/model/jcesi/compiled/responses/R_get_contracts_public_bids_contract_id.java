package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_contracts_public_bids_contract_id {
    /**
     * The amount bid, in ISK
     */
    public float amount;
    /**
     * Unique ID for the bid
     */
    public int bid_id;
    /**
     * Datetime when the bid was placed
     */
    public String date_bid;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_contracts_public_bids_contract_id othersame = ((R_get_contracts_public_bids_contract_id) other);
        if (amount!= othersame.amount) {
            return false;
        }
        if (bid_id!= othersame.bid_id) {
            return false;
        }
        if ((date_bid!= othersame.date_bid)&&((date_bid == null)||(!date_bid.equals(othersame.date_bid)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((Double.hashCode(amount)+ bid_id)+((date_bid == null)? 0 :date_bid.hashCode()));
    }
}
