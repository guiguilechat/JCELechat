package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The amount of time before applications of the cloud's effect.
 */
public class CloudEffectDelay
    extends IntAttribute
{
    public static final CloudEffectDelay INSTANCE = new CloudEffectDelay();

    @Override
    public int getId() {
        return  544;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "CloudEffectDelay";
    }
}
