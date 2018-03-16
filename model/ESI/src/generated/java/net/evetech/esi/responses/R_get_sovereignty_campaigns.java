package net.evetech.esi.responses;

public class R_get_sovereignty_campaigns {
    public int campaign_id;
    public long structure_id;
    public int solar_system_id;
    public int constellation_id;
    public String event_type;
    public String start_time;
    public int defender_id;
    public float defender_score;
    public float attackers_score;
    public R_get_sovereignty_campaigns_participants[] participants;
}
