package das.spring.security.app;

import das.spring.security.app.models.ErrorResponse;
import das.spring.security.app.util.CommonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringSecurityAppApplicationTests {

	@Test
	void contextLoads() {
		ErrorResponse errorResponse = new ErrorResponse.ErrorResponseBuilder().uuid(CommonUtil.generateUUID())
				.errorDesc("error description")
				.status(200)
				.message("a helper message")
				.build();
	}

}
