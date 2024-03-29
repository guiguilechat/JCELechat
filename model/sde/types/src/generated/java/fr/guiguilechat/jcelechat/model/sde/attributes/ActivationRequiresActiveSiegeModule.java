package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * An effect can check this to indicate that module activation requires ship to have an active Industrial Core module.
 */
public class ActivationRequiresActiveSiegeModule
    extends IntAttribute
{
    public static final ActivationRequiresActiveSiegeModule INSTANCE = new ActivationRequiresActiveSiegeModule();

    @Override
    public int getId() {
        return  5426;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return "ActivationRequiresActiveSiegeModule";
    }
}
