package net.evetech.esi.responses;

public class R_get_characters_character_id_orders_history {
    public long order_id;
    public int type_id;
    public int region_id;
    public long location_id;
    public String range;
    public double price;
    public int volume_total;
    public int volume_remain;
    public String issued;
    public boolean is_buy_order;
    public int min_volume;
    public double escrow;
    public int duration;
    public String state;
    public boolean is_corporation;
}
