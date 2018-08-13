package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.MetaGroup;

public abstract class Infantry
    extends Item
{
    public final static Infantry.MetaCat METACAT = new Infantry.MetaCat();

    @Override
    public int getCategoryId() {
        return  350001;
    }

    @Override
    public MetaCategory<Infantry> getCategory() {
        return METACAT;
    }

    public static Map<String, ? extends Infantry> loadCategory() {
        return Collections.emptyMap();
    }

    public static class MetaCat
        implements MetaCategory<Infantry>
    {
        @SuppressWarnings("unchecked")
        private final static MetaGroup<? extends Infantry> [] groups = new MetaGroup[] { };

        @Override
        public String getName() {
            return "Infantry";
        }

        public Collection<MetaGroup<? extends Infantry>> groups() {
            return Arrays.asList(groups);
        }
    }
}
