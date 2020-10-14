package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_get_corporations_corporation_id_members_titles {
    /**
     * character_id integer
     */
    public int character_id;
    /**
     * A list of title_id
     */
    public int[] titles;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_corporations_corporation_id_members_titles othersame = ((R_get_corporations_corporation_id_members_titles) other);
        if (character_id!= othersame.character_id) {
            return false;
        }
        if ((titles!= othersame.titles)&&((titles == null)||(!titles.equals(othersame.titles)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (character_id +((titles == null)? 0 :titles.hashCode()));
    }
}
