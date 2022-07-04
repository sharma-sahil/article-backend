package sharma.sahil.learning.java.article.backend.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AddQuestionRequest {

    @NotBlank(message = "Question subject is mandatory")
    private String subject;

    @NotBlank(message = "Question body is mandatory")
    private String body;

    private String tags;

    @NotNull(message = "Product is mandatory for adding a question")
    private Long product;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Long getProduct() {
        return product;
    }

    public void setProduct(Long product) {
        this.product = product;
    }
}
