package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * This defines the total capacity of fighters allowed in the fighter bay of the ship
 */
public class FighterCapacity
    extends IntAttribute
{
    public static final FighterCapacity INSTANCE = new FighterCapacity();

    @Override
    public int getId() {
        return  2055;
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
        return "FighterCapacity";
    }
}
