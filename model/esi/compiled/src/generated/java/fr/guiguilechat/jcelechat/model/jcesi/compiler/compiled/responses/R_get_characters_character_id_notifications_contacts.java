package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_get_characters_character_id_notifications_contacts {
    /**
     * message string
     */
    public String message;
    /**
     * notification_id integer
     */
    public long notification_id;
    /**
     * send_date string
     */
    public String send_date;
    /**
     * sender_character_id integer
     */
    public int sender_character_id;
    /**
     * A number representing the standing level the receiver has been added at by the sender. The standing levels are as follows: -10 -> Terrible | -5 -> Bad |  0 -> Neutral |  5 -> Good |  10 -> Excellent
     */
    public float standing_level;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_characters_character_id_notifications_contacts othersame = ((R_get_characters_character_id_notifications_contacts) other);
        if ((message!= othersame.message)&&((message == null)||(!message.equals(othersame.message)))) {
            return false;
        }
        if (notification_id!= othersame.notification_id) {
            return false;
        }
        if ((send_date!= othersame.send_date)&&((send_date == null)||(!send_date.equals(othersame.send_date)))) {
            return false;
        }
        if (sender_character_id!= othersame.sender_character_id) {
            return false;
        }
        if (standing_level!= othersame.standing_level) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((message == null)? 0 :message.hashCode())+ Long.hashCode(notification_id))+((send_date == null)? 0 :send_date.hashCode()))+ sender_character_id)+ Double.hashCode(standing_level));
    }
}
