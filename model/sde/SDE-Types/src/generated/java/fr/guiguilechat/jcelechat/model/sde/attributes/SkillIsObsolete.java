package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * When set True, skill can no longer be injected or trained. Characters will be reimbursed with free SP for any obsolete skills in the skill queue upon logon.
 */
public class SkillIsObsolete
    extends IntAttribute
{
    public static final SkillIsObsolete INSTANCE = new SkillIsObsolete();

    @Override
    public int getId() {
        return  2450;
    }

    @Override
    public int getCatId() {
        return  7;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
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
        return "SkillIsObsolete";
    }
}
