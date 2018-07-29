package fr.guiguilechat.jcelechat.model.sde.items.attributes;

import fr.guiguilechat.jcelechat.model.sde.items.IntAttribute;


/**
 * + / - modifier to a ship gravimetric strength
 */
public class ScanGravimetricStrengthModifier
    extends IntAttribute
{
    public final static ScanGravimetricStrengthModifier INSTANCE = new ScanGravimetricStrengthModifier();

    @Override
    public int getId() {
        return  1567;
    }

    @Override
    public int getCatId() {
        return  6;
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
        return "ScanGravimetricStrengthModifier";
    }
}
