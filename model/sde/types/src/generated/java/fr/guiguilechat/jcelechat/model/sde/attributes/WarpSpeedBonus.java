package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Autogenerated skill attribute, warpSpeedBonus
 */
public class WarpSpeedBonus
    extends IntAttribute
{
    public static final WarpSpeedBonus INSTANCE = new WarpSpeedBonus();

    @Override
    public int getId() {
        return  601;
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
        return "WarpSpeedBonus";
    }
}