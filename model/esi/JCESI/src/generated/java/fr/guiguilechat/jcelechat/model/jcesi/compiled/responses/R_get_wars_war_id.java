package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_wars_war_id {
    /**
     * The aggressor corporation or alliance that declared this war, only contains either corporation_id or alliance_id
     */
    public M_get_wars_war_4 aggressor;
    /**
     * allied corporations or alliances, each object contains either corporation_id or alliance_id
     */
    public R_get_wars_war_id_allies[] allies;
    /**
     * Time that the war was declared
     */
    public String declared;
    /**
     * The defending corporation or alliance that declared this war, only contains either corporation_id or alliance_id
     */
    public M_get_wars_war_4 defender;
    /**
     * Time the war ended and shooting was no longer allowed
     */
    public String finished;
    /**
     * ID of the specified war
     */
    public int id;
    /**
     * Was the war declared mutual by both parties
     */
    public boolean mutual;
    /**
     * Is the war currently open for allies or not
     */
    public boolean open_for_allies;
    /**
     * Time the war was retracted but both sides could still shoot each other
     */
    public String retracted;
    /**
     * Time when the war started and both sides could shoot each other
     */
    public String started;
}
