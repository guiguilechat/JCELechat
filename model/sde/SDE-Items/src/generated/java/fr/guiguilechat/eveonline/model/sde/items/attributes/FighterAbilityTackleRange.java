package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Range
 */
public class FighterAbilityTackleRange
    extends IntAttribute
{
    public final static FighterAbilityTackleRange INSTANCE = new FighterAbilityTackleRange();

    @Override
    public int getId() {
        return  2239;
    }

    @Override
    public int getCatId() {
        return  34;
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
        return false;
    }

    @Override
    public String toString() {
        return "FighterAbilityTackleRange";
    }
}
