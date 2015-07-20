package n3.web.service;

import java.io.IOException;

import n3.web.controller.AjaxController;
import n3.web.model.fbAcc;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

public class jsonService {

	/** Logger to log information */
	private final static Logger LOG = Logger.getLogger(jsonService.class);
	
	public fbAcc parseJson(String json) {
		// map string json to AccountInfo list
        ObjectMapper mapper = new ObjectMapper();
        try {
			fbAcc fbAcc = mapper.readValue(json, new TypeReference<fbAcc>() {
			});
			LOG.info(fbAcc.toString());
			return fbAcc;
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
}
