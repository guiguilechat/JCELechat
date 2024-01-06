package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_get_characters_character_id_opportunities {
    /**
     * completed_at string
     */
    public String completed_at;
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
        R_get_characters_character_id_opportunities othersame = ((R_get_characters_character_id_opportunities) other);
        if ((completed_at!= othersame.completed_at)&&((completed_at == null)||(!completed_at.equals(othersame.completed_at)))) {
            return false;
        }
        if (task_id!= othersame.task_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((completed_at == null)? 0 :completed_at.hashCode())+ task_id);
    }
}
