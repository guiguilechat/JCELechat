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
}
