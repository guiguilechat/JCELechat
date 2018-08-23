package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_characters_character_id_mail {
    /**
     * From whom the mail was sent
     */
    public int from;
    /**
     * is_read boolean
     */
    public boolean is_read;
    /**
     * labels array
     */
    public int[] labels;
    /**
     * mail_id integer
     */
    public int mail_id;
    /**
     * Recipients of the mail
     */
    public M_get_characters_character_mail_recipients_2 [] recipients;
    /**
     * Mail subject
     */
    public String subject;
    /**
     * When the mail was sent
     */
    public String timestamp;
}
