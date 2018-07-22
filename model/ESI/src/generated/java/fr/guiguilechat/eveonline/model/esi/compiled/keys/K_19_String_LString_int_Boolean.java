package fr.guiguilechat.eveonline.model.esi.compiled.keys;


/**
 * @see https://esi.evetech.net/v3/characters/{character_id}/search/
 * 
 */
public class K_19_String_LString_int_Boolean {
    public final String search;
    public final String[] categories;
    public final int character_id;
    public final Boolean strict;

    public K_19_String_LString_int_Boolean(String search, String[] categories, int character_id, Boolean strict) {
        this.search = search;
        this.categories = categories;
        this.character_id = character_id;
        this.strict = strict;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= this.getClass())) {
            return false;
        }
        K_19_String_LString_int_Boolean other2 = ((K_19_String_LString_int_Boolean) other);
        return (((((search == other2 .search)||((search!= null)&&search.equals(other2 .search)))&&((categories == other2 .categories)||((categories!= null)&&categories.equals(other2 .categories))))&&(character_id == other2 .character_id))&&((strict == other2 .strict)||((strict!= null)&&strict.equals(other2 .strict))));
    }

    public int hashCode() {
        return ((int)((((0 +((search == null)? 0 :search.hashCode()))+((categories == null)? 0 :categories.hashCode()))+ character_id)+((strict == null)? 0 :strict.hashCode())));
    }
}
