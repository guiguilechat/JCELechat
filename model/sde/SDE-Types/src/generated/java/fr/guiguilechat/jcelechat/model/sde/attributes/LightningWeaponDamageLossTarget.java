package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * Damage lost per target hit
 */
public class LightningWeaponDamageLossTarget
    extends DoubleAttribute
{
    public static final LightningWeaponDamageLossTarget INSTANCE = new LightningWeaponDamageLossTarget();

    @Override
    public int getId() {
        return  2106;
    }

    @Override
    public int getCatId() {
        return  7;
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
        return "LightningWeaponDamageLossTarget";
    }
}
