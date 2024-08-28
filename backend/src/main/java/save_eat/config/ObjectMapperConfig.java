package save_eat.config;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class ObjectMapperConfig {

	@Bean
	Jackson2ObjectMapperBuilderCustomizer disableObjectMapperAutoDetect() {
		return (Jackson2ObjectMapperBuilder builder) -> {
			builder
				.autoDetectFields(false)
				.autoDetectGettersSetters(false);
		};
	}

}
