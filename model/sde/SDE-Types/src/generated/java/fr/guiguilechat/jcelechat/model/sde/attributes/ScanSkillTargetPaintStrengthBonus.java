package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Skill attribute for increasing effectiveness on Target Painters
 */
public class ScanSkillTargetPaintStrengthBonus
    extends IntAttribute
{
    public static final ScanSkillTargetPaintStrengthBonus INSTANCE = new ScanSkillTargetPaintStrengthBonus();

    @Override
    public int getId() {
        return  832;
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
        return "ScanSkillTargetPaintStrengthBonus";
    }
}
