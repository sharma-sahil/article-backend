package sharma.sahil.learning.java.article.backend.service;

import sharma.sahil.learning.java.article.backend.dto.StatsResponse;
import sharma.sahil.learning.java.article.backend.entity.Question;
import sharma.sahil.learning.java.article.backend.enums.QuestionStatus;
import sharma.sahil.learning.java.article.backend.repository.QuestionRepository;
import sharma.sahil.learning.java.article.backend.repository.ReplyRepository;
import sharma.sahil.learning.java.article.backend.repository.UserRepository;
import sharma.sahil.learning.java.article.backend.util.QuestionFilterSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private UserRepository userRepository;

    public StatsResponse getApplicationStats() {

        Long totalQuestionsRaised = this.questionRepository.count();

        Specification<Question> closedQuestionSpecification = QuestionFilterSpecification.getSpecificationForQuestionStatus(QuestionStatus.CLOSED);
        Long closedQuestions = this.questionRepository.count(closedQuestionSpecification);

        Long totalUsersRegistered = this.userRepository.count();

        return new StatsResponse(totalQuestionsRaised, closedQuestions, totalUsersRegistered);
    }
}
