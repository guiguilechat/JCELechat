package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Skill attribute for increasing strength of EW modules.
 */
public class ScanSkillEwStrengthBonus
    extends DoubleAttribute
{
    public static final ScanSkillEwStrengthBonus INSTANCE = new ScanSkillEwStrengthBonus();

    @Override
    public int getId() {
        return  828;
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
        return false;
    }

    @Override
    public String toString() {
        return "ScanSkillEwStrengthBonus";
    }
}
