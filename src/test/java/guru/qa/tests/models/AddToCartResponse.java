package guru.qa.tests.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class AddToCartResponse {
    private String success;
    private String message;
    private String updatetopcartsectionhtml;
    private String updateflyoutcartsectionhtml;
}
