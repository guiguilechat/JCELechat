package fr.guiguilechat.jcelechat.model.jcesi.compiled.keys;


/**
 * @see https://esi.evetech.net/v2/search/
 * 
 */
public class K_17_String_LString_Boolean {
    public final String search;
    public final String[] categories;
    public final Boolean strict;

    public K_17_String_LString_Boolean(String search, String[] categories, Boolean strict) {
        this.search = search;
        this.categories = categories;
        this.strict = strict;
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= this.getClass())) {
            return false;
        }
        K_17_String_LString_Boolean other2 = ((K_17_String_LString_Boolean) other);
        return ((((search == other2 .search)||((search!= null)&&search.equals(other2 .search)))&&((categories == other2 .categories)||((categories!= null)&&categories.equals(other2 .categories))))&&((strict == other2 .strict)||((strict!= null)&&strict.equals(other2 .strict))));
    }

    public int hashCode() {
        return ((int)(((0 +((search == null)? 0 :search.hashCode()))+((categories == null)? 0 :categories.hashCode()))+((strict == null)? 0 :strict.hashCode())));
    }
}
