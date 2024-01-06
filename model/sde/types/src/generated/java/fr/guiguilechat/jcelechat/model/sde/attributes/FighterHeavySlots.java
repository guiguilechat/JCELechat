package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Number of Heavy Fighters the ship can launch.Heavy 
 */
public class FighterHeavySlots
    extends IntAttribute
{
    public static final FighterHeavySlots INSTANCE = new FighterHeavySlots();

    @Override
    public int getId() {
        return  2219;
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
        return "FighterHeavySlots";
    }
}
