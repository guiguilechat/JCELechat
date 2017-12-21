package is.ccp.tech.esi.responses;

public class R_get_sovereignty_campaigns_ok {
    public long campaign_id;
    public long structure_id;
    public long solar_system_id;
    public long constellation_id;
    public String event_type;
    public String start_time;
    public long defender_id;
    public double defender_score;
    public double attackers_score;
    public R_get_sovereignty_campaigns_ok_participants[] participants;
}
