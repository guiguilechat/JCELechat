package fr.guiguilechat.eveonline.model.esi.compiled.responses;

public class R_get_wars_war_id {
    public R_get_wars_war_id_aggressor aggressor;
    public R_get_wars_war_id_allies[] allies;
    public String declared;
    public R_get_wars_war_id_defender defender;
    public String finished;
    public int id;
    public boolean mutual;
    public boolean open_for_allies;
    public String retracted;
    public String started;
}
