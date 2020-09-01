package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * Booster attribute to explosion radius of missiles vs. signature radius.
 */
public class AoeCloudSizeBonus
    extends RealAttribute
{
    public static final AoeCloudSizeBonus INSTANCE = new AoeCloudSizeBonus();

    @Override
    public int getId() {
        return  848;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
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
