package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_characters_character_id_medals_graphics {
    /**
     * color integer
     */
    public int color;
    /**
     * graphic string
     */
    public String graphic;
    /**
     * layer integer
     */
    public int layer;
    /**
     * part integer
     */
    public int part;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_characters_character_id_medals_graphics othersame = ((R_get_characters_character_id_medals_graphics) other);
        if (color!= othersame.color) {
            return false;
        }
        if ((graphic!= othersame.graphic)&&((graphic == null)||(!graphic.equals(othersame.graphic)))) {
            return false;
        }
        if (layer!= othersame.layer) {
            return false;
        }
        if (part!= othersame.part) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((color +((graphic == null)? 0 :graphic.hashCode()))+ layer)+ part);
    }
}
