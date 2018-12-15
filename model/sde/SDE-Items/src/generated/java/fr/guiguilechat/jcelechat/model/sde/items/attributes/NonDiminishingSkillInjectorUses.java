package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Attribute defining usage count for penaltyless skill injections
 */
public class NonDiminishingSkillInjectorUses
    extends IntAttribute
{
    public final static NonDiminishingSkillInjectorUses INSTANCE = new NonDiminishingSkillInjectorUses();

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
