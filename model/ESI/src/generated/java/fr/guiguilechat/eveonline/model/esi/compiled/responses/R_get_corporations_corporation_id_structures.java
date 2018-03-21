package fr.guiguilechat.eveonline.model.esi.compiled.responses;

public class R_get_corporations_corporation_id_structures {
    public long structure_id;
    public int type_id;
    public int corporation_id;
    public int system_id;
    public int profile_id;
    public String fuel_expires;
    public R_get_corporations_corporation_id_structures_services[] services;
    public String state_timer_start;
    public String state_timer_end;
    public String unanchors_at;
    public String state;
    public int reinforce_weekday;
    public int reinforce_hour;
    public int next_reinforce_weekday;
    public int next_reinforce_hour;
    public String next_reinforce_apply;
}
