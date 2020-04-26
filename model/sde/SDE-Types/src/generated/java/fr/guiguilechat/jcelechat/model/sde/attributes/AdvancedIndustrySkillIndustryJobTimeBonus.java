package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * A bonus to all industry job times for the advanced industry skill
 */
public class AdvancedIndustrySkillIndustryJobTimeBonus
    extends IntAttribute
{
    public static final AdvancedIndustrySkillIndustryJobTimeBonus INSTANCE = new AdvancedIndustrySkillIndustryJobTimeBonus();

    @Override
    public int getId() {
        return  1961;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "AdvancedIndustrySkillIndustryJobTimeBonus";
    }
}
