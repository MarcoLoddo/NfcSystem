package it.extra.tagmate.system.usermanagement.controller.serializing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import it.extra.tagmate.system.usermanagement.data.UserEntity;

public class JsonSerializer {
	public static String serialize(UserEntity userEntity)
	{
		ObjectMapper mapper = new ObjectMapper();
		 
		SimpleModule module = new SimpleModule();
		module.addSerializer(UserEntity.class, new UserSerializer());
		mapper.registerModule(module);
		 
		String serialized = null;
		try {
			serialized = mapper.writeValueAsString(userEntity);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return serialized;
	}

}
