package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.MetaGroup;

public abstract class Trading
    extends Item
{
    public final static Trading.MetaCat METACAT = new Trading.MetaCat();

    @Override
    public int getCategoryId() {
        return  10;
    }

    @Override
    public MetaCategory<Trading> getCategory() {
        return METACAT;
    }

    public static Map<String, ? extends Trading> loadCategory() {
        return Collections.emptyMap();
    }

    public static class MetaCat
        implements MetaCategory<Trading>
    {
        @SuppressWarnings("unchecked")
        private final static MetaGroup<? extends Trading> [] groups = new MetaGroup[] { };

        @Override
        public String getName() {
            return "Trading";
        }

        public Collection<MetaGroup<? extends Trading>> groups() {
            return Arrays.asList(groups);
        }
    }
}
