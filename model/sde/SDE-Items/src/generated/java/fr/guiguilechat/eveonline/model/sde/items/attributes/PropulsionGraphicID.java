package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * The graphicID of the propulsion system.
 */
public class PropulsionGraphicID
    extends IntAttribute
{
    public final static PropulsionGraphicID INSTANCE = new PropulsionGraphicID();

    @Override
    public int getId() {
        return  217;
    }

    @Override
    public int getCatId() {
        return  31;
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
