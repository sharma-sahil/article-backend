package sharma.sahil.learning.java.article.backend.api;

import sharma.sahil.learning.java.article.backend.dto.AddReplyRequest;
import sharma.sahil.learning.java.article.backend.dto.ReplyResponse;
import sharma.sahil.learning.java.article.backend.service.ReplyService;
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
