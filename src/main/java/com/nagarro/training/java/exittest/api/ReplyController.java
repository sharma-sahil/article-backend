package com.nagarro.training.java.exittest.api;

import com.nagarro.training.java.exittest.dto.AddReplyRequest;
import com.nagarro.training.java.exittest.dto.ReplyResponse;
import com.nagarro.training.java.exittest.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;

    @PostMapping
    public ReplyResponse addReply(@Valid @RequestBody AddReplyRequest addReplyRequest) {
        return this.replyService.addReply(addReplyRequest);
    }

}
