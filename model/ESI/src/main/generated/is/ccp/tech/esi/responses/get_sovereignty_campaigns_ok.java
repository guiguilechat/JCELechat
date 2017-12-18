
package is.ccp.tech.esi.responses;


public class get_sovereignty_campaigns_ok {

    public int campaign_id;
    public int structure_id;
    public int solar_system_id;
    public int constellation_id;
    public String event_type;
    public String start_time;
    public int defender_id;
    public double defender_score;
    public double attackers_score;
    public get_sovereignty_campaigns_ok_participants[] participants;

}
