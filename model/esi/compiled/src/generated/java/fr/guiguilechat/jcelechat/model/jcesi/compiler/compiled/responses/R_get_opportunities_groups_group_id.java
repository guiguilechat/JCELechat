package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

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

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_opportunities_groups_group_id othersame = ((R_get_opportunities_groups_group_id) other);
        if ((connected_groups!= othersame.connected_groups)&&((connected_groups == null)||(!connected_groups.equals(othersame.connected_groups)))) {
            return false;
        }
        if ((description!= othersame.description)&&((description == null)||(!description.equals(othersame.description)))) {
            return false;
        }
        if (group_id!= othersame.group_id) {
            return false;
        }
        if ((name!= othersame.name)&&((name == null)||(!name.equals(othersame.name)))) {
            return false;
        }
        if ((notification!= othersame.notification)&&((notification == null)||(!notification.equals(othersame.notification)))) {
            return false;
        }
        if ((required_tasks!= othersame.required_tasks)&&((required_tasks == null)||(!required_tasks.equals(othersame.required_tasks)))) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((connected_groups == null)? 0 :connected_groups.hashCode())+((description == null)? 0 :description.hashCode()))+ group_id)+((name == null)? 0 :name.hashCode()))+((notification == null)? 0 :notification.hashCode()))+((required_tasks == null)? 0 :required_tasks.hashCode()));
    }
}
