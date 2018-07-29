package fr.guiguilechat.jcelechat.model.esi.compiled.responses;

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
}
