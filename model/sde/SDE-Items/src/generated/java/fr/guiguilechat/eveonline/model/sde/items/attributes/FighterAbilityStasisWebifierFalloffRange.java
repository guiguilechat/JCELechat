package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Effectiveness Falloff
 */
public class FighterAbilityStasisWebifierFalloffRange
    extends IntAttribute
{
    public final static FighterAbilityStasisWebifierFalloffRange INSTANCE = new FighterAbilityStasisWebifierFalloffRange();

    @Override
    public int getId() {
        return  2187;
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
}
