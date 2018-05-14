package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * 
 */
public class FighterAbilityKamikazeResistance
    extends IntAttribute
{
    public final static FighterAbilityKamikazeResistance INSTANCE = new FighterAbilityKamikazeResistance();

    @Override
    public int getId() {
        return  2433;
    }

    @Override
    public int getCatId() {
        return  38;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public double getDefaultValue() {
        return  0.0;
    }

    @Override
    public boolean getPublished() {
        return false;
    }

    @Override
    public boolean getStackable() {
        return true;
    }
}
