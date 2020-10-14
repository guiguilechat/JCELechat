package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_markets_structures_structure_id_range;

public class R_get_markets_structures_structure_id {
    /**
     * duration integer
     */
    public int duration;
    /**
     * is_buy_order boolean
     */
    public boolean is_buy_order;
    /**
     * issued string
     */
    public String issued;
    /**
     * location_id integer
     */
    public long location_id;
    /**
     * min_volume integer
     */
    public int min_volume;
    /**
     * order_id integer
     */
    public long order_id;
    /**
     * price number
     */
    public double price;
    /**
     * range string
     */
    public get_markets_structures_structure_id_range range;
    /**
     * type_id integer
     */
    public int type_id;
    /**
     * volume_remain integer
     */
    public int volume_remain;
    /**
     * volume_total integer
     */
    public int volume_total;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_markets_structures_structure_id othersame = ((R_get_markets_structures_structure_id) other);
        if (duration!= othersame.duration) {
            return false;
        }
        if (is_buy_order!= othersame.is_buy_order) {
            return false;
        }
        if ((issued!= othersame.issued)&&((issued == null)||(!issued.equals(othersame.issued)))) {
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
        if (type_id!= othersame.type_id) {
            return false;
        }
        if (volume_remain!= othersame.volume_remain) {
            return false;
        }
        if (volume_total!= othersame.volume_total) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((((((duration + Boolean.hashCode(is_buy_order))+((issued == null)? 0 :issued.hashCode()))+ Long.hashCode(location_id))+ min_volume)+ Long.hashCode(order_id))+ Double.hashCode(price))+((range == null)? 0 :range.hashCode()))+ type_id)+ volume_remain)+ volume_total);
    }
}
