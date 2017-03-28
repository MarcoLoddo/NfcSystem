package it.extra.tagmate.system.usermanagement.controller.serializing;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import it.extra.tagmate.system.usermanagement.data.UserEntity;

@SuppressWarnings("serial")
public class UserSerializer extends StdSerializer<UserEntity> {
	public UserSerializer()
	{
		  this(null);
    }
	protected UserSerializer(Class<UserEntity> t) {
		super(t);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void serialize(UserEntity arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException {
		arg1.writeStartObject();
		arg1.writeNumberField("user_id",arg0.getUserId());
		arg1.writeStringField("name",arg0.getFirstName());
		arg1.writeStringField("surname",arg0.getLastName());
		arg1.writeStringField("email",arg0.getEmail());
		arg1.writeEndObject();
	}

}
