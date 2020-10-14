package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.post_universe_names_category;

public class R_post_universe_names {
    /**
     * category string
     */
    public post_universe_names_category category;
    /**
     * id integer
     */
    public int id;
    /**
     * name string
     */
    public String name;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_post_universe_names othersame = ((R_post_universe_names) other);
        if ((category!= othersame.category)&&((category == null)||(!category.equals(othersame.category)))) {
            return false;
        }
        if (id!= othersame.id) {
            return false;
        }
        if ((name!= othersame.name)&&((name == null)||(!name.equals(othersame.name)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((category == null)? 0 :category.hashCode())+ id)+((name == null)? 0 :name.hashCode()));
    }
}
