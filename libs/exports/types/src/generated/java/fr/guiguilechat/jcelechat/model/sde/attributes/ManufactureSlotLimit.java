package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The maximum amount of manufacture slots that can be used at a time.
 */
public class ManufactureSlotLimit
    extends IntAttribute
{
    public static final ManufactureSlotLimit INSTANCE = new ManufactureSlotLimit();

    @Override
    public int getId() {
        return  196;
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
