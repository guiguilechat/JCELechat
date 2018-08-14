package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;

public abstract class Infantry
    extends Item
{
    public final static Infantry.MetaCat METACAT = new Infantry.MetaCat();

    @Override
    public IMetaCategory<Infantry> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Infantry>
    {
        @SuppressWarnings("unchecked")
        private final static IMetaGroup<? extends Infantry> [] groups = new IMetaGroup[] { };

        @Override
        public int getCategoryId() {
            return  350001;
        }

        @Override
        public String getName() {
            return "Infantry";
        }

        @Override
        public Collection<IMetaGroup<? extends Infantry>> groups() {
            return Arrays.asList(groups);
        }

        @Override
        public Map<String, Infantry> load() {
            HashMap<String, Infantry> ret = new HashMap<>();
            groups().stream().flatMap(img -> img.load().entrySet().stream()).forEach(e -> ret.put(e.getKey(), e.getValue()));
            return ret;
        }
    }
}
