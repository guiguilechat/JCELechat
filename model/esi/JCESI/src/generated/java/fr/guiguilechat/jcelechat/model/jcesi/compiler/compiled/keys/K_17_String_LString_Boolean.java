package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.keys;


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

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        K_17_String_LString_Boolean othersame = ((K_17_String_LString_Boolean) other);
        if ((search!= othersame.search)&&((search == null)||(!search.equals(othersame.search)))) {
            return false;
        }
        if ((categories!= othersame.categories)&&((categories == null)||(!categories.equals(othersame.categories)))) {
            return false;
        }
        if ((strict!= othersame.strict)&&((strict == null)||(!strict.equals(othersame.strict)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((search == null)? 0 :search.hashCode())+((categories == null)? 0 :categories.hashCode()))+((strict == null)? 0 :strict.hashCode()));
    }
}
