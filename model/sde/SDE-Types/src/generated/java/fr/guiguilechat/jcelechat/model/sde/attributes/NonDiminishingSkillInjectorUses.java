package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Attribute defining usage count for penaltyless skill injections
 */
public class NonDiminishingSkillInjectorUses
    extends IntAttribute
{
    public static final NonDiminishingSkillInjectorUses INSTANCE = new NonDiminishingSkillInjectorUses();

    @Override
    public int getId() {
        return  2806;
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
        return  1.0;
    }

    @Override
    public boolean getPublished() {
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "NonDiminishingSkillInjectorUses";
    }
}
