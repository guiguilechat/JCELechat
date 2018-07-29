package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.DoubleAttribute;


/**
 * Sets Explosive damage taken by Armor. 
 */
public class ArmorExplosiveDamageResonancePostAssignment
    extends DoubleAttribute
{
    public final static ArmorExplosiveDamageResonancePostAssignment INSTANCE = new ArmorExplosiveDamageResonancePostAssignment();

    @Override
    public int getId() {
        return  2080;
    }

    @Override
    public int getCatId() {
        return  3;
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
        return "ArmorExplosiveDamageResonancePostAssignment";
    }
}
