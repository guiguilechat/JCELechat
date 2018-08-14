package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;

public abstract class Effects
    extends Item
{
    public final static Effects.MetaCat METACAT = new Effects.MetaCat();

    @Override
    public IMetaCategory<Effects> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Effects>
    {
        @SuppressWarnings("unchecked")
        private final static IMetaGroup<? extends Effects> [] groups = new IMetaGroup[] { };

        @Override
        public int getCategoryId() {
            return  53;
        }

        @Override
        public String getName() {
            return "Effects";
        }

        @Override
        public Collection<IMetaGroup<? extends Effects>> groups() {
            return Arrays.asList(groups);
        }

        @Override
        public Map<String, Effects> load() {
            HashMap<String, Effects> ret = new HashMap<>();
            groups().stream().flatMap(img -> img.load().entrySet().stream()).forEach(e -> ret.put(e.getKey(), e.getValue()));
            return ret;
        }
    }
}
