package fr.guiguilechat.eveonline.model.esi.compiled.responses;

public class R_get_markets_region_id_orders {
    public long order_id;
    public int type_id;
    public long location_id;
    public int volume_total;
    public int volume_remain;
    public int min_volume;
    public double price;
    public boolean is_buy_order;
    public int duration;
    public String issued;
    public String range;
}
