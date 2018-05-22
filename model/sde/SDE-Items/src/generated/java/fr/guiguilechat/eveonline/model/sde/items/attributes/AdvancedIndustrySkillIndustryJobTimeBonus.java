package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * A bonus to all industry job times for the advanced industry skill
 */
public class AdvancedIndustrySkillIndustryJobTimeBonus
    extends IntAttribute
{
    public final static AdvancedIndustrySkillIndustryJobTimeBonus INSTANCE = new AdvancedIndustrySkillIndustryJobTimeBonus();

    @Override
    public int getId() {
        return  1961;
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
