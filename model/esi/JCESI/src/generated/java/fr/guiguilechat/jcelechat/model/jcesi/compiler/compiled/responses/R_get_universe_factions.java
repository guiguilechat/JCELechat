package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_get_universe_factions {
    /**
     * corporation_id integer
     */
    public int corporation_id;
    /**
     * description string
     */
    public String description;
    /**
     * faction_id integer
     */
    public int faction_id;
    /**
     * is_unique boolean
     */
    public boolean is_unique;
    /**
     * militia_corporation_id integer
     */
    public int militia_corporation_id;
    /**
     * name string
     */
    public String name;
    /**
     * size_factor number
     */
    public float size_factor;
    /**
     * solar_system_id integer
     */
    public int solar_system_id;
    /**
     * station_count integer
     */
    public int station_count;
    /**
     * station_system_count integer
     */
    public int station_system_count;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_universe_factions othersame = ((R_get_universe_factions) other);
        if (corporation_id!= othersame.corporation_id) {
            return false;
        }
        if ((description!= othersame.description)&&((description == null)||(!description.equals(othersame.description)))) {
            return false;
        }
        if (faction_id!= othersame.faction_id) {
            return false;
        }
        if (is_unique!= othersame.is_unique) {
            return false;
        }
        if (militia_corporation_id!= othersame.militia_corporation_id) {
            return false;
        }
        if ((name!= othersame.name)&&((name == null)||(!name.equals(othersame.name)))) {
            return false;
        }
        if (size_factor!= othersame.size_factor) {
            return false;
        }
        if (solar_system_id!= othersame.solar_system_id) {
            return false;
        }
        if (station_count!= othersame.station_count) {
            return false;
        }
        if (station_system_count!= othersame.station_system_count) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((((corporation_id +((description == null)? 0 :description.hashCode()))+ faction_id)+ Boolean.hashCode(is_unique))+ militia_corporation_id)+((name == null)? 0 :name.hashCode()))+ Double.hashCode(size_factor))+ solar_system_id)+ station_count)+ station_system_count);
    }
}
