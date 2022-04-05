package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * This attribute enables a ship to activate a Jump Conduit. Its value specifies a dogma attribute ID that a passenger ship must possess in order to be carried though that Jump Conduit.
 */
public class JumpConduitPassengerRequiredAttributeID
    extends IntAttribute
{
    public static final JumpConduitPassengerRequiredAttributeID INSTANCE = new JumpConduitPassengerRequiredAttributeID();

    @Override
    public int getId() {
        return  3321;
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
        return false;
    }

    @Override
    public String toString() {
        return "JumpConduitPassengerRequiredAttributeID";
    }
}
