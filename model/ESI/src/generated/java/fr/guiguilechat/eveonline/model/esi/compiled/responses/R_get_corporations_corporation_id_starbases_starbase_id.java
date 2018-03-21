package fr.guiguilechat.eveonline.model.esi.compiled.responses;

public class R_get_corporations_corporation_id_starbases_starbase_id {
    public String fuel_bay_view;
    public String fuel_bay_take;
    public String anchor;
    public String unanchor;
    public String online;
    public String offline;
    public boolean allow_corporation_members;
    public boolean allow_alliance_members;
    public boolean use_alliance_standings;
    public float attack_standing_threshold;
    public float attack_security_status_threshold;
    public boolean attack_if_other_security_status_dropping;
    public boolean attack_if_at_war;
    public R_get_corporations_corporation_id_starbases_starbase_id_fuels[] fuels;
}
