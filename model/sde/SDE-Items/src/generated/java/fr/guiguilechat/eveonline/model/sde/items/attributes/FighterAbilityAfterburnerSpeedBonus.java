package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 * Maximum Velocity Bonus
 */
public class FighterAbilityAfterburnerSpeedBonus
    extends IntAttribute
{
    public final static FighterAbilityAfterburnerSpeedBonus INSTANCE = new FighterAbilityAfterburnerSpeedBonus();

    @Override
    public int getId() {
        return  2151;
    }

    @Override
    public int getCatId() {
        return  34;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public double getDefaultValue() {
        return  100.0;
    }

    @Override
    public boolean getPublished() {
        return true;
    }

    @Override
    public boolean getStackable() {
        return false;
    }
}
