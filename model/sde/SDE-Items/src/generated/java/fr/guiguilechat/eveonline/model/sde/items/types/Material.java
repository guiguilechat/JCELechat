package fr.guiguilechat.eveonline.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.eveonline.model.sde.items.Item;
import fr.guiguilechat.eveonline.model.sde.items.types.material.AncientSalvage;
import fr.guiguilechat.eveonline.model.sde.items.types.material.BiochemicalMaterial;
import fr.guiguilechat.eveonline.model.sde.items.types.material.Composite;
import fr.guiguilechat.eveonline.model.sde.items.types.material.FuelBlock;
import fr.guiguilechat.eveonline.model.sde.items.types.material.GasIsotopes;
import fr.guiguilechat.eveonline.model.sde.items.types.material.HybridPolymers;
import fr.guiguilechat.eveonline.model.sde.items.types.material.IceProduct;
import fr.guiguilechat.eveonline.model.sde.items.types.material.IntermediateMaterials;
import fr.guiguilechat.eveonline.model.sde.items.types.material.Mineral;
import fr.guiguilechat.eveonline.model.sde.items.types.material.Money;
import fr.guiguilechat.eveonline.model.sde.items.types.material.MoonMaterials;
import fr.guiguilechat.eveonline.model.sde.items.types.material.NamedComponents;
import fr.guiguilechat.eveonline.model.sde.items.types.material.RogueDroneComponents;
import fr.guiguilechat.eveonline.model.sde.items.types.material.SalvagedMaterials;
import fr.guiguilechat.eveonline.model.sde.items.types.material.WormholeMinerals;

public abstract class Material
    extends Item
{

    @Override
    public int getCategoryId() {
        return  4;
    }

    @Override
    public Class<?> getCategory() {
        return Material.class;
    }

    public static Map<String, ? extends Material> loadCategory() {
        return Stream.of(HybridPolymers.load(), FuelBlock.load(), MoonMaterials.load(), IntermediateMaterials.load(), AncientSalvage.load(), Composite.load(), NamedComponents.load(), IceProduct.load(), Mineral.load(), BiochemicalMaterial.load(), Money.load(), SalvagedMaterials.load(), WormholeMinerals.load(), GasIsotopes.load(), RogueDroneComponents.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
