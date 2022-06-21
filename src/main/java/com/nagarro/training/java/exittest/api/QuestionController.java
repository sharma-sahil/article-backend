package com.nagarro.training.java.exittest.api;

import com.nagarro.training.java.exittest.dto.AddQuestionRequest;
import com.nagarro.training.java.exittest.dto.PageResponse;
import com.nagarro.training.java.exittest.dto.QuestionDetailsResponse;
import com.nagarro.training.java.exittest.dto.QuestionPreviewResponse;
import com.nagarro.training.java.exittest.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @PostMapping
    public QuestionDetailsResponse createQuestion(@Valid @RequestBody AddQuestionRequest addQuestionRequest) {
        return this.questionService.addQuestion(addQuestionRequest);
    }

    @GetMapping("/{questionId}")
    public QuestionDetailsResponse getQuestion(@PathVariable("questionId") Long questionId) {
        return this.questionService.getQuestion(questionId);
    }

    @GetMapping("/search")
    public PageResponse<List<QuestionPreviewResponse>> searchQuestions(@RequestParam("text") String searchText,
                                                                       @RequestParam("pageNumber") Integer pageNumber,
                                                                       @RequestParam("pageSize") Integer pageSize) {
        return this.questionService.searchQuestions(searchText, pageNumber, pageSize);
    }

    @GetMapping("/myQuestions")
    public PageResponse<List<QuestionPreviewResponse>> getUserQuestions(@RequestParam("pageNumber") Integer pageNumber,
                                                                        @RequestParam("pageSize") Integer pageSize) {
        return this.questionService.getUserQuestions(pageNumber, pageSize);
    }

    @PutMapping("/{questionId}/close")
    public QuestionDetailsResponse closeQuestion(@PathVariable("questionId") Long questionId, @RequestParam("acceptedReply") Long acceptedReplyId) {
        return this.questionService.closeQuestion(questionId, acceptedReplyId);
    }

}
