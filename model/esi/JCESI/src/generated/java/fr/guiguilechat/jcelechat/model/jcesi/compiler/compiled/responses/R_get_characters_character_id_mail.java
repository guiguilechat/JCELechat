package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

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
    public R_get_characters_character_id_mail_recipients[] recipients;
    /**
     * Mail subject
     */
    public String subject;
    /**
     * When the mail was sent
     */
    public String timestamp;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_characters_character_id_mail othersame = ((R_get_characters_character_id_mail) other);
        if (from!= othersame.from) {
            return false;
        }
        if (is_read!= othersame.is_read) {
            return false;
        }
        if ((labels!= othersame.labels)&&((labels == null)||(!labels.equals(othersame.labels)))) {
            return false;
        }
        if (mail_id!= othersame.mail_id) {
            return false;
        }
        if ((recipients!= othersame.recipients)&&((recipients == null)||(!recipients.equals(othersame.recipients)))) {
            return false;
        }
        if ((subject!= othersame.subject)&&((subject == null)||(!subject.equals(othersame.subject)))) {
            return false;
        }
        if ((timestamp!= othersame.timestamp)&&((timestamp == null)||(!timestamp.equals(othersame.timestamp)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((from + Boolean.hashCode(is_read))+((labels == null)? 0 :labels.hashCode()))+ mail_id)+((recipients == null)? 0 :recipients.hashCode()))+((subject == null)? 0 :subject.hashCode()))+((timestamp == null)? 0 :timestamp.hashCode()));
    }
}
