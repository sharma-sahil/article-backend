package sharma.sahil.learning.java.article.backend.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AddReplyRequest {

    @NotBlank(message = "Reply body is mandatory")
    private String body;

    @NotNull(message = "Question Id in mandatory")
    private Long question;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getQuestion() {
        return question;
    }

    public void setQuestion(Long question) {
        this.question = question;
    }
}
