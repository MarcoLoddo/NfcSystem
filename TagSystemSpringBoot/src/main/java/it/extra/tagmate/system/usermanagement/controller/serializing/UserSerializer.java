package it.extra.tagmate.system.usermanagement.controller.serializing;

import java.io.IOException;

import org.hibernate.sql.ordering.antlr.GeneratedOrderByFragmentParser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import it.extra.tagmate.system.usermanagement.data.NfcTagEntity;
import it.extra.tagmate.system.usermanagement.data.UserEntity;

public class UserSerializer extends JsonSerializer<UserEntity> {

	
	@Override
	public void serialize(UserEntity userEntity, JsonGenerator generator, SerializerProvider arg2) throws IOException {
		
		generator.writeStartObject();
		generator.writeNumberField("user_id", userEntity.getUserId());
		generator.writeArrayFieldStart("nfc");
		for (NfcTagEntity nfc : userEntity.getNfc()) {
			generator.writeObject(nfc);
		}
		generator.writeEndArray();
		generator.writeEndObject();
	}

}
