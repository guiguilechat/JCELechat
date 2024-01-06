package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.keys;


/**
 * @see https://esi.evetech.net/v3/characters/{character_id}/search/
 * 
 */
public class K_18_String_LString_int_Boolean {
    public final String search;
    public final String[] categories;
    public final int character_id;
    public final Boolean strict;

    public K_18_String_LString_int_Boolean(String search,
        String[] categories,
        int character_id,
        Boolean strict) {
        this.search = search;
        this.categories = categories;
        this.character_id = character_id;
        this.strict = strict;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        K_18_String_LString_int_Boolean othersame = ((K_18_String_LString_int_Boolean) other);
        if ((search!= othersame.search)&&((search == null)||(!search.equals(othersame.search)))) {
            return false;
        }
        if ((categories!= othersame.categories)&&((categories == null)||(!categories.equals(othersame.categories)))) {
            return false;
        }
        if (character_id!= othersame.character_id) {
            return false;
        }
        if ((strict!= othersame.strict)&&((strict == null)||(!strict.equals(othersame.strict)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((search == null)? 0 :search.hashCode())+((categories == null)? 0 :categories.hashCode()))+ character_id)+((strict == null)? 0 :strict.hashCode()));
    }
}
