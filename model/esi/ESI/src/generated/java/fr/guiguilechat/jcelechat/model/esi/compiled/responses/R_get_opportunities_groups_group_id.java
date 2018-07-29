package fr.guiguilechat.jcelechat.model.esi.compiled.responses;

public class R_get_opportunities_groups_group_id {
    /**
     * The groups that are connected to this group on the opportunities map
     */
    public int[] connected_groups;
    /**
     * description string
     */
    public String description;
    /**
     * group_id integer
     */
    public int group_id;
    /**
     * name string
     */
    public String name;
    /**
     * notification string
     */
    public String notification;
    /**
     * Tasks need to complete for this group
     */
    public int[] required_tasks;
}
