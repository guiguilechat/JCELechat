package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Sets Em damage taken by Hull. 
 */
public class EmDamageResonancePostAssignment
    extends DoubleAttribute
{
    public final static EmDamageResonancePostAssignment INSTANCE = new EmDamageResonancePostAssignment();

    @Override
    public int getId() {
        return  2087;
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
