package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Skill attribute for increasing strength of Propulsion modules.
 */
public class PropulsionSkillPropulsionStrengthBonus
    extends IntAttribute
{
    public final static PropulsionSkillPropulsionStrengthBonus INSTANCE = new PropulsionSkillPropulsionStrengthBonus();

    @Override
    public int getId() {
        return  829;
    }

    @Override
    public int getCatId() {
        return  9;
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
}
