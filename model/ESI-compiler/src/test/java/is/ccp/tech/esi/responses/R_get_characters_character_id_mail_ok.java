package is.ccp.tech.esi.responses;

public class R_get_characters_character_id_mail_ok {
    public int mail_id;
    public String subject;
    public int from;
    public String timestamp;
    public int[] labels;
    public R_get_characters_character_id_mail_ok_recipients[] recipients;
    public boolean is_read;
}
