package fr.guiguilechat.jcelechat.model.sde.types.deployable;

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
import fr.guiguilechat.jcelechat.model.sde.types.Deployable;

public class MobileSiphonUnit
    extends Deployable
{
    /**
     * How long it takes to anchor or unanchor this object.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(60000)
    public int anchoringdelay;
    /**
     * The maximum security level at which the structure can be anchored. Used as a non-functional display attribute on some deployables.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double anchoringsecuritylevelmax;
    /**
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double armoruniformity;
    /**
     * Minimum distance where a starbase structure can be anchored at from the control tower shield extremity in meters.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int controltowerminimumdistance;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill1;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int requiredskill1level;
    /**
     * Gravimetric strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scangravimetricstrength;
    /**
     * Magnetometric strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanmagnetometricstrength;
    /**
     * Radar strength.
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultRealValue(0.0)
    public double scanradarstrength;
    /**
     * DO NOT MESS WITH This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double shielduniformity;
    /**
     * Amount of Polymer Materials stolen from active Polymer Reactor Array every cycle. 
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int siphonpolymaterial;
    /**
     * Amount of Processed Materials stolen from active Simple Reactor Array every cycle.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int siphonpromaterial;
    /**
     * The amount of Raw Material stolen from active Moon Harvester Arrays each cycle.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int siphonrawmaterial;
    /**
     * Amount of stolen materials that is destroyed.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int siphonwasteamount;
    /**
     * DO NOT MESS WITH
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(1.0)
    public double structureuniformity;
    /**
     * This number is deducted from the %chance of the seeping to armor, to slow seep of damage through shield.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultRealValue(0.0)
    public double uniformity;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {ShieldUniformity.INSTANCE, TechLevel.INSTANCE, ShieldCapacity.INSTANCE, Uniformity.INSTANCE, AnchoringSecurityLevelMax.INSTANCE, SiphonRawMaterial.INSTANCE, SignatureRadius.INSTANCE, SiphonProMaterial.INSTANCE, Hp.INSTANCE, ArmorHP.INSTANCE, SiphonWasteAmount.INSTANCE, ArmorUniformity.INSTANCE, AnchoringDelay.INSTANCE, StructureUniformity.INSTANCE, ControlTowerMinimumDistance.INSTANCE, SiphonPolyMaterial.INSTANCE, ScanRadarStrength.INSTANCE, ScanLadarStrength.INSTANCE, ScanMagnetometricStrength.INSTANCE, ScanGravimetricStrength.INSTANCE, RequiredSkill1Level.INSTANCE, RequiredSkill1 .INSTANCE, ShieldRechargeRate.INSTANCE })));
    public static final MobileSiphonUnit.MetaGroup METAGROUP = new MobileSiphonUnit.MetaGroup();

    @Override
    public Number valueSet(Attribute attribute) {
        switch (attribute.getId()) {
            case  556 :
            {
                return anchoringdelay;
            }
            case  1032 :
            {
                return anchoringsecuritylevelmax;
            }
            case  524 :
            {
                return armoruniformity;
            }
            case  1165 :
            {
                return controltowerminimumdistance;
            }
            case  182 :
            {
                return requiredskill1;
            }
            case  277 :
            {
                return requiredskill1level;
            }
            case  211 :
            {
                return scangravimetricstrength;
            }
            case  210 :
            {
                return scanmagnetometricstrength;
            }
            case  208 :
            {
                return scanradarstrength;
            }
            case  484 :
            {
                return shielduniformity;
            }
            case  1933 :
            {
                return siphonpolymaterial;
            }
            case  1929 :
            {
                return siphonpromaterial;
            }
            case  1928 :
            {
                return siphonrawmaterial;
            }
            case  1930 :
            {
                return siphonwasteamount;
            }
            case  525 :
            {
                return structureuniformity;
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
    public IMetaGroup<MobileSiphonUnit> getGroup() {
        return METAGROUP;
    }

    public static class MetaGroup
        implements IMetaGroup<MobileSiphonUnit>
    {
        public static final String RESOURCE_PATH = "SDE/types/deployable/MobileSiphonUnit.yaml";
        private Map<Integer, MobileSiphonUnit> cache = (null);

        @Override
        public IMetaCategory<? super MobileSiphonUnit> category() {
            return Deployable.METACAT;
        }

        @Override
        public int getGroupId() {
            return  1247;
        }

        @Override
        public String getName() {
            return "MobileSiphonUnit";
        }

        @Override
        public synchronized Map<Integer, MobileSiphonUnit> load() {
            if (cache == null) {
                try(final InputStreamReader reader = new InputStreamReader(MobileSiphonUnit.MetaGroup.class.getClassLoader().getResourceAsStream((RESOURCE_PATH)))) {
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
            public LinkedHashMap<Integer, MobileSiphonUnit> types;
        }
    }
}
