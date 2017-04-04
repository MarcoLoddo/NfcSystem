package data;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.ui.Notification;

public class RestManager {

	public static UserDto sendUserDto(String uri,UserDto userDto)
	{
		ObjectMapper mapper = new ObjectMapper();
		try {
			CloseableHttpClient client = HttpClientBuilder.create().build();
		    HttpPost httpPost = new HttpPost(uri);
			String jsonString=mapper.writeValueAsString(userDto);
			StringEntity entity = new StringEntity(jsonString);
		    httpPost.setEntity(entity);
		    httpPost.setHeader("Accept", "application/json");
		    httpPost.setHeader("Content-type", "application/json");
		    HttpResponse response = client.execute(httpPost);
		    String json = EntityUtils.toString(response.getEntity());
		    return mapper.readValue(json, UserDto.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	public static List<UserDto> sendSearchName(String uri, String name)
	{
		ObjectMapper mapper = new ObjectMapper();
		CloseableHttpClient client = HttpClientBuilder.create().build();
	    HttpPost httpPost = new HttpPost(uri);
	    try {
	    	String jsonString="{ \"name\" : \""+name+"\" }";
	    	System.out.println(jsonString);
			StringEntity entity = new StringEntity(jsonString);
			httpPost.setHeader("Accept", "application/json");
		    httpPost.setHeader("Content-type", "application/json");
		    httpPost.setEntity(entity);
		    HttpResponse response = client.execute(httpPost);
		    String json = EntityUtils.toString(response.getEntity());
		    return (List<UserDto>) mapper.readValue(json, new TypeReference<List<UserDto>>(){});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return null;
	}
}
