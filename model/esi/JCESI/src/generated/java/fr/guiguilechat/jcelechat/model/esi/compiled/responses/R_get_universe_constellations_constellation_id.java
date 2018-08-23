package fr.guiguilechat.jcelechat.model.esi.compiled.responses;

public class R_get_universe_constellations_constellation_id {
    /**
     * constellation_id integer
     */
    public int constellation_id;
    /**
     * name string
     */
    public String name;
    /**
     * position object
     */
    public M_3_xnumber_ynumber_znumber position;
    /**
     * The region this constellation is in
     */
    public int region_id;
    /**
     * systems array
     */
    public int[] systems;
}
