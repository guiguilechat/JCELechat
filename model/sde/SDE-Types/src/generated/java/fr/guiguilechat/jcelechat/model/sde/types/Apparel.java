package fr.guiguilechat.jcelechat.model.sde.types;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.types.apparel.Augmentations;
import fr.guiguilechat.jcelechat.model.sde.types.apparel.Bottoms;
import fr.guiguilechat.jcelechat.model.sde.types.apparel.Eyewear;
import fr.guiguilechat.jcelechat.model.sde.types.apparel.Footwear;
import fr.guiguilechat.jcelechat.model.sde.types.apparel.Headwear;
import fr.guiguilechat.jcelechat.model.sde.types.apparel.Outer;
import fr.guiguilechat.jcelechat.model.sde.types.apparel.Prosthetics;
import fr.guiguilechat.jcelechat.model.sde.types.apparel.Tattoos;
import fr.guiguilechat.jcelechat.model.sde.types.apparel.Tops;

public abstract class Apparel
    extends EveType
{
    /**
     * The cargo space allowed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Capacity;
    /**
     * Used to describe what sex a given item is meant for.
     * 
     *  1 = Male,
     *  2 = Unisex,
     *  3 = Female
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(2)
    public int Gender;
    /**
     * Integer that describes the types mass
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double Mass;
    /**
     * Radius of an object in meters
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Radius;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {fr.guiguilechat.jcelechat.model.sde.attributes.Radius.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.Mass.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.Capacity.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.Gender.INSTANCE })));
    public static final Apparel.MetaCat METACAT = new Apparel.MetaCat();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  38 :
            {
                return Capacity;
            }
            case  1773 :
            {
                return Gender;
            }
            case  4 :
            {
                return Mass;
            }
            case  162 :
            {
                return Radius;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

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
            return Arrays.asList(Eyewear.METAGROUP, Tattoos.METAGROUP, Outer.METAGROUP, Tops.METAGROUP, Bottoms.METAGROUP, Footwear.METAGROUP, Headwear.METAGROUP, Prosthetics.METAGROUP, Augmentations.METAGROUP);
        }
    }
}
