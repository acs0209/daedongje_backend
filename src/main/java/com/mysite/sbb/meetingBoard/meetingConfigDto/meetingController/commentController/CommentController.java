package com.mysite.sbb.meetingBoard.meetingConfigDto.meetingController.commentController;

import com.mysite.sbb.meetingBoard.meetingService.AnswerService;
import com.mysite.sbb.meetingBoard.meetingService.CommentService;
import com.mysite.sbb.meetingBoard.meetingService.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;
    private final QuestionService questionService;
    private final AnswerService answerService;

    // 답변
    @GetMapping(value = "/create/answer/{id}")
    public String createAnswerComment(CommentForm commentForm) {

        return "comment_form";
    }

    // 질문
    @GetMapping(value = "/create/question/{id}")
    public String createQuestionComment(CommentForm commentForm) {

        return "comment_form";
    }


}
