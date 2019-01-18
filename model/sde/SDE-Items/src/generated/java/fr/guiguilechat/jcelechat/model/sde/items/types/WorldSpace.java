package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;

public abstract class WorldSpace
    extends Item
{
    public static final WorldSpace.MetaCat METACAT = new WorldSpace.MetaCat();

    @Override
    public IMetaCategory<WorldSpace> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<WorldSpace>
    {

        @Override
        public int getCategoryId() {
            return  26;
        }

        @Override
        public String getName() {
            return "WorldSpace";
        }

        @Override
        public Collection<IMetaGroup<? extends WorldSpace>> groups() {
            return Arrays.asList(fr.guiguilechat.jcelechat.model.sde.items.types.worldspace.WorldSpace.METAGROUP);
        }
    }
}
