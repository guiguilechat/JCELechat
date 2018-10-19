package fr.guiguilechat.jcelechat.model.jcesi.compiled.structures;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum get_universe_stations_station_id_services {
    @JsonProperty("bounty-missions")
    bounty_missions("bounty-missions"),
    @JsonProperty("assasination-missions")
    assasination_missions("assasination-missions"),
    @JsonProperty("courier-missions")
    courier_missions("courier-missions"),
    @JsonProperty("interbus")
    interbus("interbus"),
    @JsonProperty("reprocessing-plant")
    reprocessing_plant("reprocessing-plant"),
    @JsonProperty("refinery")
    refinery("refinery"),
    @JsonProperty("market")
    market("market"),
    @JsonProperty("black-market")
    black_market("black-market"),
    @JsonProperty("stock-exchange")
    stock_exchange("stock-exchange"),
    @JsonProperty("cloning")
    cloning("cloning"),
    @JsonProperty("surgery")
    surgery("surgery"),
    @JsonProperty("dna-therapy")
    dna_therapy("dna-therapy"),
    @JsonProperty("repair-facilities")
    repair_facilities("repair-facilities"),
    @JsonProperty("factory")
    factory("factory"),
    @JsonProperty("labratory")
    labratory("labratory"),
    @JsonProperty("gambling")
    gambling("gambling"),
    @JsonProperty("fitting")
    fitting("fitting"),
    @JsonProperty("paintshop")
    paintshop("paintshop"),
    @JsonProperty("news")
    news("news"),
    @JsonProperty("storage")
    storage("storage"),
    @JsonProperty("insurance")
    insurance("insurance"),
    @JsonProperty("docking")
    docking("docking"),
    @JsonProperty("office-rental")
    office_rental("office-rental"),
    @JsonProperty("jump-clone-facility")
    jump_clone_facility("jump-clone-facility"),
    @JsonProperty("loyalty-point-store")
    loyalty_point_store("loyalty-point-store"),
    @JsonProperty("navy-offices")
    navy_offices("navy-offices"),
    @JsonProperty("security-offices")
    security_offices("security-offices");
    public final String toString;

    get_universe_stations_station_id_services(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return toString;
    }
}
