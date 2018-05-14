package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Armor hitpoint attribute used by structures as a workaround for implementing Standup layered plating stacking penalties
 */
public class HiddenArmorHPMultiplier
    extends IntAttribute
{
    public final static HiddenArmorHPMultiplier INSTANCE = new HiddenArmorHPMultiplier();

    @Override
    public int getId() {
        return  2751;
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
        return false;
    }

    @Override
    public boolean getStackable() {
        return false;
    }
}
