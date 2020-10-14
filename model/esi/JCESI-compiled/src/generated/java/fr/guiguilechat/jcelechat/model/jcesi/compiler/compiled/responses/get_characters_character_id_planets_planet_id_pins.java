package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class get_characters_character_id_planets_planet_id_pins {
    /**
     * contents array
     */
    public get_characters_character_id_planets_planet_id_contents[] contents;
    /**
     * expiry_time string
     */
    public String expiry_time;
    /**
     * extractor_details object
     */
    public get_characters_character_id_planets_planet_id_extractor_details extractor_details;
    /**
     * factory_details object
     */
    public get_characters_character_id_planets_planet_id_factory_details factory_details;
    /**
     * install_time string
     */
    public String install_time;
    /**
     * last_cycle_start string
     */
    public String last_cycle_start;
    /**
     * latitude number
     */
    public float latitude;
    /**
     * longitude number
     */
    public float longitude;
    /**
     * pin_id integer
     */
    public long pin_id;
    /**
     * schematic_id integer
     */
    public int schematic_id;
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
        get_characters_character_id_planets_planet_id_pins othersame = ((get_characters_character_id_planets_planet_id_pins) other);
        if ((contents!= othersame.contents)&&((contents == null)||(!contents.equals(othersame.contents)))) {
            return false;
        }
        if ((expiry_time!= othersame.expiry_time)&&((expiry_time == null)||(!expiry_time.equals(othersame.expiry_time)))) {
            return false;
        }
        if ((extractor_details!= othersame.extractor_details)&&((extractor_details == null)||(!extractor_details.equals(othersame.extractor_details)))) {
            return false;
        }
        if ((factory_details!= othersame.factory_details)&&((factory_details == null)||(!factory_details.equals(othersame.factory_details)))) {
            return false;
        }
        if ((install_time!= othersame.install_time)&&((install_time == null)||(!install_time.equals(othersame.install_time)))) {
            return false;
        }
        if ((last_cycle_start!= othersame.last_cycle_start)&&((last_cycle_start == null)||(!last_cycle_start.equals(othersame.last_cycle_start)))) {
            return false;
        }
        if (latitude!= othersame.latitude) {
            return false;
        }
        if (longitude!= othersame.longitude) {
            return false;
        }
        if (pin_id!= othersame.pin_id) {
            return false;
        }
        if (schematic_id!= othersame.schematic_id) {
            return false;
        }
        if (type_id!= othersame.type_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((((((((contents == null)? 0 :contents.hashCode())+((expiry_time == null)? 0 :expiry_time.hashCode()))+((extractor_details == null)? 0 :extractor_details.hashCode()))+((factory_details == null)? 0 :factory_details.hashCode()))+((install_time == null)? 0 :install_time.hashCode()))+((last_cycle_start == null)? 0 :last_cycle_start.hashCode()))+ Double.hashCode(latitude))+ Double.hashCode(longitude))+ Long.hashCode(pin_id))+ schematic_id)+ type_id);
    }
}
