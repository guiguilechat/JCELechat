package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Sets kinetic damage taken by Shields. 
 */
public class ShieldKineticDamageResonancePostAssignment
    extends DoubleAttribute
{
    public final static ShieldKineticDamageResonancePostAssignment INSTANCE = new ShieldKineticDamageResonancePostAssignment();

    @Override
    public int getId() {
        return  2085;
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
