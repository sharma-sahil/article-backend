package sharma.sahil.learning.java.article.backend.dto;

public class StatsResponse {

    private Long totalQuestions;

    private Long closedQuestions;

    private Long totalRegisteredUsers;

    public StatsResponse() {
    }

    public StatsResponse(Long totalQuestions, Long closedQuestions, Long totalRegisteredUsers) {
        this.totalQuestions = totalQuestions;
        this.closedQuestions = closedQuestions;
        this.totalRegisteredUsers = totalRegisteredUsers;
    }

    public Long getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(Long totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public Long getClosedQuestions() {
        return closedQuestions;
    }

    public void setClosedQuestions(Long closedQuestions) {
        this.closedQuestions = closedQuestions;
    }

    public Long getTotalRegisteredUsers() {
        return totalRegisteredUsers;
    }

    public void setTotalRegisteredUsers(Long totalRegisteredUsers) {
        this.totalRegisteredUsers = totalRegisteredUsers;
    }
}
