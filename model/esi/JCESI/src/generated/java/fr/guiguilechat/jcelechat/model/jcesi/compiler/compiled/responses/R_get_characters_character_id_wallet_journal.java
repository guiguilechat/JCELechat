package fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.responses;

import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_wallet_journal_context_id_type;
import fr.guiguilechat.jcelechat.model.jcesi.compiler.compiled.structures.get_characters_character_id_wallet_journal_ref_type;

public class R_get_characters_character_id_wallet_journal {
    /**
     * The amount of ISK given or taken from the wallet as a result of the given transaction. Positive when ISK is deposited into the wallet and negative when ISK is withdrawn
     */
    public double amount;
    /**
     * Wallet balance after transaction occurred
     */
    public double balance;
    /**
     * An ID that gives extra context to the particular transaction. Because of legacy reasons the context is completely different per ref_type and means different things. It is also possible to not have a context_id
     */
    public long context_id;
    /**
     * The type of the given context_id if present
     */
    public get_characters_character_id_wallet_journal_context_id_type context_id_type;
    /**
     * Date and time of transaction
     */
    public String date;
    /**
     * The reason for the transaction, mirrors what is seen in the client
     */
    public String description;
    /**
     * The id of the first party involved in the transaction. This attribute has no consistency and is different or non existant for particular ref_types. The description attribute will help make sense of what this attribute means. For more info about the given ID it can be dropped into the /universe/names/ ESI route to determine its type and name
     */
    public int first_party_id;
    /**
     * Unique journal reference ID
     */
    public long id;
    /**
     * The user stated reason for the transaction. Only applies to some ref_types
     */
    public String reason;
    /**
     * "The transaction type for the given. transaction. Different transaction types will populate different attributes."
     */
    public get_characters_character_id_wallet_journal_ref_type ref_type;
    /**
     * The id of the second party involved in the transaction. This attribute has no consistency and is different or non existant for particular ref_types. The description attribute will help make sense of what this attribute means. For more info about the given ID it can be dropped into the /universe/names/ ESI route to determine its type and name
     */
    public int second_party_id;
    /**
     * Tax amount received. Only applies to tax related transactions
     */
    public double tax;
    /**
     * The corporation ID receiving any tax paid. Only applies to tax related transactions
     */
    public int tax_receiver_id;

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other == null)||(other.getClass()!= getClass())) {
            return false;
        }
        R_get_characters_character_id_wallet_journal othersame = ((R_get_characters_character_id_wallet_journal) other);
        if (amount!= othersame.amount) {
            return false;
        }
        if (balance!= othersame.balance) {
            return false;
        }
        if (context_id!= othersame.context_id) {
            return false;
        }
        if ((context_id_type!= othersame.context_id_type)&&((context_id_type == null)||(!context_id_type.equals(othersame.context_id_type)))) {
            return false;
        }
        if ((date!= othersame.date)&&((date == null)||(!date.equals(othersame.date)))) {
            return false;
        }
        if ((description!= othersame.description)&&((description == null)||(!description.equals(othersame.description)))) {
            return false;
        }
        if (first_party_id!= othersame.first_party_id) {
            return false;
        }
        if (id!= othersame.id) {
            return false;
        }
        if ((reason!= othersame.reason)&&((reason == null)||(!reason.equals(othersame.reason)))) {
            return false;
        }
        if ((ref_type!= othersame.ref_type)&&((ref_type == null)||(!ref_type.equals(othersame.ref_type)))) {
            return false;
        }
        if (second_party_id!= othersame.second_party_id) {
            return false;
        }
        if (tax!= othersame.tax) {
            return false;
        }
        if (tax_receiver_id!= othersame.tax_receiver_id) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((((((((Double.hashCode(amount)+ Double.hashCode(balance))+ Long.hashCode(context_id))+((context_id_type == null)? 0 :context_id_type.hashCode()))+((date == null)? 0 :date.hashCode()))+((description == null)? 0 :description.hashCode()))+ first_party_id)+ Long.hashCode(id))+((reason == null)? 0 :reason.hashCode()))+((ref_type == null)? 0 :ref_type.hashCode()))+ second_party_id)+ Double.hashCode(tax))+ tax_receiver_id);
    }
}
