package com.nagarro.training.java.exittest.service;

import com.nagarro.training.java.exittest.dto.AddReplyRequest;
import com.nagarro.training.java.exittest.dto.ReplyResponse;
import com.nagarro.training.java.exittest.entity.Question;
import com.nagarro.training.java.exittest.entity.Reply;
import com.nagarro.training.java.exittest.entity.UserEntity;
import com.nagarro.training.java.exittest.exception.CustomException;
import com.nagarro.training.java.exittest.mapper.ReplyMapper;
import com.nagarro.training.java.exittest.repository.QuestionRepository;
import com.nagarro.training.java.exittest.repository.ReplyRepository;
import com.nagarro.training.java.exittest.repository.UserRepository;
import com.nagarro.training.java.exittest.security.UserPrincipal;
import com.nagarro.training.java.exittest.util.SecurityUtils;
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
