package fr.guiguilechat.eveonline.model.esi.compiled.responses;

public class R_get_characters_character_id_mail {
    public int from;
    public boolean is_read;
    public int[] labels;
    public int mail_id;
    public R_get_characters_character_id_mail_recipients[] recipients;
    public String subject;
    public String timestamp;
}
