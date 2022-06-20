package com.nagarro.training.java.exittest.web.api;

import com.nagarro.training.java.exittest.dto.AddReplyRequest;
import com.nagarro.training.java.exittest.dto.ReplyResponse;
import com.nagarro.training.java.exittest.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @PostMapping
    public ReplyResponse addReply(@Valid @RequestBody AddReplyRequest addReplyRequest) {
        return this.replyService.addReply(addReplyRequest);
    }

}
