package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_corporations_corporation_id_orders_history {
    /**
     * Number of days the order was valid for (starting from the issued date). An order expires at time issued + duration
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
    public String range;
    /**
     * ID of the region where order was placed
     */
    public int region_id;
    /**
     * Current order state
     */
    public String state;
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
     * The corporation wallet division used for this order
     */
    public int wallet_division;
}
