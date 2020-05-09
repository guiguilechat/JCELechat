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
import fr.guiguilechat.jcelechat.model.sde.types.skill.Armor;
import fr.guiguilechat.jcelechat.model.sde.types.skill.CorporationManagement;
import fr.guiguilechat.jcelechat.model.sde.types.skill.Drones;
import fr.guiguilechat.jcelechat.model.sde.types.skill.ElectronicSystems;
import fr.guiguilechat.jcelechat.model.sde.types.skill.Engineering;
import fr.guiguilechat.jcelechat.model.sde.types.skill.FleetSupport;
import fr.guiguilechat.jcelechat.model.sde.types.skill.Gunnery;
import fr.guiguilechat.jcelechat.model.sde.types.skill.Missiles;
import fr.guiguilechat.jcelechat.model.sde.types.skill.Navigation;
import fr.guiguilechat.jcelechat.model.sde.types.skill.NeuralEnhancement;
import fr.guiguilechat.jcelechat.model.sde.types.skill.PlanetManagement;
import fr.guiguilechat.jcelechat.model.sde.types.skill.Production;
import fr.guiguilechat.jcelechat.model.sde.types.skill.ResourceProcessing;
import fr.guiguilechat.jcelechat.model.sde.types.skill.Rigging;
import fr.guiguilechat.jcelechat.model.sde.types.skill.Scanning;
import fr.guiguilechat.jcelechat.model.sde.types.skill.Science;
import fr.guiguilechat.jcelechat.model.sde.types.skill.Shields;
import fr.guiguilechat.jcelechat.model.sde.types.skill.Social;
import fr.guiguilechat.jcelechat.model.sde.types.skill.SpaceshipCommand;
import fr.guiguilechat.jcelechat.model.sde.types.skill.StructureManagement;
import fr.guiguilechat.jcelechat.model.sde.types.skill.Subsystems;
import fr.guiguilechat.jcelechat.model.sde.types.skill.Targeting;
import fr.guiguilechat.jcelechat.model.sde.types.skill.Trade;

public abstract class Skill
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
     * Integer that describes the types mass
     */
    @HighIsGood(true)
    @Stackable(false)
    @DefaultDoubleValue(0.0)
    public double Mass;
    /**
     * Only refers to another dogma attribute.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int PrimaryAttribute;
    /**
     * Radius of an object in meters
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double Radius;
    /**
     * The type ID of the skill that is required.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1;
    /**
     * Required skill level for skill 1
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int RequiredSkill1Level;
    /**
     * Only refers to another dogma attribute.
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SecondaryAttribute;
    /**
     * Level of skill
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultIntValue(0)
    public int SkillLevel;
    /**
     * This attribute is a multiplier to the number of skill points required to train. Skill points required to train a skill = 250 * skillTimeConstant * sqrt(32)^(skillLevel - 1)
     */
    @HighIsGood(true)
    @Stackable(true)
    @DefaultDoubleValue(0.0)
    public double SkillTimeConstant;
    public static final Set<Attribute> ATTRIBUTES = Collections.unmodifiableSet(new LinkedHashSet<>(Arrays.asList(new Attribute[] {fr.guiguilechat.jcelechat.model.sde.attributes.Radius.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.Mass.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.Capacity.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.SkillTimeConstant.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.PrimaryAttribute.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1Level.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.SecondaryAttribute.INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.RequiredSkill1 .INSTANCE, fr.guiguilechat.jcelechat.model.sde.attributes.SkillLevel.INSTANCE })));
    public static final Skill.MetaCat METACAT = new Skill.MetaCat();

    @Override
    public Number attribute(Attribute attribute) {
        switch (attribute.getId()) {
            case  38 :
            {
                return Capacity;
            }
            case  4 :
            {
                return Mass;
            }
            case  180 :
            {
                return PrimaryAttribute;
            }
            case  162 :
            {
                return Radius;
            }
            case  182 :
            {
                return RequiredSkill1;
            }
            case  277 :
            {
                return RequiredSkill1Level;
            }
            case  181 :
            {
                return SecondaryAttribute;
            }
            case  280 :
            {
                return SkillLevel;
            }
            case  275 :
            {
                return SkillTimeConstant;
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
    public IMetaCategory<Skill> getCategory() {
        return METACAT;
    }

    public static class MetaCat
        implements IMetaCategory<Skill>
    {

        @Override
        public int getCategoryId() {
            return  16;
        }

        @Override
        public String getName() {
            return "Skill";
        }

        @Override
        public Collection<IMetaGroup<? extends Skill>> groups() {
            return Arrays.asList(Gunnery.METAGROUP, Missiles.METAGROUP, SpaceshipCommand.METAGROUP, FleetSupport.METAGROUP, CorporationManagement.METAGROUP, Production.METAGROUP, Rigging.METAGROUP, Science.METAGROUP, ElectronicSystems.METAGROUP, Drones.METAGROUP, Trade.METAGROUP, Navigation.METAGROUP, Social.METAGROUP, Shields.METAGROUP, Armor.METAGROUP, Targeting.METAGROUP, Engineering.METAGROUP, Scanning.METAGROUP, ResourceProcessing.METAGROUP, NeuralEnhancement.METAGROUP, Subsystems.METAGROUP, PlanetManagement.METAGROUP, StructureManagement.METAGROUP);
        }
    }
}
