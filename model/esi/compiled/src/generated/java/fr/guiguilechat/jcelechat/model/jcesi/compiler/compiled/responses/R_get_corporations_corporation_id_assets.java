package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_assets_location_flag;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_corporations_corporation_id_assets_location_type;

public class R_get_corporations_corporation_id_assets {
    /**
     * is_blueprint_copy boolean
     */
    public boolean is_blueprint_copy;
    /**
     * is_singleton boolean
     */
    public boolean is_singleton;
    /**
     * item_id integer
     */
    public long item_id;
    /**
     * location_flag string
     */
    public get_corporations_corporation_id_assets_location_flag location_flag;
    /**
     * location_id integer
     */
    public long location_id;
    /**
     * location_type string
     */
    public get_corporations_corporation_id_assets_location_type location_type;
    /**
     * quantity integer
     */
    public int quantity;
    /**
     * type_id integer
     */
    public int type_id;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_corporations_corporation_id_assets othersame = ((R_get_corporations_corporation_id_assets) other);
        if (is_blueprint_copy!= othersame.is_blueprint_copy) {
            return false;
        }
        if (is_singleton!= othersame.is_singleton) {
            return false;
        }
        if (item_id!= othersame.item_id) {
            return false;
        }
        if ((location_flag!= othersame.location_flag)&&((location_flag == null)||(!location_flag.equals(othersame.location_flag)))) {
            return false;
        }
        if (location_id!= othersame.location_id) {
            return false;
        }
        if ((location_type!= othersame.location_type)&&((location_type == null)||(!location_type.equals(othersame.location_type)))) {
            return false;
        }
        if (quantity!= othersame.quantity) {
            return false;
        }
        if (type_id!= othersame.type_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((Boolean.hashCode(is_blueprint_copy)+ Boolean.hashCode(is_singleton))+ Long.hashCode(item_id))+((location_flag == null)? 0 :location_flag.hashCode()))+ Long.hashCode(location_id))+((location_type == null)? 0 :location_type.hashCode()))+ quantity)+ type_id);
    }
}
