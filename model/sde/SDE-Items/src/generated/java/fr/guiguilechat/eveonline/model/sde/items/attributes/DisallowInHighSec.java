package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Security status restriction, preventing ships from entering high sec and modules from being activated.
 */
public class DisallowInHighSec
    extends IntAttribute
{
    public final static DisallowInHighSec INSTANCE = new DisallowInHighSec();

    @Override
    public int getId() {
        return  1970;
    }

    @Override
    public int getCatId() {
        return  7;
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
}
