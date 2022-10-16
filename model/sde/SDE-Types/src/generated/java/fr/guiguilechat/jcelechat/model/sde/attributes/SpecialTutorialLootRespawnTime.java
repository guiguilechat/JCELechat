package fr.guiguilechat.jcelechat.model.sde.attributes;

import fr.guiguilechat.jcelechat.model.sde.IntAttribute;


/**
 * The number of milliseconds before the container replenishes the loot inside itself. This special tutorial attribute will allow re-spawning of items in distribution dungeons bypassing restrictions present. 10 second minimum (10000 ms).
 */
public class SpecialTutorialLootRespawnTime
    extends IntAttribute
{
    public static final SpecialTutorialLootRespawnTime INSTANCE = new SpecialTutorialLootRespawnTime();

    @Override
    public int getId() {
        return  1582;
    }

    @Override
    public boolean getHighIsGood() {
        return true;
    }

    @Override
    public Number getDefaultValue() {
        return  30000.0;
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
        return "SpecialTutorialLootRespawnTime";
    }
}
