package fr.guiguilechat.jcelechat.libs.spring.evespringtypeattributes.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.guiguilechat.jcelechat.jcesi.disconnected.ESIStatic;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_dogma_attributes_attribute_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.R_get_universe_types_type_id;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses.get_dogma_dynamic_items_type_id_item_id_dogma_attributes;

@RestController
public class TypeAttributesController {

	@RequestMapping(value = "/attribute/{attId}", produces = MediaType.APPLICATION_XML_VALUE)
	public String attribute(@PathVariable int attId, int... typeIds) {
		R_get_dogma_attributes_attribute_id attr = ESIStatic.INSTANCE.cache().dogma.attributes(attId).get();
		if (attr == null) {
			return "invalid attribute";
		}
		if (typeIds != null) {
			for (int typeId : typeIds) {
				ESIStatic.INSTANCE.cache().universe.types(typeId);
			}
		}
		StringBuilder sb = new StringBuilder("<attribute id=\"" + attId + "\" name=\"" + attr.name + "\">\n");
		if (typeIds != null) {
			for (int typeId : typeIds) {
				float value = attr.default_value;
				R_get_universe_types_type_id type = ESIStatic.INSTANCE.cache().universe.types(typeId).get();
				if (type != null && type.dogma_attributes != null) {
					for (get_dogma_dynamic_items_type_id_item_id_dogma_attributes att : type.dogma_attributes) {
						if (att.attribute_id == attId) {
							value = att.value;
							break;
						}
					}
				}
				sb.append("<type id=\"" + typeId + "\">").append("<value>").append(value).append("</value></type>\n");
			}
		}
		sb.append("</attribute>");
		return sb.toString();
	}

}
