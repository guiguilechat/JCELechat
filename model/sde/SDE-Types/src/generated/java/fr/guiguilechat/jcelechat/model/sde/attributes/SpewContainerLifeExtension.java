package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * If present, will add the given value to the automatic computed lifetime of MiniContainers with regards to the time required to take them and the amount of containers scattered out into space.
 */
public class SpewContainerLifeExtension
    extends IntAttribute
{
    public static final SpewContainerLifeExtension INSTANCE = new SpewContainerLifeExtension();

    @Override
    public int getId() {
        return  1917;
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
        return "SpewContainerLifeExtension";
    }
}
