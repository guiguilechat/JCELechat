package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_mail_labels_color;

public class get_characters_character_id_mail_labels_labels {
    /**
     * color string
     */
    public get_characters_character_id_mail_labels_color color;
    /**
     * label_id integer
     */
    public int label_id;
    /**
     * name string
     */
    public String name;
    /**
     * unread_count integer
     */
    public int unread_count;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        get_characters_character_id_mail_labels_labels othersame = ((get_characters_character_id_mail_labels_labels) other);
        if ((color!= othersame.color)&&((color == null)||(!color.equals(othersame.color)))) {
            return false;
        }
        if (label_id!= othersame.label_id) {
            return false;
        }
        if ((name!= othersame.name)&&((name == null)||(!name.equals(othersame.name)))) {
            return false;
        }
        if (unread_count!= othersame.unread_count) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((color == null)? 0 :color.hashCode())+ label_id)+((name == null)? 0 :name.hashCode()))+ unread_count);
    }
}
