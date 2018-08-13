package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.MetaGroup;

public abstract class Station
    extends Item
{
    public final static Station.MetaCat METACAT = new Station.MetaCat();

    @Override
    public int getCategoryId() {
        return  3;
    }

    @Override
    public MetaCategory<Station> getCategory() {
        return METACAT;
    }

    public static Map<String, ? extends Station> loadCategory() {
        return Collections.emptyMap();
    }

    public static class MetaCat
        implements MetaCategory<Station>
    {
        @SuppressWarnings("unchecked")
        private final static MetaGroup<? extends Station> [] groups = new MetaGroup[] { };

        @Override
        public String getName() {
            return "Station";
        }

        public Collection<MetaGroup<? extends Station>> groups() {
            return Arrays.asList(groups);
        }
    }
}
