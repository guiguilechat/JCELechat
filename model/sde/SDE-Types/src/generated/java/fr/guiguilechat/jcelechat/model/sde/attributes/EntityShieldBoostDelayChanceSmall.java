package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.DoubleAttribute;


/**
 * 
 */
public class EntityShieldBoostDelayChanceSmall
    extends DoubleAttribute
{
    public static final EntityShieldBoostDelayChanceSmall INSTANCE = new EntityShieldBoostDelayChanceSmall();

    @Override
    public int getId() {
        return  1006;
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
        return "EntityShieldBoostDelayChanceSmall";
    }
}