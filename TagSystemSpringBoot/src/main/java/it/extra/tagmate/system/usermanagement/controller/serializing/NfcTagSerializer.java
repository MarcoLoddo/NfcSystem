package it.extra.tagmate.system.usermanagement.controller.serializing;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import it.extra.tagmate.system.usermanagement.data.NfcTagEntity;

public class NfcTagSerializer extends JsonSerializer<NfcTagEntity> {

	@Override
	public void serialize(NfcTagEntity nfcTagEntity, JsonGenerator generator, SerializerProvider arg2)
			throws IOException, JsonProcessingException {
		generator.writeStartObject();
		generator.writeStringField("nfc_id", nfcTagEntity.getNfcId());
		generator.writeBooleanField("disabled", nfcTagEntity.isDisabled());
		generator.writeEndObject();
	}

}
