package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * The agility of the object.
 */
public class Agility
    extends DoubleAttribute
{
    public final static Agility INSTANCE = new Agility();

    @Override
    public int getId() {
        return  70;
    }

    @Override
    public int getCatId() {
        return  17;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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

    @Override
    public String toString() {
        return "Agility";
    }
}
