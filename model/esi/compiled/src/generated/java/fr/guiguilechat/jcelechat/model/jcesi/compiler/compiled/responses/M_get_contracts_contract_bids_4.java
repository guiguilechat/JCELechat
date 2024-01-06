package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class M_get_contracts_contract_bids_4 {
    /**
     * The amount bid, in ISK
     */
    public float amount;
    /**
     * Unique ID for the bid
     */
    public int bid_id;
    /**
     * Character ID of the bidder
     */
    public int bidder_id;
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
        M_get_contracts_contract_bids_4 othersame = ((M_get_contracts_contract_bids_4) other);
        if (amount!= othersame.amount) {
            return false;
        }
        if (bid_id!= othersame.bid_id) {
            return false;
        }
        if (bidder_id!= othersame.bidder_id) {
            return false;
        }
        if ((date_bid!= othersame.date_bid)&&((date_bid == null)||(!date_bid.equals(othersame.date_bid)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((Double.hashCode(amount)+ bid_id)+ bidder_id)+((date_bid == null)? 0 :date_bid.hashCode()));
    }
}
