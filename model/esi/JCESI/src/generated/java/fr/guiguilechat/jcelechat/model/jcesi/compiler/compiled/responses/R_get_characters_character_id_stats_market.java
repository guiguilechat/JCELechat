package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_get_characters_character_id_stats_market {
    /**
     * accept_contracts_courier integer
     */
    public long accept_contracts_courier;
    /**
     * accept_contracts_item_exchange integer
     */
    public long accept_contracts_item_exchange;
    /**
     * buy_orders_placed integer
     */
    public long buy_orders_placed;
    /**
     * cancel_market_order integer
     */
    public long cancel_market_order;
    /**
     * create_contracts_auction integer
     */
    public long create_contracts_auction;
    /**
     * create_contracts_courier integer
     */
    public long create_contracts_courier;
    /**
     * create_contracts_item_exchange integer
     */
    public long create_contracts_item_exchange;
    /**
     * deliver_courier_contract integer
     */
    public long deliver_courier_contract;
    /**
     * isk_gained integer
     */
    public long isk_gained;
    /**
     * isk_spent integer
     */
    public long isk_spent;
    /**
     * modify_market_order integer
     */
    public long modify_market_order;
    /**
     * search_contracts integer
     */
    public long search_contracts;
    /**
     * sell_orders_placed integer
     */
    public long sell_orders_placed;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_characters_character_id_stats_market othersame = ((R_get_characters_character_id_stats_market) other);
        if (accept_contracts_courier!= othersame.accept_contracts_courier) {
            return false;
        }
        if (accept_contracts_item_exchange!= othersame.accept_contracts_item_exchange) {
            return false;
        }
        if (buy_orders_placed!= othersame.buy_orders_placed) {
            return false;
        }
        if (cancel_market_order!= othersame.cancel_market_order) {
            return false;
        }
        if (create_contracts_auction!= othersame.create_contracts_auction) {
            return false;
        }
        if (create_contracts_courier!= othersame.create_contracts_courier) {
            return false;
        }
        if (create_contracts_item_exchange!= othersame.create_contracts_item_exchange) {
            return false;
        }
        if (deliver_courier_contract!= othersame.deliver_courier_contract) {
            return false;
        }
        if (isk_gained!= othersame.isk_gained) {
            return false;
        }
        if (isk_spent!= othersame.isk_spent) {
            return false;
        }
        if (modify_market_order!= othersame.modify_market_order) {
            return false;
        }
        if (search_contracts!= othersame.search_contracts) {
            return false;
        }
        if (sell_orders_placed!= othersame.sell_orders_placed) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((((((((Long.hashCode(accept_contracts_courier)+ Long.hashCode(accept_contracts_item_exchange))+ Long.hashCode(buy_orders_placed))+ Long.hashCode(cancel_market_order))+ Long.hashCode(create_contracts_auction))+ Long.hashCode(create_contracts_courier))+ Long.hashCode(create_contracts_item_exchange))+ Long.hashCode(deliver_courier_contract))+ Long.hashCode(isk_gained))+ Long.hashCode(isk_spent))+ Long.hashCode(modify_market_order))+ Long.hashCode(search_contracts))+ Long.hashCode(sell_orders_placed));
    }
}
