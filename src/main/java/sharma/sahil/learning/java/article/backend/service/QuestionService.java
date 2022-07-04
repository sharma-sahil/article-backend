package sharma.sahil.learning.java.article.backend.service;

import sharma.sahil.learning.java.article.backend.dto.AddQuestionRequest;
import sharma.sahil.learning.java.article.backend.dto.PageResponse;
import sharma.sahil.learning.java.article.backend.dto.QuestionDetailsResponse;
import sharma.sahil.learning.java.article.backend.dto.QuestionPreviewResponse;
import sharma.sahil.learning.java.article.backend.entity.Product;
import sharma.sahil.learning.java.article.backend.entity.Question;
import sharma.sahil.learning.java.article.backend.entity.Reply;
import sharma.sahil.learning.java.article.backend.entity.UserEntity;
import sharma.sahil.learning.java.article.backend.enums.QuestionStatus;
import sharma.sahil.learning.java.article.backend.exception.CustomException;
import sharma.sahil.learning.java.article.backend.mapper.QuestionMapper;
import sharma.sahil.learning.java.article.backend.repository.ProductRepository;
import sharma.sahil.learning.java.article.backend.repository.QuestionRepository;
import sharma.sahil.learning.java.article.backend.repository.ReplyRepository;
import sharma.sahil.learning.java.article.backend.repository.UserRepository;
import sharma.sahil.learning.java.article.backend.security.UserPrincipal;
import sharma.sahil.learning.java.article.backend.util.QuestionFilterSpecification;
import sharma.sahil.learning.java.article.backend.util.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(QuestionService.class);

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
        log.info("Request received to add a new question. product : {}", addQuestionRequest.getProduct());
        Product product = this.productRepository.findById(addQuestionRequest.getProduct())
                .orElseThrow(() -> new CustomException("Invalid product id " + addQuestionRequest.getProduct() +
                        " passed in the request", "invalid.product.id"));

        UserPrincipal userPrincipal = SecurityUtils.getUserPrincipal();
        UserEntity userEntity = this.userRepository.findByUsername(userPrincipal.getUsername()).get();

        log.info("Adding a new question. Product : {}, User : {}", addQuestionRequest.getProduct(), userEntity.getUsername());

        Question question = new Question();
        question.setBody(addQuestionRequest.getBody());
        question.setSubject(addQuestionRequest.getSubject());
        question.setStatus(QuestionStatus.OPEN);
        question.setProduct(product);
        question.setUser(userEntity);

        question = this.questionRepository.save(question);
        log.info("Question added successfully with ID : {} .Product : {}, User : {}", question.getId(), addQuestionRequest.getProduct(), userEntity.getUsername());
        QuestionDetailsResponse questionResponse = this.questionMapper.convertToDto(question);
        return questionResponse;
    }

    public QuestionDetailsResponse getQuestion(Long questionId) {
        log.info("Request received to get details of Question with ID : {} ", questionId);
        Question question = this.questionRepository.findById(questionId)
                .orElseThrow(() -> new CustomException("Invalid Question id " + questionId + " passed in the request",
                        "invalid.question.id"));
        QuestionDetailsResponse questionResponse = this.questionMapper.convertToDto(question);
        return questionResponse;
    }

    public PageResponse<List<QuestionPreviewResponse>> getUserQuestions(Integer pageNumber, Integer pageSize) {
        UserPrincipal userPrincipal = SecurityUtils.getUserPrincipal();
        UserEntity userEntity = this.userRepository.findByUsername(userPrincipal.getUsername()).get();
        log.info("Request received to get Questions raised by user : {} ", userEntity.getUsername());

        Order order = Order.desc("createdOn");
        Page<Question> questionsPage = this.questionRepository.findByUser_userIdOrderByCreatedOnDesc(userEntity.getUserId(),
                PageRequest.of(pageNumber - 1, pageSize, Sort.by(order)));

        List<QuestionPreviewResponse> questionResponse = this.questionMapper.convertToPreviewDtoList(questionsPage.getContent());
        return new PageResponse<>(questionResponse, questionsPage.getNumber() + 1, questionsPage.getSize(),
                questionsPage.getTotalPages(), questionsPage.getTotalElements());

    }

    public PageResponse<List<QuestionPreviewResponse>> searchQuestions(String searchText, Integer pageNumber, Integer pageSize) {

        log.info("Request received to get questions. Search Query : {} ", searchText);

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
        log.info("Request received to close question with id : {}, acceptedReply : {}", questionId, acceptedReplyId);
        Question question = this.questionRepository.findById(questionId)
                .orElseThrow(() -> new CustomException("Invalid Question id " + questionId + " passed in the request",
                        "invalid.question.id"));

        Reply reply = this.replyRepository.findById(acceptedReplyId)
                .orElseThrow(() -> new CustomException("Invalid Reply id " + acceptedReplyId + " passed in the request",
                        "invalid.reply.id"));

        if (reply.getQuestion().getId() != question.getId()) {
            log.error("Reply Id : {} does not belong to the question : {}", acceptedReplyId, questionId);
            throw new CustomException("Reply id " + acceptedReplyId + " passed in the request does not belong to the question with id " + questionId,
                    "invalid.reply.id");
        }

        if (QuestionStatus.CLOSED == question.getStatus()) {
            log.error("Question : {} is already closed", acceptedReplyId, questionId);
            throw new CustomException("Question already closed", "invalid.question.status");
        }

        reply.setAcceptedAnswer(Boolean.TRUE);
        question.setStatus(QuestionStatus.CLOSED);

        this.replyRepository.save(reply);
        question = this.questionRepository.save(question);

        QuestionDetailsResponse questionResponse = this.questionMapper.convertToDto(question);
        return questionResponse;
    }

}
