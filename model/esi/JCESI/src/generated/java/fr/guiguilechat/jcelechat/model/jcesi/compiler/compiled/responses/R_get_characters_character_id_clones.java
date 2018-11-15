package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_get_characters_character_id_clones {
    /**
     * home_location object
     */
    public get_characters_character_id_clones_home_location home_location;
    /**
     * jump_clones array
     */
    public get_characters_character_id_clones_jump_clones[] jump_clones;
    /**
     * last_clone_jump_date string
     */
    public String last_clone_jump_date;
    /**
     * last_station_change_date string
     */
    public String last_station_change_date;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_characters_character_id_clones othersame = ((R_get_characters_character_id_clones) other);
        if ((home_location!= othersame.home_location)&&((home_location == null)||(!home_location.equals(othersame.home_location)))) {
            return false;
        }
        if ((jump_clones!= othersame.jump_clones)&&((jump_clones == null)||(!jump_clones.equals(othersame.jump_clones)))) {
            return false;
        }
        if ((last_clone_jump_date!= othersame.last_clone_jump_date)&&((last_clone_jump_date == null)||(!last_clone_jump_date.equals(othersame.last_clone_jump_date)))) {
            return false;
        }
        if ((last_station_change_date!= othersame.last_station_change_date)&&((last_station_change_date == null)||(!last_station_change_date.equals(othersame.last_station_change_date)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((home_location == null)? 0 :home_location.hashCode())+((jump_clones == null)? 0 :jump_clones.hashCode()))+((last_clone_jump_date == null)? 0 :last_clone_jump_date.hashCode()))+((last_station_change_date == null)? 0 :last_station_change_date.hashCode()));
    }
}
