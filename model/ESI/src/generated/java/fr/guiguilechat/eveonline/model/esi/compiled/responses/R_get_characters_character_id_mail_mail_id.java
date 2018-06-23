package fr.guiguilechat.eveonline.model.esi.compiled.responses;

public class R_get_characters_character_id_mail_mail_id {
    /**
     * Mail's body
     */
    public String body;
    /**
     * From whom the mail was sent
     */
    public int from;
    /**
     * Labels attached to the mail
     */
    public int[] labels;
    /**
     * Whether the mail is flagged as read
     */
    public boolean read;
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
