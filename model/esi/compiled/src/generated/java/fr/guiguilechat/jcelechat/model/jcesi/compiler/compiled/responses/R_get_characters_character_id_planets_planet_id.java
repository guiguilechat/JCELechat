package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_get_characters_character_id_planets_planet_id {
    /**
     * links array
     */
    public get_characters_character_id_planets_planet_id_links[] links;
    /**
     * pins array
     */
    public get_characters_character_id_planets_planet_id_pins[] pins;
    /**
     * routes array
     */
    public get_characters_character_id_planets_planet_id_routes[] routes;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_characters_character_id_planets_planet_id othersame = ((R_get_characters_character_id_planets_planet_id) other);
        if ((links!= othersame.links)&&((links == null)||(!links.equals(othersame.links)))) {
            return false;
        }
        if ((pins!= othersame.pins)&&((pins == null)||(!pins.equals(othersame.pins)))) {
            return false;
        }
        if ((routes!= othersame.routes)&&((routes == null)||(!routes.equals(othersame.routes)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((links == null)? 0 :links.hashCode())+((pins == null)? 0 :pins.hashCode()))+((routes == null)? 0 :routes.hashCode()));
    }
}
