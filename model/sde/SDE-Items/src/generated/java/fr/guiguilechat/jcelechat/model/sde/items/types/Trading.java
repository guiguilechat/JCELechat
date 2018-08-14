package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;

public abstract class Trading
    extends Item
{
    public final static Trading.MetaCat METACAT = new Trading.MetaCat();

    @Override
    public IMetaCategory<Trading> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Trading>
    {
        @SuppressWarnings("unchecked")
        private final static IMetaGroup<? extends Trading> [] groups = new IMetaGroup[] { };

        @Override
        public int getCategoryId() {
            return  10;
        }

        @Override
        public String getName() {
            return "Trading";
        }

        @Override
        public Collection<IMetaGroup<? extends Trading>> groups() {
            return Arrays.asList(groups);
        }

        @Override
        public Map<String, Trading> load() {
            HashMap<String, Trading> ret = new HashMap<>();
            groups().stream().flatMap(img -> img.load().entrySet().stream()).forEach(e -> ret.put(e.getKey(), e.getValue()));
            return ret;
        }
    }
}
