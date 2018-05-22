package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * If module is offensive should it deactivate on disconnect. Default to 1
 */
public class DeactivateIfOffensive
    extends IntAttribute
{
    public final static DeactivateIfOffensive INSTANCE = new DeactivateIfOffensive();

    @Override
    public int getId() {
        return  1934;
    }

    @Override
    public int getCatId() {
        return  0;
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
