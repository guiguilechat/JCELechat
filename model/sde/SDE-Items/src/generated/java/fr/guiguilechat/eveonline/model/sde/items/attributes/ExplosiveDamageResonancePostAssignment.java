package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Sets Explosive damage taken by Hull. 
 */
public class ExplosiveDamageResonancePostAssignment
    extends DoubleAttribute
{
    public final static ExplosiveDamageResonancePostAssignment INSTANCE = new ExplosiveDamageResonancePostAssignment();

    @Override
    public int getId() {
        return  2088;
    }

    @Override
    public int getCatId() {
        return  4;
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
