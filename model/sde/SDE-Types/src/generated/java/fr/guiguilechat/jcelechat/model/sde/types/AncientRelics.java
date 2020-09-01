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
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.Capacity;
import fr.guiguilechat.jcelechat.model.sde.attributes.IndustryBlueprintRank;
import fr.guiguilechat.jcelechat.model.sde.attributes.Mass;
import fr.guiguilechat.jcelechat.model.sde.attributes.Radius;
import fr.guiguilechat.jcelechat.model.sde.types.ancientrelics.SleeperDefensiveRelics;
import fr.guiguilechat.jcelechat.model.sde.types.ancientrelics.SleeperEngineeringRelics;
import fr.guiguilechat.jcelechat.model.sde.types.ancientrelics.SleeperHullRelics;
import fr.guiguilechat.jcelechat.model.sde.types.ancientrelics.SleeperOffensiveRelics;
import fr.guiguilechat.jcelechat.model.sde.types.ancientrelics.SleeperPropulsionRelics;

public abstract class AncientRelics
    extends EveType
{
    /**
     * The cargo space allowed
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double capacity;
    /**
     * This is a bookkeeping attribute for blueprints, which will hopefully be deprecated by the end of 2014
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double industryblueprintrank;
    /**
     * Integer that describes the types mass
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double mass;
    /**
     * Radius of an object in meters
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double radius;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {Radius.INSTANCE, IndustryBlueprintRank.INSTANCE, Mass.INSTANCE, Capacity.INSTANCE })));
    public static final AncientRelics.MetaCat METACAT = new AncientRelics.MetaCat();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  38 :
            {
                return capacity;
            }
            case  1955 :
            {
                return industryblueprintrank;
            }
            case  4 :
            {
                return mass;
            }
            case  162 :
            {
                return radius;
            }
            default:
            {
                return super.valueSet((attribute));
            }
        }
    }

    @Override
    public Set<Attribute> getAttributes() {
        return ATTRIBUTES;
    }

    @Override
    public IMetaCategory<AncientRelics> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<AncientRelics>
    {

        @Override
        public int getCategoryId() {
            return  34;
        }

        @Override
        public String getName() {
            return "AncientRelics";
        }

        @Override
        public Collection<IMetaGroup<? extends AncientRelics>> groups() {
            return Arrays.asList(SleeperPropulsionRelics.METAGROUP, SleeperOffensiveRelics.METAGROUP, SleeperEngineeringRelics.METAGROUP, SleeperDefensiveRelics.METAGROUP, SleeperHullRelics.METAGROUP);
        }
    }
}
