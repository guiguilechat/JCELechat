package fr.guiguilechat.jcelechat.model.esi.compiled.responses;

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
}
