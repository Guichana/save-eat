package save_eat;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

class TestDto {

	public String test1;

	@JsonProperty
	public String test2;

}

@SpringBootTest
public class ObjectMapperTest {

	@Autowired
	ObjectMapper objectMapper;

	@Test
	void deserializeJsonPropertyOnly() throws JsonMappingException, JsonProcessingException {
		String source = "{\"test1\":\"test\", \"test2\":\"test\"}";

		var parsed = objectMapper.readValue(source, TestDto.class);

		assertNull(parsed.test1);
		assertNotNull(parsed.test2);

	}

}
