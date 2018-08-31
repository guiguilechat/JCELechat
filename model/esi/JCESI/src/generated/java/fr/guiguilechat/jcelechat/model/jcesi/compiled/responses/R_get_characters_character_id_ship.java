package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_characters_character_id_ship {
    /**
     * Item id's are unique to a ship and persist until it is repackaged. This value can be used to track repeated uses of a ship, or detect when a pilot changes into a different instance of the same ship type.
     */
    public long ship_item_id;
    /**
     * ship_name string
     */
    public String ship_name;
    /**
     * ship_type_id integer
     */
    public int ship_type_id;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_characters_character_id_ship othersame = ((R_get_characters_character_id_ship) other);
        if (ship_item_id!= othersame.ship_item_id) {
            return false;
        }
        if ((ship_name!= othersame.ship_name)&&((ship_name == null)||(!ship_name.equals(othersame.ship_name)))) {
            return false;
        }
        if (ship_type_id!= othersame.ship_type_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((Long.hashCode(ship_item_id)+((ship_name == null)? 0 :ship_name.hashCode()))+ ship_type_id);
    }
}
