package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * Sets Kinetic damage taken by Hull. 
 */
public class KineticDamageResonancePostAssignment
    extends DoubleAttribute
{
    public final static KineticDamageResonancePostAssignment INSTANCE = new KineticDamageResonancePostAssignment();

    @Override
    public int getId() {
        return  2090;
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

    @Override
    public String toString() {
        return "KineticDamageResonancePostAssignment";
    }
}
