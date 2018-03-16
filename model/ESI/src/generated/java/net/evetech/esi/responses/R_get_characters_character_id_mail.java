package net.evetech.esi.responses;

public class R_get_characters_character_id_mail {
    public long mail_id;
    public String subject;
    public int from;
    public String timestamp;
    public long[] labels;
    public R_get_characters_character_id_mail_recipients[] recipients;
    public boolean is_read;
}
