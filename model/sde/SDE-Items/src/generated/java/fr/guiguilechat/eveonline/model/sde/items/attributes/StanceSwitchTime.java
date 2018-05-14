package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Time in milliseconds that it takes to switch stances.
 */
public class StanceSwitchTime
    extends IntAttribute
{
    public final static StanceSwitchTime INSTANCE = new StanceSwitchTime();

    @Override
    public int getId() {
        return  1985;
    }

    @Override
    public int getCatId() {
        return  1;
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
