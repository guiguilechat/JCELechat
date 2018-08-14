package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.types.owner.Character;

public abstract class Owner
    extends Item
{
    public final static Owner.MetaCat METACAT = new Owner.MetaCat();

    @Override
    public IMetaCategory<Owner> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Owner>
    {
        @SuppressWarnings("unchecked")
        private final static IMetaGroup<? extends Owner> [] groups = new IMetaGroup[] {Character.METAGROUP };

        @Override
        public int getCategoryId() {
            return  1;
        }

        @Override
        public String getName() {
            return "Owner";
        }

        @Override
        public Collection<IMetaGroup<? extends Owner>> groups() {
            return Arrays.asList(groups);
        }

        @Override
        public Map<String, Owner> load() {
            HashMap<String, Owner> ret = new HashMap<>();
            groups().stream().flatMap(img -> img.load().entrySet().stream()).forEach(e -> ret.put(e.getKey(), e.getValue()));
            return ret;
        }
    }
}
