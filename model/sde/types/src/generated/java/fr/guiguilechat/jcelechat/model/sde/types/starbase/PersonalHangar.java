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

public class PersonalHangar
    extends Starbase
{
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
     * The maximum distance at which the object can be used.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxoperationaldistance;
    /**
     * The maximum number of users that can be present within the operational range of the structure for it to be capable of operation.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int maxoperationalusers;
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
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ShieldUniformity.INSTANCE, FighterAbilityAntiCapitalMissileResistance.INSTANCE, UnanchoringDelay.INSTANCE, OnliningDelay.INSTANCE, ShieldCapacity.INSTANCE, Uniformity.INSTANCE, SignatureRadius.INSTANCE, DisallowOffensiveModifiers.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, MaxOperationalDistance.INSTANCE, MaxOperationalUsers.INSTANCE, ArmorUniformity.INSTANCE, AnchoringDelay.INSTANCE, StructureUniformity.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, Cpu.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, Power.INSTANCE, ShieldRechargeRate.INSTANCE })));
    public static final PersonalHangar.MetaGroup METAGROUP = new PersonalHangar.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  50 :
            {
                return cpu;
            }
            case  2244 :
            {
                return fighterabilityanticapitalmissileresistance;
            }
            case  715 :
            {
                return maxoperationaldistance;
            }
            case  716 :
            {
                return maxoperationalusers;
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
    public IMetaGroup<PersonalHangar> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<PersonalHangar>
    {
        public static final String RESOURCE_PATH = "SDE/types/starbase/PersonalHangar.yaml";
        private Map<Integer, PersonalHangar> cache = (null);

        @Override
        public IMetaCategory<? super PersonalHangar> category() {
            return Starbase.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1212;
        }

        @Override
        public String getName() {
            return "PersonalHangar";
        }

        @Override
        public synchronized Map<Integer, PersonalHangar> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(PersonalHangar.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, PersonalHangar> types;
        }
    }
}
