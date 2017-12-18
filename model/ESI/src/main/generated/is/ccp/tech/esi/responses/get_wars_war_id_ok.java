
package is.ccp.tech.esi.responses;


public class get_wars_war_id_ok {

    public int id;
    public String declared;
    public String started;
    public String retracted;
    public String finished;
    public boolean mutual;
    public boolean open_for_allies;
    public get_wars_war_id_ok_aggressor aggressor;
    public get_wars_war_id_ok_defender defender;
    public get_wars_war_id_ok_allies[] allies;

}
