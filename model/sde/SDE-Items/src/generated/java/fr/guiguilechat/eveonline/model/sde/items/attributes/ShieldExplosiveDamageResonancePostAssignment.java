package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Sets Explosive damage taken by shields. 
 */
public class ShieldExplosiveDamageResonancePostAssignment
    extends DoubleAttribute
{
    public final static ShieldExplosiveDamageResonancePostAssignment INSTANCE = new ShieldExplosiveDamageResonancePostAssignment();

    @Override
    public int getId() {
        return  2084;
    }

    @Override
    public int getCatId() {
        return  2;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public double getDefaultValue() {
        return  1.0;
    }

    @Override
    public boolean getPublished() {
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }
}
