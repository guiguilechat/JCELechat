package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.MetaGroup;

public abstract class System
    extends Item
{
    public final static System.MetaCat METACAT = new System.MetaCat();

    @Override
    public int getCategoryId() {
        return  0;
    }

    @Override
    public MetaCategory<System> getCategory() {
        return METACAT;
    }

    public static Map<String, ? extends System> loadCategory() {
        return Collections.emptyMap();
    }

    public static class MetaCat
        implements MetaCategory<System>
    {
        @SuppressWarnings("unchecked")
        private final static MetaGroup<? extends System> [] groups = new MetaGroup[] { };

        @Override
        public String getName() {
            return "System";
        }

        public Collection<MetaGroup<? extends System>> groups() {
            return Arrays.asList(groups);
        }
    }
}
