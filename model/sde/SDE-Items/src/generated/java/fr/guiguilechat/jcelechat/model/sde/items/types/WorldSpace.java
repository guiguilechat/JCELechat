package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;

public abstract class WorldSpace
    extends Item
{
    public final static WorldSpace.MetaCat METACAT = new WorldSpace.MetaCat();

    @Override
    public IMetaCategory<WorldSpace> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<WorldSpace>
    {
        @SuppressWarnings("unchecked")
        private final static IMetaGroup<? extends WorldSpace> [] groups = new IMetaGroup[] {fr.guiguilechat.jcelechat.model.sde.items.types.worldspace.WorldSpace.METAGROUP };

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
            return Arrays.asList(groups);
        }

        @Override
        public Map<String, WorldSpace> load() {
            HashMap<String, WorldSpace> ret = new HashMap<>();
            groups().stream().flatMap(img -> img.load().entrySet().stream()).forEach(e -> ret.put(e.getKey(), e.getValue()));
            return ret;
        }
    }
}
