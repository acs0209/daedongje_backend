package com.mysite.sbb.lostBoard.lostApi;

import com.mysite.sbb.lostBoard.lostDto.LostSuccessDto;
import com.mysite.sbb.entity.lostEntity.LostAnswer;
import com.mysite.sbb.entity.lostEntity.LostComment;
import com.mysite.sbb.entity.lostEntity.LostCommentRepository;
import com.mysite.sbb.lostBoard.lostForm.LostDeleteForm;
import com.mysite.sbb.lostBoard.lostService.LostAnswerService;
import com.mysite.sbb.lostBoard.lostService.LostCommentService;
import com.mysite.sbb.lostBoard.lostService.LostPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RequestMapping("/lost")
@RestController
public class LostCommentApiController {

    @Autowired
    private LostCommentService lostCommentService;

    @Autowired
    private LostCommentRepository lostCommentRepository;

    @Autowired
    private LostAnswerService lostAnswerService;

    @Autowired
    private LostPostService lostPostService;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    // 전체 대댓글 조회 API
//    @GetMapping("/comments")
//    public List<Comment> all() {
//
//        return commentRepository.findAll();
//    }
//
//    // id로 대댓글 1개 조회 API
//    @GetMapping("/comments/{id}")
//    public ResponseEntity<Comment> one(@PathVariable Long id) {
//
//        Comment comment = commentRepository.findById(id).orElse(null);
//        if (comment == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "요청하신 데이터를 찾을 수 없습니다.");
//        return new ResponseEntity<>(comment, HttpStatus.OK);
//    }

    // 대댓글 등록 API
    @PostMapping(value = "/comments/{id}")
    public ResponseEntity<LostSuccessDto> createLostPostComment(@PathVariable("id") Long id, @Valid @RequestBody LostComment lostCommentForm) {

        String encodePassword = passwordEncoder.encode(lostCommentForm.getPassword());
        lostCommentForm.setPassword(encodePassword);

        if (lostCommentForm.getUsername() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "닉네임 입력 필수");
        }

        LostAnswer lostAnswer = lostAnswerService.getAnswer(id);
        if (lostAnswer == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "요청하신 데이터를 찾을 수 없습니다.");

        LostComment lostComment = lostCommentService.create(lostAnswer, lostCommentForm);
        LostSuccessDto lostSuccessDto;
        // DB에 잘 저장 되었으면 true 아니면 false
        if (lostCommentRepository.findById(lostComment.getId()).orElse(null) != null) {
            lostSuccessDto = new LostSuccessDto(true);
        } else {
            lostSuccessDto = new LostSuccessDto(false);
        }
        if (lostComment == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "요청하신 데이터를 찾을 수 없습니다.");

        return (lostComment != null) ? ResponseEntity.status(HttpStatus.OK).body(lostSuccessDto) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 대댓글 수정 api
    @PutMapping("/comments/{id}")
    public ResponseEntity<LostSuccessDto> answerModify(@Valid @RequestBody LostComment newLostComment, @PathVariable("id") Long id) {

        LostComment exLostComment = lostCommentRepository.findById(id).orElse(null);
        if (exLostComment == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "요청하신 데이터를 찾을 수 없습니다.");

        if (passwordEncoder.matches(newLostComment.getPassword(), exLostComment.getPassword())) {

        return lostCommentRepository.findById(id)
                .map(comment -> {
                    comment.setContent(newLostComment.getContent());
                    lostCommentRepository.save(comment);

                    LostSuccessDto lostSuccessDto = new LostSuccessDto(lostPostService.isSuccessModify());
                    return ResponseEntity.status(HttpStatus.OK).body(lostSuccessDto);
                })
                .orElseGet(() -> {
                    newLostComment.setId(id);
                    lostCommentRepository.save(newLostComment);

                    LostSuccessDto lostSuccessDto = new LostSuccessDto(lostPostService.isSuccessModify());
                    return ResponseEntity.status(HttpStatus.OK).body(lostSuccessDto);
                });

        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호 불일치");
        }
    }

    // 대댓글 삭제 API
    @DeleteMapping("/comments/{id}")
    public ResponseEntity deleteComment(@PathVariable("id") Long id, @Valid @RequestBody LostDeleteForm lostDeleteForm) {
        LostComment lostComment = this.lostCommentService.getComment(id).orElse(null);
        if (lostComment == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "요청하신 데이터를 찾을 수 없습니다.");

        if (lostDeleteForm.getPassword() == null || lostDeleteForm.getPassword().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호 입력 필수");
        }

        if (!passwordEncoder.matches(lostDeleteForm.getPassword(), lostComment.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }

        LostSuccessDto lostSuccessDto = new LostSuccessDto(this.lostCommentService.delete(lostComment));
        return ResponseEntity.ok(lostSuccessDto);
    }
}