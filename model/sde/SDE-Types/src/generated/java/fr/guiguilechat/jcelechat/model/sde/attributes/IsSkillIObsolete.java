package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * When set True, skill can no longer be injected or trained. Characters will be reimbursed with free SP for any obsolete skills in the skill queue upon logon.
 */
public class IsSkillIObsolete
    extends IntAttribute
{
    public static final IsSkillIObsolete INSTANCE = new IsSkillIObsolete();

    @Override
    public int getId() {
        return  2450;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  0.0;
    }

    @Override
    public boolean getPublished() {
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "IsSkillIObsolete";
    }
}
