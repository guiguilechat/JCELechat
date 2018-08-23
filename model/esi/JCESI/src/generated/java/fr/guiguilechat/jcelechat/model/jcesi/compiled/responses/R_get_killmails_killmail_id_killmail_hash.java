package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_killmails_killmail_id_killmail_hash {
    /**
     * attackers array
     */
    public R_get_killmails_killmail_id_killmail_hash_attackers[] attackers;
    /**
     * ID of the killmail
     */
    public int killmail_id;
    /**
     * Time that the victim was killed and the killmail generated
     * 
     */
    public String killmail_time;
    /**
     * Moon if the kill took place at one
     */
    public int moon_id;
    /**
     * Solar system that the kill took place in
     * 
     */
    public int solar_system_id;
    /**
     * victim object
     */
    public R_get_killmails_killmail_id_killmail_hash_victim victim;
    /**
     * War if the killmail is generated in relation to an official war
     * 
     */
    public int war_id;
}
