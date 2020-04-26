package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * If module is offensive should it deactivate on disconnect. Default to 1
 */
public class DeactivateIfOffensive
    extends IntAttribute
{
    public static final DeactivateIfOffensive INSTANCE = new DeactivateIfOffensive();

    @Override
    public int getId() {
        return  1934;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  1.0;
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
        return "DeactivateIfOffensive";
    }
}
