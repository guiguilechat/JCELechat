package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_get_characters_character_id_portrait {
    /**
     * px128x128 string
     */
    public String px128x128;
    /**
     * px256x256 string
     */
    public String px256x256;
    /**
     * px512x512 string
     */
    public String px512x512;
    /**
     * px64x64 string
     */
    public String px64x64;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_characters_character_id_portrait othersame = ((R_get_characters_character_id_portrait) other);
        if ((px128x128 != othersame.px128x128)&&((px128x128 == null)||(!px128x128 .equals(othersame.px128x128)))) {
            return false;
        }
        if ((px256x256 != othersame.px256x256)&&((px256x256 == null)||(!px256x256 .equals(othersame.px256x256)))) {
            return false;
        }
        if ((px512x512 != othersame.px512x512)&&((px512x512 == null)||(!px512x512 .equals(othersame.px512x512)))) {
            return false;
        }
        if ((px64x64 != othersame.px64x64)&&((px64x64 == null)||(!px64x64 .equals(othersame.px64x64)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((px128x128 == null)? 0 :px128x128 .hashCode())+((px256x256 == null)? 0 :px256x256 .hashCode()))+((px512x512 == null)? 0 :px512x512 .hashCode()))+((px64x64 == null)? 0 :px64x64 .hashCode()));
    }
}
