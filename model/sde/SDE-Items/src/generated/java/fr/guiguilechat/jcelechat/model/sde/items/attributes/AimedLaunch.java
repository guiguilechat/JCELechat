package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * Determines wether a missile launches aligned with the ship (0) or directly at the target (1).
 */
public class AimedLaunch
    extends IntAttribute
{
    public final static AimedLaunch INSTANCE = new AimedLaunch();

    @Override
    public int getId() {
        return  644;
    }

    @Override
    public int getCatId() {
        return  9;
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
        return "AimedLaunch";
    }
}