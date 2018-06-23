package fr.guiguilechat.eveonline.model.esi.compiled.responses;

public class R_get_corporations_corporation_id_medals_issued {
    /**
     * ID of the character who was rewarded this medal
     */
    public int character_id;
    /**
     * issued_at string
     */
    public String issued_at;
    /**
     * ID of the character who issued the medal
     */
    public int issuer_id;
    /**
     * medal_id integer
     */
    public int medal_id;
    /**
     * reason string
     */
    public String reason;
    /**
     * status string
     */
    public String status;
}
