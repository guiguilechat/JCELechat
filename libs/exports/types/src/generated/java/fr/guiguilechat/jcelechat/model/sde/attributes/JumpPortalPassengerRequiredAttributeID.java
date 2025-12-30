package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * This attribute enables a ship to open a Jump Portal. Its value specifies a dogma attribute ID that a passenger ship must possess in order to travel through that Jump Portal.
 */
public class JumpPortalPassengerRequiredAttributeID
    extends IntAttribute
{
    public static final JumpPortalPassengerRequiredAttributeID INSTANCE = new JumpPortalPassengerRequiredAttributeID();

    @Override
    public int getId() {
        return  3318;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
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
        return "JumpPortalPassengerRequiredAttributeID";
    }
}
