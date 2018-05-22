package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Number of Heavy Fighters the ship can launch.Heavy 
 */
public class FighterHeavySlots
    extends IntAttribute
{
    public final static FighterHeavySlots INSTANCE = new FighterHeavySlots();

    @Override
    public int getId() {
        return  2219;
    }

    @Override
    public int getCatId() {
        return  38;
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
        return "FighterHeavySlots";
    }
}
