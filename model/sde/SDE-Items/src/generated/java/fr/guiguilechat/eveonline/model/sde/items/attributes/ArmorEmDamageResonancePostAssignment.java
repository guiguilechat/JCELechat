package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.DoubleAttribute;


/**
 * Sets Em damage taken by Armor. 
 */
public class ArmorEmDamageResonancePostAssignment
    extends DoubleAttribute
{
    public final static ArmorEmDamageResonancePostAssignment INSTANCE = new ArmorEmDamageResonancePostAssignment();

    @Override
    public int getId() {
        return  2079;
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
        return "ArmorEmDamageResonancePostAssignment";
    }
}
