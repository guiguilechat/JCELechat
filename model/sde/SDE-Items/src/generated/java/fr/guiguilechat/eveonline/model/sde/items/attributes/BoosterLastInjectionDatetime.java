package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * The last allowed injection date.  After this date the booster can no longer be consumed.
 */
public class BoosterLastInjectionDatetime
    extends DoubleAttribute
{
    public final static BoosterLastInjectionDatetime INSTANCE = new BoosterLastInjectionDatetime();

    @Override
    public int getId() {
        return  2422;
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
}
