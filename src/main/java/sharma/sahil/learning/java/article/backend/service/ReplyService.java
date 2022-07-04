package sharma.sahil.learning.java.article.backend.service;

import sharma.sahil.learning.java.article.backend.dto.AddReplyRequest;
import sharma.sahil.learning.java.article.backend.dto.ReplyResponse;
import sharma.sahil.learning.java.article.backend.entity.Question;
import sharma.sahil.learning.java.article.backend.entity.Reply;
import sharma.sahil.learning.java.article.backend.entity.UserEntity;
import sharma.sahil.learning.java.article.backend.exception.CustomException;
import sharma.sahil.learning.java.article.backend.mapper.ReplyMapper;
import sharma.sahil.learning.java.article.backend.repository.QuestionRepository;
import sharma.sahil.learning.java.article.backend.repository.ReplyRepository;
import sharma.sahil.learning.java.article.backend.repository.UserRepository;
import sharma.sahil.learning.java.article.backend.security.UserPrincipal;
import sharma.sahil.learning.java.article.backend.util.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {

    private static final Logger log = LoggerFactory.getLogger(ReplyService.class);

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReplyMapper replyMapper;

    public ReplyResponse addReply(AddReplyRequest addReplyRequest) {
        log.info("Request received to add a new reply. Question : {}", addReplyRequest.getQuestion());
        Question question = this.questionRepository.findById(addReplyRequest.getQuestion())
                .orElseThrow(() -> new CustomException("Invalid Question id " + addReplyRequest.getQuestion() + " passed in the request", "invalid.question.id"));

        UserPrincipal userPrincipal = SecurityUtils.getUserPrincipal();
        UserEntity userEntity = this.userRepository.findByUsername(userPrincipal.getUsername()).get();

        Reply reply = new Reply();
        reply.setBody(addReplyRequest.getBody());
        reply.setUser(userEntity);
        reply.setQuestion(question);

        reply = this.replyRepository.save(reply);
        log.info("New Reply added for Question : {} with new reply id : {}", addReplyRequest.getQuestion(), reply.getId());
        return this.replyMapper.convertToDto(reply);
    }

}
