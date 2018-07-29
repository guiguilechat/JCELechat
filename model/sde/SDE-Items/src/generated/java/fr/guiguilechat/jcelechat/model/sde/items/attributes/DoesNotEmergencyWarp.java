package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * This is a devhax attribute that prevents you from e-warping on logon or logoff
 */
public class DoesNotEmergencyWarp
    extends IntAttribute
{
    public final static DoesNotEmergencyWarp INSTANCE = new DoesNotEmergencyWarp();

    @Override
    public int getId() {
        return  1854;
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
        return "DoesNotEmergencyWarp";
    }
}
