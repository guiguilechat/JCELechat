package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Sets Em damage taken by Shields. 
 */
public class ShieldEmDamageResonancePostAssignment
    extends DoubleAttribute
{
    public final static ShieldEmDamageResonancePostAssignment INSTANCE = new ShieldEmDamageResonancePostAssignment();

    @Override
    public int getId() {
        return  2083;
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
