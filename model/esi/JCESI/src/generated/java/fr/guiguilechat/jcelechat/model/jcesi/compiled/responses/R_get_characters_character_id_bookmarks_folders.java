package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class R_get_characters_character_id_bookmarks_folders {
    /**
     * folder_id integer
     */
    public int folder_id;
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
        R_get_characters_character_id_bookmarks_folders othersame = ((R_get_characters_character_id_bookmarks_folders) other);
        if (folder_id!= othersame.folder_id) {
            return false;
        }
        if ((name!= othersame.name)&&((name == null)||(!name.equals(othersame.name)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (folder_id +((name == null)? 0 :name.hashCode()));
    }
}
