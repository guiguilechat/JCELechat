package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;

public abstract class Station
    extends Item
{
    public final static Station.MetaCat METACAT = new Station.MetaCat();

    @Override
    public IMetaCategory<Station> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Station>
    {
        @SuppressWarnings("unchecked")
        private final static IMetaGroup<? extends Station> [] groups = new IMetaGroup[] { };

        @Override
        public int getCategoryId() {
            return  3;
        }

        @Override
        public String getName() {
            return "Station";
        }

        @Override
        public Collection<IMetaGroup<? extends Station>> groups() {
            return Arrays.asList(groups);
        }

        @Override
        public Map<String, Station> load() {
            HashMap<String, Station> ret = new HashMap<>();
            groups().stream().flatMap(img -> img.load().entrySet().stream()).forEach(e -> ret.put(e.getKey(), e.getValue()));
            return ret;
        }
    }
}
