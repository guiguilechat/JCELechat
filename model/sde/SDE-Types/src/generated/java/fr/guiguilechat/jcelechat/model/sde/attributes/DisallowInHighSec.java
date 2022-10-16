package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Security status restriction, preventing ships from entering high sec and modules from being activated.
 */
public class DisallowInHighSec
    extends IntAttribute
{
    public static final DisallowInHighSec INSTANCE = new DisallowInHighSec();

    @Override
    public int getId() {
        return  1970;
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
        return "DisallowInHighSec";
    }
}
