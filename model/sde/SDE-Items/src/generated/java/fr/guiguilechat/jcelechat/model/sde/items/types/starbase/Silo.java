package fr.guiguilechat.jcelechat.model.sde.items.types.starbase;

import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Attribute;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.items.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultDoubleValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.items.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.items.types.Starbase;
import org.yaml.snakeyaml.Yaml;

public class Silo
    extends Starbase
{
    /**
     * The cargo group that can be loaded into this container
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CargoGroup;
    /**
     * The second cargo group that can be loaded into this container
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int CargoGroup2;
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Cpu;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int FighterAbilityAntiCapitalMissileResistance;
    /**
     * current power need
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int Power;
    /**
     * DO NOT MESS WITH This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double ShieldUniformity;
    /**
     * This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Uniformity;
    public final static Silo.MetaGroup METAGROUP = new Silo.MetaGroup();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  629 :
            {
                return CargoGroup;
            }
            case  1846 :
            {
                return CargoGroup2;
            }
            case  50 :
            {
                return Cpu;
            }
            case  2244 :
            {
                return FighterAbilityAntiCapitalMissileResistance;
            }
            case  30 :
            {
                return Power;
            }
            case  484 :
            {
                return ShieldUniformity;
            }
            case  136 :
            {
                return Uniformity;
            }
            default:
            {
                return super.attribute((attribute));
            }
        }
    }

    @Override
    public IMetaGroup<Silo> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Silo>
    {
        public final static String RESOURCE_PATH = "SDE/items/starbase/Silo.yaml";
        private Map<String, Silo> cache = (null);

        @Override
        public IMetaCategory<? super Silo> category() {
            return Starbase.METACAT;
        }

        @Override
        public int getGroupId() {
            return  404;
        }

        @Override
        public String getName() {
            return "Silo";
        }

        @Override
        public synchronized Map<String, Silo> load() {
            if (cache == null) {
                try {
                    cache = new Yaml().loadAs(new InputStreamReader(Silo.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<String, Silo> items;
        }
    }
}
