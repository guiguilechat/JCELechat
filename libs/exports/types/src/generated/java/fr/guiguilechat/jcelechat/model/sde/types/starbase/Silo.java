package fr.guiguilechat.jcelechat.model.sde.types.starbase;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import fr.guiguilechat.jcelechat.model.sde.Attribute;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultIntValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.DefaultRealValue;
import fr.guiguilechat.jcelechat.model.sde.annotations.HighIsGood;
import fr.guiguilechat.jcelechat.model.sde.annotations.Stackable;
import fr.guiguilechat.jcelechat.model.sde.attributes.*;
import fr.guiguilechat.jcelechat.model.sde.types.Starbase;

public class Silo
    extends Starbase
{
    /**
     * The cargo group that can be loaded into this container
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int cargogroup;
    /**
     * The second cargo group that can be loaded into this container
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int cargogroup2;
    /**
     * CPU need of module
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double cpu;
    /**
     * 
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int fighterabilityanticapitalmissileresistance;
    /**
     * current power need
     */
    @HighIsGood(false)
    @Stackable(true)
    @DefaultIntValue(0)
    public int power;
    /**
     * DO NOT MESS WITH This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shielduniformity;
    /**
     * This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double uniformity;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ShieldUniformity.INSTANCE, FighterAbilityAntiCapitalMissileResistance.INSTANCE, UnanchoringDelay.INSTANCE, OnliningDelay.INSTANCE, ShieldCapacity.INSTANCE, Uniformity.INSTANCE, SignatureRadius.INSTANCE, DisallowOffensiveModifiers.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, ArmorUniformity.INSTANCE, AnchoringDelay.INSTANCE, StructureUniformity.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, Cpu.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, CargoGroup.INSTANCE, CargoGroup2 .INSTANCE, Power.INSTANCE, ShieldRechargeRate.INSTANCE })));
    public static final Silo.MetaGroup METAGROUP = new Silo.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  629 :
            {
                return cargogroup;
            }
            case  1846 :
            {
                return cargogroup2;
            }
            case  50 :
            {
                return cpu;
            }
            case  2244 :
            {
                return fighterabilityanticapitalmissileresistance;
            }
            case  30 :
            {
                return power;
            }
            case  484 :
            {
                return shielduniformity;
            }
            case  136 :
            {
                return uniformity;
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
    public IMetaGroup<Silo> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<Silo>
    {
        public static final String RESOURCE_PATH = "SDE/types/starbase/Silo.yaml";
        private Map<Integer, Silo> cache = (null);

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
        public synchronized Map<Integer, Silo> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(Silo.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
                    LoaderOptions options = new LoaderOptions();
                    options.setCodePointLimit(Integer.MAX_VALUE);
                    cache = new Yaml(options).loadAs(reader, (Container.class)).types;
                } catch (final Exception exception) {
                    throw new UnsupportedOperationException("catch this", exception);
                }
            }
            return Collections.unmodifiableMap(cache);
        }

        private static class Container {
            public LinkedHashMap<Integer, Silo> types;
        }
    }
}
