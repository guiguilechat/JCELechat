package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * This defines the total number of fighter launch tubes on the ship.
 */
public class FighterTubes
    extends IntAttribute
{
    public static final FighterTubes INSTANCE = new FighterTubes();

    @Override
    public int getId() {
        return  2216;
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
        return "FighterTubes";
    }
}
