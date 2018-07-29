package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * The maximum amount of manufacture slots that can be used at a time.
 */
public class ManufactureSlotLimit
    extends IntAttribute
{
    public final static ManufactureSlotLimit INSTANCE = new ManufactureSlotLimit();

    @Override
    public int getId() {
        return  196;
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
        return "ManufactureSlotLimit";
    }
}
