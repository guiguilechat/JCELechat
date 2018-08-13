package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.MetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.MetaGroup;

public abstract class Cells
    extends Item
{
    public final static Cells.MetaCat METACAT = new Cells.MetaCat();

    @Override
    public int getCategoryId() {
        return  59;
    }

    @Override
    public MetaCategory<Cells> getCategory() {
        return METACAT;
    }

    public static Map<String, ? extends Cells> loadCategory() {
        return Collections.emptyMap();
    }

    public static class MetaCat
        implements MetaCategory<Cells>
    {
        @SuppressWarnings("unchecked")
        private final static MetaGroup<? extends Cells> [] groups = new MetaGroup[] { };

        @Override
        public String getName() {
            return "Cells";
        }

        public Collection<MetaGroup<? extends Cells>> groups() {
            return Arrays.asList(groups);
        }
    }
}
