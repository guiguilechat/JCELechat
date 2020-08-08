package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Used to increase signature radius of entity when it activates Max Velocity. Used to fake MWD sig radius increase.
 */
public class EntityMaxVelocitySignatureRadiusMultiplier
    extends IntAttribute
{
    public static final EntityMaxVelocitySignatureRadiusMultiplier INSTANCE = new EntityMaxVelocitySignatureRadiusMultiplier();

    @Override
    public int getId() {
        return  1133;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  6.0;
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
        return "EntityMaxVelocitySignatureRadiusMultiplier";
    }
}
