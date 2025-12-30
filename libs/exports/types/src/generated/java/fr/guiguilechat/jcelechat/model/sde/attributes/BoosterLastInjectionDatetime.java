package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.RealAttribute;


/**
 * The last allowed injection date.  After this date the booster can no longer be consumed. Formatted YYYY.MM.DD HH:MM:SS
 */
public class BoosterLastInjectionDatetime
    extends RealAttribute
{
    public static final BoosterLastInjectionDatetime INSTANCE = new BoosterLastInjectionDatetime();

    @Override
    public int getId() {
        return  2422;
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
        return "BoosterLastInjectionDatetime";
    }
}
