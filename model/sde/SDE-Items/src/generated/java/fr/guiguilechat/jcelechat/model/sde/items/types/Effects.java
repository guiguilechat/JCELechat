package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.MetaGroup;

public abstract class Effects
    extends Item
{
    public final static Effects.MetaCat METACAT = new Effects.MetaCat();

    @Override
    public int getCategoryId() {
        return  53;
    }

    @Override
    public MetaCategory<Effects> getCategory() {
        return METACAT;
    }

    public static Map<String, ? extends Effects> loadCategory() {
        return Collections.emptyMap();
    }

    public static class MetaCat
        implements MetaCategory<Effects>
    {
        @SuppressWarnings("unchecked")
        private final static MetaGroup<? extends Effects> [] groups = new MetaGroup[] { };

        @Override
        public String getName() {
            return "Effects";
        }

        public Collection<MetaGroup<? extends Effects>> groups() {
            return Arrays.asList(groups);
        }
    }
}
