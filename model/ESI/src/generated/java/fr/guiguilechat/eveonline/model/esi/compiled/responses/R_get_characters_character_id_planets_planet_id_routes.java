package fr.guiguilechat.eveonline.model.esi.compiled.responses;

public class R_get_characters_character_id_planets_planet_id_routes {
    /**
     * content_type_id integer
     */
    public int content_type_id;
    /**
     * destination_pin_id integer
     */
    public long destination_pin_id;
    /**
     * quantity number
     */
    public float quantity;
    /**
     * route_id integer
     */
    public long route_id;
    /**
     * source_pin_id integer
     */
    public long source_pin_id;
    /**
     * list of pin ID waypoints
     */
    public long[] waypoints;
}
