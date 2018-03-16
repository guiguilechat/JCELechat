package net.evetech.esi.responses;

public class R_get_characters_character_id_mail_mail_id {
    public String subject;
    public int from;
    public String timestamp;
    public R_get_characters_character_id_mail_mail_id_recipients[] recipients;
    public String body;
    public long[] labels;
    public boolean read;
}
