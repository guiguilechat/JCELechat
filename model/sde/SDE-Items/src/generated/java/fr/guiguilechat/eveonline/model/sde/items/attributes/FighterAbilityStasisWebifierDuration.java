package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Duration
 */
public class FighterAbilityStasisWebifierDuration
    extends IntAttribute
{
    public final static FighterAbilityStasisWebifierDuration INSTANCE = new FighterAbilityStasisWebifierDuration();

    @Override
    public int getId() {
        return  2183;
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
