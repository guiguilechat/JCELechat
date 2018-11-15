package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_get_opportunities_tasks_task_id {
    /**
     * description string
     */
    public String description;
    /**
     * name string
     */
    public String name;
    /**
     * notification string
     */
    public String notification;
    /**
     * task_id integer
     */
    public int task_id;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_opportunities_tasks_task_id othersame = ((R_get_opportunities_tasks_task_id) other);
        if ((description!= othersame.description)&&((description == null)||(!description.equals(othersame.description)))) {
            return false;
        }
        if ((name!= othersame.name)&&((name == null)||(!name.equals(othersame.name)))) {
            return false;
        }
        if ((notification!= othersame.notification)&&((notification == null)||(!notification.equals(othersame.notification)))) {
            return false;
        }
        if (task_id!= othersame.task_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((description == null)? 0 :description.hashCode())+((name == null)? 0 :name.hashCode()))+((notification == null)? 0 :notification.hashCode()))+ task_id);
    }
}
