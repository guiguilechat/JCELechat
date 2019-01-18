package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * Booster attribute to explosion radius of missiles vs. signature radius.
 */
public class AoeCloudSizeBonus
    extends DoubleAttribute
{
    public static final AoeCloudSizeBonus INSTANCE = new AoeCloudSizeBonus();

    @Override
    public int getId() {
        return  848;
    }

    @Override
    public int getCatId() {
        return  37;
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
        return "AoeCloudSizeBonus";
    }
}
