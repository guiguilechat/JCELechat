package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_universe_stations_station_id {
    /**
     * max_dockable_ship_volume number
     */
    public float max_dockable_ship_volume;
    /**
     * name string
     */
    public String name;
    /**
     * office_rental_cost number
     */
    public float office_rental_cost;
    /**
     * ID of the corporation that controls this station
     */
    public int owner;
    /**
     * position object
     */
    public M_3_xnumber_ynumber_znumber position;
    /**
     * race_id integer
     */
    public int race_id;
    /**
     * reprocessing_efficiency number
     */
    public float reprocessing_efficiency;
    /**
     * reprocessing_stations_take number
     */
    public float reprocessing_stations_take;
    /**
     * services array
     */
    public String[] services;
    /**
     * station_id integer
     */
    public int station_id;
    /**
     * The solar system this station is in
     */
    public int system_id;
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
        R_get_universe_stations_station_id othersame = ((R_get_universe_stations_station_id) other);
        if (max_dockable_ship_volume!= othersame.max_dockable_ship_volume) {
            return false;
        }
        if ((name!= othersame.name)&&((name == null)||(!name.equals(othersame.name)))) {
            return false;
        }
        if (office_rental_cost!= othersame.office_rental_cost) {
            return false;
        }
        if (owner!= othersame.owner) {
            return false;
        }
        if ((position!= othersame.position)&&((position == null)||(!position.equals(othersame.position)))) {
            return false;
        }
        if (race_id!= othersame.race_id) {
            return false;
        }
        if (reprocessing_efficiency!= othersame.reprocessing_efficiency) {
            return false;
        }
        if (reprocessing_stations_take!= othersame.reprocessing_stations_take) {
            return false;
        }
        if ((services!= othersame.services)&&((services == null)||(!services.equals(othersame.services)))) {
            return false;
        }
        if (station_id!= othersame.station_id) {
            return false;
        }
        if (system_id!= othersame.system_id) {
            return false;
        }
        if (type_id!= othersame.type_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((((((Double.hashCode(max_dockable_ship_volume)+((name == null)? 0 :name.hashCode()))+ Double.hashCode(office_rental_cost))+ owner)+((position == null)? 0 :position.hashCode()))+ race_id)+ Double.hashCode(reprocessing_efficiency))+ Double.hashCode(reprocessing_stations_take))+((services == null)? 0 :services.hashCode()))+ station_id)+ system_id)+ type_id);
    }
}
