package fr.guiguilechat.eveonline.model.esi.compiled.responses;

public class R_get_corporations_corporation_id_orders_history {
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
    public int wallet_division;
}
