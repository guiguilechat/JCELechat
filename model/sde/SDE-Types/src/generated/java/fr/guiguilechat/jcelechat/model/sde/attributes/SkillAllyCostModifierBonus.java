package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class SkillAllyCostModifierBonus
    extends IntAttribute
{
    public static final SkillAllyCostModifierBonus INSTANCE = new SkillAllyCostModifierBonus();

    @Override
    public int getId() {
        return  1821;
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
        return "SkillAllyCostModifierBonus";
    }
}
