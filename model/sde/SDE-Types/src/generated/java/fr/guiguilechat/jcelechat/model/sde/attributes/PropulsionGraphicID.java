package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The graphicID of the propulsion system.
 */
public class PropulsionGraphicID
    extends IntAttribute
{
    public static final PropulsionGraphicID INSTANCE = new PropulsionGraphicID();

    @Override
    public int getId() {
        return  217;
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
        return "PropulsionGraphicID";
    }
}
