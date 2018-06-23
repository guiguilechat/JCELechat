package fr.guiguilechat.eveonline.model.esi.compiled.responses;

public class R_get_corporations_corporation_id_outposts_outpost_id {
    /**
     * coordinates object
     */
    public M_3_xnumber_ynumber_znumber coordinates;
    /**
     * docking_cost_per_ship_volume number
     */
    public float docking_cost_per_ship_volume;
    /**
     * office_rental_cost integer
     */
    public long office_rental_cost;
    /**
     * The entity that owns the station (e.g. the entity whose logo is on the station services bar)
     */
    public int owner_id;
    /**
     * reprocessing_efficiency number
     */
    public float reprocessing_efficiency;
    /**
     * reprocessing_station_take number
     */
    public float reprocessing_station_take;
    /**
     * A list of services the given outpost provides
     */
    public R_get_corporations_corporation_id_outposts_outpost_id_services[] services;
    /**
     * The owner ID that sets the ability for someone to dock based on standings.
     */
    public int standing_owner_id;
    /**
     * The ID of the solar system the outpost rests in
     */
    public int system_id;
    /**
     * The type ID of the given outpost
     */
    public int type_id;
}
