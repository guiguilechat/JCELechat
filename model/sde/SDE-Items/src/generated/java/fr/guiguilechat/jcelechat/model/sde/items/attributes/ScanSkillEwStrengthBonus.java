package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * Skill attribute for increasing strength of EW modules.
 */
public class ScanSkillEwStrengthBonus
    extends DoubleAttribute
{
    public final static ScanSkillEwStrengthBonus INSTANCE = new ScanSkillEwStrengthBonus();

    @Override
    public int getId() {
        return  828;
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
        return false;
    }

    @Override
    public String toString() {
        return "ScanSkillEwStrengthBonus";
    }
}
