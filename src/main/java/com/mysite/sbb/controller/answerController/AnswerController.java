package com.mysite.sbb.controller.answerController;

import com.mysite.sbb.entity.answer.Answer;
import com.mysite.sbb.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

//    @GetMapping("/vote/{id}")
//    public String answerVote(Principal principal, @PathVariable("id") Long id) {
//        Answer answer = this.answerService.getAnswer(id);
//        String siteUser = answer.getUsername();
//        this.answerService.vote(answer, siteUser);
//        return String.format("redirect:/question/detail/%s#answer_%s", answer.getQuestion().getId(), answer.getId());
//    }

}
