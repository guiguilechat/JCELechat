package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;

public abstract class System
    extends Item
{
    public final static System.MetaCat METACAT = new System.MetaCat();

    @Override
    public IMetaCategory<System> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<System>
    {
        @SuppressWarnings("unchecked")
        private final static IMetaGroup<? extends System> [] groups = new IMetaGroup[] { };

        @Override
        public int getCategoryId() {
            return  0;
        }

        @Override
        public String getName() {
            return "System";
        }

        @Override
        public Collection<IMetaGroup<? extends System>> groups() {
            return Arrays.asList(groups);
        }

        @Override
        public Map<String, System> load() {
            HashMap<String, System> ret = new HashMap<>();
            groups().stream().flatMap(img -> img.load().entrySet().stream()).forEach(e -> ret.put(e.getKey(), e.getValue()));
            return ret;
        }
    }
}
