package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_universe_structures_structure_id {
    /**
     * The full name of the structure
     */
    public String name;
    /**
     * The ID of the corporation who owns this particular structure
     */
    public int owner_id;
    /**
     * Coordinates of the structure in Cartesian space relative to the Sun, in metres.
     * 
     */
    public M_3_xnumber_ynumber_znumber position;
    /**
     * solar_system_id integer
     */
    public int solar_system_id;
    /**
     * type_id integer
     */
    public int type_id;
}
