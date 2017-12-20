package is.ccp.tech.esi.responses;

public class R_get_universe_systems_system_id_ok {
    public long star_id;
    public long system_id;
    public String name;
    public R_get_universe_systems_system_id_ok_position position;
    public double security_status;
    public String security_class;
    public long constellation_id;
    public R_get_universe_systems_system_id_ok_planets[] planets;
    public long[] stargates;
    public long[] stations;
}
