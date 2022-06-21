package com.nagarro.training.java.exittest.service;

import com.nagarro.training.java.exittest.dto.AddQuestionRequest;
import com.nagarro.training.java.exittest.dto.PageResponse;
import com.nagarro.training.java.exittest.dto.QuestionDetailsResponse;
import com.nagarro.training.java.exittest.dto.QuestionPreviewResponse;
import com.nagarro.training.java.exittest.entity.Product;
import com.nagarro.training.java.exittest.entity.Question;
import com.nagarro.training.java.exittest.entity.Reply;
import com.nagarro.training.java.exittest.entity.UserEntity;
import com.nagarro.training.java.exittest.enums.QuestionStatus;
import com.nagarro.training.java.exittest.exception.CustomException;
import com.nagarro.training.java.exittest.mapper.QuestionMapper;
import com.nagarro.training.java.exittest.repository.ProductRepository;
import com.nagarro.training.java.exittest.repository.QuestionRepository;
import com.nagarro.training.java.exittest.repository.ReplyRepository;
import com.nagarro.training.java.exittest.repository.UserRepository;
import com.nagarro.training.java.exittest.security.UserPrincipal;
import com.nagarro.training.java.exittest.util.QuestionFilterSpecification;
import com.nagarro.training.java.exittest.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private ReplyRepository replyRepository;

    public QuestionDetailsResponse addQuestion(AddQuestionRequest addQuestionRequest) {
        Product product = this.productRepository.findById(addQuestionRequest.getProduct())
                .orElseThrow(() -> new CustomException("Invalid product id " + addQuestionRequest.getProduct() +
                        " passed in the request", "invalid.product.id"));

        UserPrincipal userPrincipal = SecurityUtils.getUserPrincipal();
        UserEntity userEntity = this.userRepository.findByUsername(userPrincipal.getUsername()).get();

        Question question = new Question();
        question.setBody(addQuestionRequest.getBody());
        question.setSubject(addQuestionRequest.getSubject());
        question.setStatus(QuestionStatus.OPEN);
        question.setProduct(product);
        question.setUser(userEntity);

        question = this.questionRepository.save(question);
        QuestionDetailsResponse questionResponse = this.questionMapper.convertToDto(question);
        return questionResponse;
    }

    public QuestionDetailsResponse getQuestion(Long questionId) {
        Question question = this.questionRepository.findById(questionId)
                .orElseThrow(() -> new CustomException("Invalid Question id " + questionId + " passed in the request",
                        "invalid.question.id"));
        QuestionDetailsResponse questionResponse = this.questionMapper.convertToDto(question);
        return questionResponse;
    }

    public PageResponse<List<QuestionPreviewResponse>> getUserQuestions(Integer pageNumber, Integer pageSize) {
        UserPrincipal userPrincipal = SecurityUtils.getUserPrincipal();
        UserEntity userEntity = this.userRepository.findByUsername(userPrincipal.getUsername()).get();

        Order order = Order.desc("createdOn");
        Page<Question> questionsPage = this.questionRepository.findByUser_userIdOrderByCreatedOnDesc(userEntity.getUserId(),
                PageRequest.of(pageNumber - 1, pageSize, Sort.by(order)));

        List<QuestionPreviewResponse> questionResponse = this.questionMapper.convertToPreviewDtoList(questionsPage.getContent());
        return new PageResponse<>(questionResponse, questionsPage.getNumber() + 1, questionsPage.getSize(),
                questionsPage.getTotalPages(), questionsPage.getTotalElements());

    }

    public PageResponse<List<QuestionPreviewResponse>> searchQuestions(String searchText, Integer pageNumber, Integer pageSize) {

        Specification<Question> questionSpecification = QuestionFilterSpecification.getSpecificationForSearchText(searchText);

        Order order = Order.desc("createdOn");
        Page<Question> questionsPage = this.questionRepository.findAll(questionSpecification,
                PageRequest.of(pageNumber - 1, pageSize, Sort.by(order)));

        List<Question> questions = questionsPage.getContent();

        List<QuestionPreviewResponse> questionResponse = this.questionMapper.convertToPreviewDtoList(questions);

        return new PageResponse<>(questionResponse, questionsPage.getNumber() + 1, questionsPage.getSize(),
                questionsPage.getTotalPages(), questionsPage.getTotalElements());
    }

    public QuestionDetailsResponse closeQuestion(Long questionId, Long acceptedReplyId) {
        Question question = this.questionRepository.findById(questionId)
                .orElseThrow(() -> new CustomException("Invalid Question id " + questionId + " passed in the request",
                        "invalid.question.id"));

        Reply reply = this.replyRepository.findById(acceptedReplyId)
                .orElseThrow(() -> new CustomException("Invalid Reply id " + acceptedReplyId + " passed in the request",
                        "invalid.reply.id"));

        if (reply.getQuestion().getId() != question.getId()) {
            throw new CustomException("Reply id " + acceptedReplyId + " passed in the request does not belong to the question with id " + questionId,
                    "invalid.reply.id");
        }

        if (QuestionStatus.CLOSED == question.getStatus()) {
            throw new CustomException("Question already closed",
                    "invalid.question.status");
        }

        reply.setAcceptedAnswer(Boolean.TRUE);
        question.setStatus(QuestionStatus.CLOSED);

        this.replyRepository.save(reply);
        question = this.questionRepository.save(question);

        QuestionDetailsResponse questionResponse = this.questionMapper.convertToDto(question);
        return questionResponse;
    }

}
