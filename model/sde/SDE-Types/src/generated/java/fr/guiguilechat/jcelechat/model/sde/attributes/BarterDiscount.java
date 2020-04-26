package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * skill discount when selling to npc corps
 */
public class BarterDiscount
    extends IntAttribute
{
    public static final BarterDiscount INSTANCE = new BarterDiscount();

    @Override
    public int getId() {
        return  442;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
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

    @Override
    public String toString() {
        return "BarterDiscount";
    }
}
