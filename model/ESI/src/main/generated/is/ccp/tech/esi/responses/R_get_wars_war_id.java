package is.ccp.tech.esi.responses;

public class R_get_wars_war_id {
    public int id;
    public String declared;
    public String started;
    public String retracted;
    public String finished;
    public boolean mutual;
    public boolean open_for_allies;
    public R_get_wars_war_id_aggressor aggressor;
    public R_get_wars_war_id_defender defender;
    public R_get_wars_war_id_allies[] allies;
}
