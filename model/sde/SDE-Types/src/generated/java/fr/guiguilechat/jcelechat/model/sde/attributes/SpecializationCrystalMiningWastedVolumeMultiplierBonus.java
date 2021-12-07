package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * Attribute added to the waste multiplier (mainly used in mining crystals)
 */
public class SpecializationCrystalMiningWastedVolumeMultiplierBonus
    extends IntAttribute
{
    public static final SpecializationCrystalMiningWastedVolumeMultiplierBonus INSTANCE = new SpecializationCrystalMiningWastedVolumeMultiplierBonus();

    @Override
    public int getId() {
        return  3159;
    }

    @Override
    public boolean getHighIsGood() {
        return false;
    }

    @Override
    public Number getDefaultValue() {
        return  0.0;
    }

    @Override
    public boolean getPublished() {
        return true;
    }

    @Override
    public boolean getStackable() {
        return false;
    }

    @Override
    public String toString() {
        return "SpecializationCrystalMiningWastedVolumeMultiplierBonus";
    }
}
