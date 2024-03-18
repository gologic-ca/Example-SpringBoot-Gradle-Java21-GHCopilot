package io.spring.api;


import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.spring.JacksonCustomizations;
import io.spring.api.security.WebSecurityConfig;
import io.spring.application.TagsQueryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

@WebMvcTest(TagsApi.class)
@Import({WebSecurityConfig.class, JacksonCustomizations.class})
class TagsApiTest extends TestWithCurrentUser {
  @Autowired
  private MockMvc mvc;

  @MockBean
  private TagsQueryService tagsQueryService;

  @BeforeEach
  public void setUp() throws Exception {
    super.setUp();
    RestAssuredMockMvc.mockMvc(mvc);
  }

  @Test
  void should_get_tags_success() {
    when(tagsQueryService.allTags()).thenReturn(Arrays.asList("reactjs", "angularjs"));
    RestAssuredMockMvc.when()
        .get("/tags")
        .prettyPeek()
        .then()
        .statusCode(200)
        .body("tags.size()", equalTo(2))
        .body("tags[0]", equalTo("reactjs"))
        .body("tags[1]", equalTo("angularjs"));
  }
}
