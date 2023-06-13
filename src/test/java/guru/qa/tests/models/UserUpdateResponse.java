package guru.qa.tests.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserUpdateResponse {

    String name;
    String job;
    String updatedAt;
}
