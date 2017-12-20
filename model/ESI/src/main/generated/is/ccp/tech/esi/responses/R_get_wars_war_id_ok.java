package is.ccp.tech.esi.responses;

public class R_get_wars_war_id_ok {
    public long id;
    public String declared;
    public String started;
    public String retracted;
    public String finished;
    public boolean mutual;
    public boolean open_for_allies;
    public R_get_wars_war_id_ok_aggressor aggressor;
    public R_get_wars_war_id_ok_defender defender;
    public R_get_wars_war_id_ok_allies[] allies;
}
