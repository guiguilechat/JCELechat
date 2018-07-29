package fr.guiguilechat.jcelechat.model.esi.compiled.responses;

public class R_get_universe_systems_system_id {
    /**
     * The constellation this solar system is in
     */
    public int constellation_id;
    /**
     * name string
     */
    public String name;
    /**
     * planets array
     */
    public R_get_universe_systems_system_id_planets[] planets;
    /**
     * position object
     */
    public M_3_xnumber_ynumber_znumber position;
    /**
     * security_class string
     */
    public String security_class;
    /**
     * security_status number
     */
    public float security_status;
    /**
     * star_id integer
     */
    public int star_id;
    /**
     * stargates array
     */
    public int[] stargates;
    /**
     * stations array
     */
    public int[] stations;
    /**
     * system_id integer
     */
    public int system_id;
}
