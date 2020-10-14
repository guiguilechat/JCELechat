package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

public class R_get_characters_character_id_skills {
    /**
     * skills array
     */
    public get_characters_character_id_skills_skills[] skills;
    /**
     * total_sp integer
     */
    public long total_sp;
    /**
     * Skill points available to be assigned
     */
    public int unallocated_sp;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_characters_character_id_skills othersame = ((R_get_characters_character_id_skills) other);
        if ((skills!= othersame.skills)&&((skills == null)||(!skills.equals(othersame.skills)))) {
            return false;
        }
        if (total_sp!= othersame.total_sp) {
            return false;
        }
        if (unallocated_sp!= othersame.unallocated_sp) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((skills == null)? 0 :skills.hashCode())+ Long.hashCode(total_sp))+ unallocated_sp);
    }
}
