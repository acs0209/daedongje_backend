package com.mysite.sbb.meetingBoard.api.commentApiController;

import com.mysite.sbb.meetingBoard.configDto.MeetingDeleteInfoDto;
import com.mysite.sbb.meetingBoard.configDto.MeetingModifyInfoDto;
import com.mysite.sbb.meetingBoard.configDto.MeetingSuccessDto;
import com.mysite.sbb.meetingBoard.controller.commentController.CommentForm;
import com.mysite.sbb.entity.answer.Answer;
import com.mysite.sbb.entity.comment.Comment;
import com.mysite.sbb.entity.question.Question;
import com.mysite.sbb.meetingBoard.service.AnswerService;
import com.mysite.sbb.meetingBoard.service.CommentService;
import com.mysite.sbb.meetingBoard.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/meeting/comment")
public class CommentApiController {

    private final AnswerService answerService;
    private final CommentService commentService;
    private final QuestionService questionService;

    // 답변 댓글 가져오기
//    @GetMapping("/answer/list/{answerId}")
//    public ResponseEntity<Map<String, Object>> answerCommentList(@PathVariable("answerId") Long id,
//                                  @RequestParam(value = "page", defaultValue = "0") int page) {
//
//        Answer answer = answerService.getAnswer(id);
//        AnswerDto answerDto = new AnswerDto(answer.getId(), answer.getContent(), answer.getCreateDate(), answer.getUsername(), answer.getVoter().size(), answer.getCommentList());
//        Page<Comment> commentPage = commentService.getAnswerCommentList(page, id);
//
//        if (commentPage.getNumberOfElements() == 0 && page != 0) {
//            throw new IllegalArgumentException("잘못된 입력 값입니다");
//        }
//
//        Page<CommentDto> commentDtoPage = commentPage.map(
//                post -> new CommentDto (
//                        post.getId(),post.getContent(),post.getCreateDate(),
//                        post.getModifyDate(),post.getUsername()
//                ));
//
//        Map<String, Object> result = new HashMap<>();
//        result.put("answer", answerDto);
//        result.put("comments", commentDtoPage);
//
//        return ResponseEntity.ok(result);
//    }
//    // 질문 댓글 가져오기
//    @GetMapping("/question/list/{questionId}")
//    public ResponseEntity<Map<String, Object>> questionCommentList(@PathVariable("questionId") Long id,
//                                    @RequestParam(value = "page", defaultValue = "0") int page) {
//
//        Question question = questionService.getQuestion(id);
//        QuestionDto questionDto = new QuestionDto(question.getId(), question.getSubject(), question.getContent(), question.getCreateDate(), question.getModifyDate(),question.getUsername() ,question.getView(), question.getVoter().size());
//        Page<Comment> commentPage = commentService.getQuestionCommentList(page, id);
//
//        if (commentPage.getNumberOfElements() == 0 && page != 0) {
//            throw new IllegalArgumentException("잘못된 입력 값입니다.");
//        }
//
//        Page<CommentDto> commentDtoPage = commentPage.map(
//                post -> new CommentDto (
//                        post.getId(),post.getContent(),post.getCreateDate(),
//                        post.getModifyDate(),post.getUsername()
//                ));
//
//        Map<String, Object> result = new HashMap<>();
//        result.put("question", questionDto);
//        result.put("comments", commentDtoPage);
//
//        return ResponseEntity.ok(result);
//    }

    // 답변 댓글 생성
    @PostMapping(value = "/create/answer/{id}")
    public ResponseEntity<CommentCreateForm> createAnswerComment(@PathVariable("id") Long id, @Valid @RequestBody CommentForm commentForm,
                                              BindingResult bindingResult) {

        Optional<Answer> answer = Optional.ofNullable(this.answerService.getAnswer(id));

        if (answer.isPresent()) {
            if (bindingResult.hasErrors()) {
                throw new IllegalArgumentException("잘못된 입력 값입니다.");
            }
            Comment c = this.commentService.create(answer.get(), commentForm.getContent(), commentForm.getUsername(), commentForm.getPassword());

            CommentCreateForm commentCreateForm = new CommentCreateForm(commentForm.getContent(), commentForm.getUsername());
            return ResponseEntity.ok(commentCreateForm);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "요청하신 데이터를 찾을 수 없습니다.");
        }
    }

    // 질문 댓글 생성
    @PostMapping(value = "/create/question/{id}")
    public ResponseEntity<CommentCreateForm> createQuestionComment(@PathVariable("id") Long id, @Valid @RequestBody CommentForm commentForm,
                                        BindingResult bindingResult) {

        Optional<Question> question = Optional.ofNullable(this.questionService.getQuestion(id));

        if (question.isPresent()) {
            if (bindingResult.hasErrors()) {
                throw new IllegalArgumentException("잘못된 입력 값입니다.");
            }
            Comment c = this.commentService.create(question.get(), commentForm.getContent(), commentForm.getUsername(), commentForm.getPassword());

            CommentCreateForm commentCreateForm = new CommentCreateForm(commentForm.getContent(), commentForm.getUsername());
            return ResponseEntity.ok(commentCreateForm);

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "요청하신 데이터를 찾을 수 없습니다.");
        }

    }

    @GetMapping("/modify/{id}")
    public ResponseEntity<CommentForm> modifyComment(CommentForm commentForm, @PathVariable("id") Long id) {
        Optional<Comment> comment = this.commentService.getComment(id);
        if (comment.isPresent()) {
            Comment c = comment.get();

            commentForm.setContent(c.getContent());
        }

        return ResponseEntity.ok(commentForm);
    }

    @PutMapping("/modify/{id}")
    public ResponseEntity<CommentModifyForm> modifyComment(@Valid @RequestBody MeetingModifyInfoDto meetingModifyInfoDto, BindingResult bindingResult,
                                                           @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("잘못된 입력 값입니다.");
        }
        Optional<Comment> comment = this.commentService.getComment(id);
        if (comment.isPresent()) {
            Comment c = comment.get();

            if (!c.getPassword().equals(meetingModifyInfoDto.getPassword())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
            }

            c = this.commentService.modify(c, meetingModifyInfoDto.getContent());

            CommentModifyForm commentModifyForm = new CommentModifyForm(meetingModifyInfoDto.getContent());
            return ResponseEntity.ok(commentModifyForm);

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "요청하신 데이터를 찾을 수 없습니다.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<MeetingSuccessDto> deleteComment(@PathVariable("id") Long id, @Valid @RequestBody MeetingDeleteInfoDto meetingDeleteInfoDto) {
        Optional<Comment> comment = this.commentService.getComment(id);
        if (comment.isPresent()) {
            Comment c = comment.get();

            if (!c.getPassword().equals(meetingDeleteInfoDto.getPassword())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
            }

            MeetingSuccessDto meetingSuccessDto = new MeetingSuccessDto(this.commentService.delete(c));
            return ResponseEntity.ok(meetingSuccessDto);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"요청하신 데이터를 찾을 수 없습니다.");
        }


    }

}