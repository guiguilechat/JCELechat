package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_orders_range;

public class R_get_corporations_corporation_id_orders {
    /**
     * Number of days for which order is valid (starting from the issued date). An order expires at time issued + duration
     */
    public int duration;
    /**
     * For buy orders, the amount of ISK in escrow
     */
    public double escrow;
    /**
     * True if the order is a bid (buy) order
     */
    public boolean is_buy_order;
    /**
     * Date and time when this order was issued
     */
    public String issued;
    /**
     * The character who issued this order
     */
    public int issued_by;
    /**
     * ID of the location where order was placed
     */
    public long location_id;
    /**
     * For buy orders, the minimum quantity that will be accepted in a matching sell order
     */
    public int min_volume;
    /**
     * Unique order ID
     */
    public long order_id;
    /**
     * Cost per unit for this order
     */
    public double price;
    /**
     * Valid order range, numbers are ranges in jumps
     */
    public get_corporations_corporation_id_orders_range range;
    /**
     * ID of the region where order was placed
     */
    public int region_id;
    /**
     * The type ID of the item transacted in this order
     */
    public int type_id;
    /**
     * Quantity of items still required or offered
     */
    public int volume_remain;
    /**
     * Quantity of items required or offered at time order was placed
     */
    public int volume_total;
    /**
     * The corporation wallet division used for this order.
     */
    public int wallet_division;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_corporations_corporation_id_orders othersame = ((R_get_corporations_corporation_id_orders) other);
        if (duration!= othersame.duration) {
            return false;
        }
        if (escrow!= othersame.escrow) {
            return false;
        }
        if (is_buy_order!= othersame.is_buy_order) {
            return false;
        }
        if ((issued!= othersame.issued)&&((issued == null)||(!issued.equals(othersame.issued)))) {
            return false;
        }
        if (issued_by!= othersame.issued_by) {
            return false;
        }
        if (location_id!= othersame.location_id) {
            return false;
        }
        if (min_volume!= othersame.min_volume) {
            return false;
        }
        if (order_id!= othersame.order_id) {
            return false;
        }
        if (price!= othersame.price) {
            return false;
        }
        if ((range!= othersame.range)&&((range == null)||(!range.equals(othersame.range)))) {
            return false;
        }
        if (region_id!= othersame.region_id) {
            return false;
        }
        if (type_id!= othersame.type_id) {
            return false;
        }
        if (volume_remain!= othersame.volume_remain) {
            return false;
        }
        if (volume_total!= othersame.volume_total) {
            return false;
        }
        if (wallet_division!= othersame.wallet_division) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((((((((((duration + Double.hashCode(escrow))+ Boolean.hashCode(is_buy_order))+((issued == null)? 0 :issued.hashCode()))+ issued_by)+ Long.hashCode(location_id))+ min_volume)+ Long.hashCode(order_id))+ Double.hashCode(price))+((range == null)? 0 :range.hashCode()))+ region_id)+ type_id)+ volume_remain)+ volume_total)+ wallet_division);
    }
}
