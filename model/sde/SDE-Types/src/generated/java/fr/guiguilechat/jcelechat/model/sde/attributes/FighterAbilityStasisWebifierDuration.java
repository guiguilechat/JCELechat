package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Duration
 */
public class FighterAbilityStasisWebifierDuration
    extends IntAttribute
{
    public static final FighterAbilityStasisWebifierDuration INSTANCE = new FighterAbilityStasisWebifierDuration();

    @Override
    public int getId() {
        return  2183;
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
        return false;
    }

    @Override
    public String toString() {
        return "FighterAbilityStasisWebifierDuration";
    }
}
