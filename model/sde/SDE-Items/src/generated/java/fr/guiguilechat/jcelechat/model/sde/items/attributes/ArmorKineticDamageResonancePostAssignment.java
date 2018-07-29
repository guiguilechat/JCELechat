package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * Sets Kinetic damage taken by Armor. 
 */
public class ArmorKineticDamageResonancePostAssignment
    extends DoubleAttribute
{
    public final static ArmorKineticDamageResonancePostAssignment INSTANCE = new ArmorKineticDamageResonancePostAssignment();

    @Override
    public int getId() {
        return  2081;
    }

    @Override
    public int getCatId() {
        return  3;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
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
        return "ArmorKineticDamageResonancePostAssignment";
    }
}
