package is.ccp.tech.esi.responses;

public class R_get_characters_character_id_mail_ok {
    public long mail_id;
    public String subject;
    public long from;
    public String timestamp;
    public long[] labels;
    public R_get_characters_character_id_mail_ok_recipients[] recipients;
    public boolean is_read;
}
