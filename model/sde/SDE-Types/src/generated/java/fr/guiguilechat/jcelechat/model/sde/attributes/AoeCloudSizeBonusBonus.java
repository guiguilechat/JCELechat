package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * 
 */
public class AoeCloudSizeBonusBonus
    extends IntAttribute
{
    public static final AoeCloudSizeBonusBonus INSTANCE = new AoeCloudSizeBonusBonus();

    @Override
    public int getId() {
        return  2023;
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
        return  1399.0;
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
        return "AoeCloudSizeBonusBonus";
    }
}
