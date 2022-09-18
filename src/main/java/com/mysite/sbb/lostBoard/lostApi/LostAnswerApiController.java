package com.mysite.sbb.lostBoard.lostApi;

import com.mysite.sbb.lostBoard.lostDto.LostSuccessDto;
import com.mysite.sbb.entity.lostEntity.LostAnswer;
import com.mysite.sbb.entity.lostEntity.LostAnswerRepository;
import com.mysite.sbb.entity.lostEntity.LostPost;
import com.mysite.sbb.lostBoard.lostForm.LostDeleteForm;
import com.mysite.sbb.lostBoard.lostService.LostAnswerService;
import com.mysite.sbb.lostBoard.lostService.LostPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RequestMapping("/lost")
@RequiredArgsConstructor
@RestController
public class LostAnswerApiController {

    private final LostPostService lostPostService;
    private final LostAnswerRepository lostAnswerRepository;
    private final LostAnswerService lostAnswerService;
    private final PasswordEncoder passwordEncoder;

//    // 전체 댓글 조회 API
//    @GetMapping("/answers")
//    public List<Answer> all() {
//
//        return answerRepository.findAll();
//    }
//
//    // id로 댓글 1개 조회 API
//    @GetMapping("/answers/{id}")
//    public ResponseEntity<Answer> one(@PathVariable Long id) {
//
//        Answer answer = answerRepository.findById(id).orElse(null);
//        if (answer == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "요청하신 데이터를 찾을 수 없습니다.");
//        return new ResponseEntity<>(answer, HttpStatus.OK);
//    }

    // 댓글 등록 API
    @PostMapping("/answers/{id}")
    public ResponseEntity<LostSuccessDto> answerCreate(@PathVariable Long id, @Valid @RequestBody LostAnswer lostAnswerForm){

        String encodePassword = passwordEncoder.encode(lostAnswerForm.getPassword());
        lostAnswerForm.setPassword(encodePassword);

        if (lostAnswerForm.getUsername() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "닉네임 입력 필수");
        }

        LostPost lostPost = this.lostPostService.getQuestion(id);
        if (lostPost == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "요청하신 데이터를 찾을 수 없습니다.");

        LostAnswer lostAnswer = this.lostAnswerService.create(lostPost, lostAnswerForm);

        LostSuccessDto lostSuccessDto;
        // DB에 잘 저장 되었으면 true 아니면 false
        if (lostAnswerRepository.findById(lostAnswer.getId()).orElse(null) != null) {
            lostSuccessDto = new LostSuccessDto(true);
        } else {
            lostSuccessDto = new LostSuccessDto(false);
        }

        if (lostAnswer == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "요청하신 데이터를 찾을 수 없습니다.");

        return (lostAnswer != null) ? ResponseEntity.status(HttpStatus.OK).body(lostSuccessDto) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 댓글 수정 api
    @PutMapping("/answers/{id}")
    public ResponseEntity<LostSuccessDto> answerModify(@Valid @RequestBody LostAnswer newLostAnswer, @PathVariable("id") Long id) {

        LostAnswer exLostAnswer = lostAnswerRepository.findById(id).orElse(null);
        if (exLostAnswer == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "요청하신 데이터를 찾을 수 없습니다.");

        if (passwordEncoder.matches(newLostAnswer.getPassword(), exLostAnswer.getPassword())) {

        return lostAnswerRepository.findById(id)
                .map(answer -> {
                    answer.setContent(newLostAnswer.getContent());
                    lostAnswerRepository.save(answer);

                    LostSuccessDto lostSuccessDto = new LostSuccessDto(lostPostService.isSuccessModify());
                    return ResponseEntity.status(HttpStatus.OK).body(lostSuccessDto);
                })
                .orElseGet(() -> {
                    newLostAnswer.setId(id);
                    lostAnswerRepository.save(newLostAnswer);

                    LostSuccessDto lostSuccessDto = new LostSuccessDto(lostPostService.isSuccessModify());
                    return ResponseEntity.status(HttpStatus.OK).body(lostSuccessDto);
                });

        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호 불일치");
        }
    }

    // 댓글 삭제 API
    @DeleteMapping("/answers/{id}")
    public ResponseEntity deleteComment(@PathVariable("id") Long id, @Valid @RequestBody LostDeleteForm lostDeleteForm) {
        LostAnswer lostAnswer = this.lostAnswerService.getAnswer(id);
        if (lostAnswer == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "요청하신 데이터를 찾을 수 없습니다.");

        if (lostDeleteForm.getPassword() == null || lostDeleteForm.getPassword().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호 입력 필수");
        }

        if (passwordEncoder.matches(lostDeleteForm.getPassword(), lostAnswer.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }

        LostSuccessDto lostSuccessDto = new LostSuccessDto(this.lostAnswerService.delete(lostAnswer));
        return ResponseEntity.ok(lostSuccessDto);

    }

}
