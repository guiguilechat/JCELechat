package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class get_characters_character_id_skills_skills {
    /**
     * active_skill_level integer
     */
    public int active_skill_level;
    /**
     * skill_id integer
     */
    public int skill_id;
    /**
     * skillpoints_in_skill integer
     */
    public long skillpoints_in_skill;
    /**
     * trained_skill_level integer
     */
    public int trained_skill_level;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        get_characters_character_id_skills_skills othersame = ((get_characters_character_id_skills_skills) other);
        if (active_skill_level!= othersame.active_skill_level) {
            return false;
        }
        if (skill_id!= othersame.skill_id) {
            return false;
        }
        if (skillpoints_in_skill!= othersame.skillpoints_in_skill) {
            return false;
        }
        if (trained_skill_level!= othersame.trained_skill_level) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((active_skill_level + skill_id)+ Long.hashCode(skillpoints_in_skill))+ trained_skill_level);
    }
}
