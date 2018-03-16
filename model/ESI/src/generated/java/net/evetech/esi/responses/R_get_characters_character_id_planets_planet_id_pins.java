package net.evetech.esi.responses;

public class R_get_characters_character_id_planets_planet_id_pins {
    public float latitude;
    public float longitude;
    public long pin_id;
    public int type_id;
    public int schematic_id;
    public R_get_characters_character_id_planets_planet_id_pins_extractor_details extractor_details;
    public R_get_characters_character_id_planets_planet_id_pins_factory_details factory_details;
    public R_get_characters_character_id_planets_planet_id_pins_contents[] contents;
    public String install_time;
    public String expiry_time;
    public String last_cycle_start;
}
