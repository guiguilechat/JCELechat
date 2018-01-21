package is.ccp.tech.esi.responses;

public class R_get_universe_systems_system_id {
    public int star_id;
    public int system_id;
    public String name;
    public R_get_universe_systems_system_id_position position;
    public float security_status;
    public String security_class;
    public int constellation_id;
    public R_get_universe_systems_system_id_planets[] planets;
    public int[] stargates;
    public int[] stations;
}
