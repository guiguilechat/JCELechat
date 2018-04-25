package fr.guiguilechat.eveonline.model.esi.compiled.responses;

public class R_get_characters_character_id_mail {
    public int mail_id;
    public String subject;
    public int from;
    public String timestamp;
    public int[] labels;
    public R_get_characters_character_id_mail_recipients[] recipients;
    public boolean is_read;
}
