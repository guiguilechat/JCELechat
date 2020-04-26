package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * price bonus when selling to npc corps
 * 
 */
public class TradePremium
    extends IntAttribute
{
    public static final TradePremium INSTANCE = new TradePremium();

    @Override
    public int getId() {
        return  443;
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
        return true;
    }

    @Override
    public boolean getStackable() {
        return true;
    }

    @Override
    public String toString() {
        return "TradePremium";
    }
}
