package com.nagarro.training.java.exittest.service;

import com.nagarro.training.java.exittest.dto.StatsResponse;
import com.nagarro.training.java.exittest.entity.Question;
import com.nagarro.training.java.exittest.enums.QuestionStatus;
import com.nagarro.training.java.exittest.repository.QuestionRepository;
import com.nagarro.training.java.exittest.repository.ReplyRepository;
import com.nagarro.training.java.exittest.repository.UserRepository;
import com.nagarro.training.java.exittest.util.QuestionFilterSpecification;
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
