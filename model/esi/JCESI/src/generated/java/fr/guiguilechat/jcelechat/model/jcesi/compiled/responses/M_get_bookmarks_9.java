package fr.guiguilechat.jcelechat.model.jcesi.compiled.responses;

public class M_get_bookmarks_9 {
    /**
     * bookmark_id integer
     */
    public int bookmark_id;
    /**
     * Optional object that is returned if a bookmark was made on a planet or a random location in space.
     */
    public M_3_xnumber_ynumber_znumber coordinates;
    /**
     * created string
     */
    public String created;
    /**
     * creator_id integer
     */
    public int creator_id;
    /**
     * folder_id integer
     */
    public int folder_id;
    /**
     * Optional object that is returned if a bookmark was made on a particular item.
     */
    public M_get_bookmarks_item_2 item;
    /**
     * label string
     */
    public String label;
    /**
     * location_id integer
     */
    public int location_id;
    /**
     * notes string
     */
    public String notes;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        M_get_bookmarks_9 othersame = ((M_get_bookmarks_9) other);
        if (bookmark_id!= othersame.bookmark_id) {
            return false;
        }
        if ((coordinates!= othersame.coordinates)&&((coordinates == null)||(!coordinates.equals(othersame.coordinates)))) {
            return false;
        }
        if ((created!= othersame.created)&&((created == null)||(!created.equals(othersame.created)))) {
            return false;
        }
        if (creator_id!= othersame.creator_id) {
            return false;
        }
        if (folder_id!= othersame.folder_id) {
            return false;
        }
        if ((item!= othersame.item)&&((item == null)||(!item.equals(othersame.item)))) {
            return false;
        }
        if ((label!= othersame.label)&&((label == null)||(!label.equals(othersame.label)))) {
            return false;
        }
        if (location_id!= othersame.location_id) {
            return false;
        }
        if ((notes!= othersame.notes)&&((notes == null)||(!notes.equals(othersame.notes)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((((bookmark_id +((coordinates == null)? 0 :coordinates.hashCode()))+((created == null)? 0 :created.hashCode()))+ creator_id)+ folder_id)+((item == null)? 0 :item.hashCode()))+((label == null)? 0 :label.hashCode()))+ location_id)+((notes == null)? 0 :notes.hashCode()));
    }
}
