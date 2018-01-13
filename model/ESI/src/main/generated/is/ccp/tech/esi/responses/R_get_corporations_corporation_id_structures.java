package is.ccp.tech.esi.responses;

public class R_get_corporations_corporation_id_structures {
    public long structure_id;
    public long type_id;
    public long corporation_id;
    public long system_id;
    public long profile_id;
    public R_get_corporations_corporation_id_structures_current_vul[] current_vul;
    public R_get_corporations_corporation_id_structures_next_vul[] next_vul;
    public String fuel_expires;
    public R_get_corporations_corporation_id_structures_services[] services;
    public String state_timer_start;
    public String state_timer_end;
    public String unanchors_at;
}
