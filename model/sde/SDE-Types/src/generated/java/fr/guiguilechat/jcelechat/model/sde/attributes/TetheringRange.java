package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Distance which tethering will engage / disengage piloted ships.
 */
public class TetheringRange
    extends IntAttribute
{
    public static final TetheringRange INSTANCE = new TetheringRange();

    @Override
    public int getId() {
        return  2268;
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

    @Override
    public String toString() {
        return "TetheringRange";
    }
}
