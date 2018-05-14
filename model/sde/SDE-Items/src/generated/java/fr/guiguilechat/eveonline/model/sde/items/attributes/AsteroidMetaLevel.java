package fr.guiguilechat.eveonline.model.sde.items.attributes;

import fr.guiguilechat.eveonline.model.sde.items.IntAttribute;


/**
 *  0: Mission/NPE Ore
 *  1: Standard Ore/Ice
 *  2: +5% Ore
 *  3: +10% Ore
 *  4: High Quality Ice or Extracted Ore
 *  5: Jackpot Moon Ore
 */
public class AsteroidMetaLevel
    extends IntAttribute
{
    public final static AsteroidMetaLevel INSTANCE = new AsteroidMetaLevel();

    @Override
    public int getId() {
        return  2699;
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
}
