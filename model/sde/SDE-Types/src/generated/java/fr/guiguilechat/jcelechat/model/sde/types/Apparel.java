package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.types.apparel.Augmentations;
import fr.guiguilechat.jcelechat.model.sde.types.apparel.Bottoms;
import fr.guiguilechat.jcelechat.model.sde.types.apparel.Eyewear;
import fr.guiguilechat.jcelechat.model.sde.types.apparel.Footwear;
import fr.guiguilechat.jcelechat.model.sde.types.apparel.Headwear;
import fr.guiguilechat.jcelechat.model.sde.types.apparel.Masks;
import fr.guiguilechat.jcelechat.model.sde.types.apparel.Outer;
import fr.guiguilechat.jcelechat.model.sde.types.apparel.Prosthetics;
import fr.guiguilechat.jcelechat.model.sde.types.apparel.Tattoos;
import fr.guiguilechat.jcelechat.model.sde.types.apparel.Tops;

public abstract class Apparel
    extends EveType
{
    public static final Set<Attribute> ATTRIBUTES = Collections.emptySet();
    public static final Apparel.MetaCat METACAT = new Apparel.MetaCat();

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaCategory<Apparel> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Apparel>
    {

        @Override
        public int getCategoryId() {
            return  30;
        }

        @Override
        public String getName() {
            return "Apparel";
        }

        @Override
        public Collection<IMetaGroup<? extends Apparel>> groups() {
            return Arrays.asList(Eyewear.METAGROUP, Tattoos.METAGROUP, Outer.METAGROUP, Tops.METAGROUP, Bottoms.METAGROUP, Footwear.METAGROUP, Headwear.METAGROUP, Prosthetics.METAGROUP, Augmentations.METAGROUP, Masks.METAGROUP);
        }
    }
}
