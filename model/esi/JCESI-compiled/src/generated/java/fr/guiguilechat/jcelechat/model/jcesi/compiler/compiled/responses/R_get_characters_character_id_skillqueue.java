package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_get_characters_character_id_skillqueue {
    /**
     * Date on which training of the skill will complete. Omitted if the skill queue is paused.
     */
    public String finish_date;
    /**
     * finished_level integer
     */
    public int finished_level;
    /**
     * level_end_sp integer
     */
    public int level_end_sp;
    /**
     * Amount of SP that was in the skill when it started training it's current level. Used to calculate % of current level complete.
     */
    public int level_start_sp;
    /**
     * queue_position integer
     */
    public int queue_position;
    /**
     * skill_id integer
     */
    public int skill_id;
    /**
     * start_date string
     */
    public String start_date;
    /**
     * training_start_sp integer
     */
    public int training_start_sp;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_characters_character_id_skillqueue othersame = ((R_get_characters_character_id_skillqueue) other);
        if ((finish_date!= othersame.finish_date)&&((finish_date == null)||(!finish_date.equals(othersame.finish_date)))) {
            return false;
        }
        if (finished_level!= othersame.finished_level) {
            return false;
        }
        if (level_end_sp!= othersame.level_end_sp) {
            return false;
        }
        if (level_start_sp!= othersame.level_start_sp) {
            return false;
        }
        if (queue_position!= othersame.queue_position) {
            return false;
        }
        if (skill_id!= othersame.skill_id) {
            return false;
        }
        if ((start_date!= othersame.start_date)&&((start_date == null)||(!start_date.equals(othersame.start_date)))) {
            return false;
        }
        if (training_start_sp!= othersame.training_start_sp) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((((finish_date == null)? 0 :finish_date.hashCode())+ finished_level)+ level_end_sp)+ level_start_sp)+ queue_position)+ skill_id)+((start_date == null)? 0 :start_date.hashCode()))+ training_start_sp);
    }
}
