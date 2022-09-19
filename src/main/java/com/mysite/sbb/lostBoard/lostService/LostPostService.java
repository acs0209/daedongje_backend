package com.mysite.sbb.lostBoard.lostService;

import com.mysite.sbb.exception.exception.DataNotFoundException;
import com.mysite.sbb.entity.lostEntity.LostPost;
import com.mysite.sbb.entity.lostEntity.LostPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Service
public class LostPostService {

    @Autowired
    private LostPostRepository lostPostRepository;

    // 글 등록
    public void write(LostPost lostPost, MultipartFile file) throws Exception {

        if (!file.getOriginalFilename().isEmpty()) {

            String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

            UUID uuid = UUID.randomUUID();

            String fileName = uuid + "_" + file.getOriginalFilename();

            File saveFile = new File(projectPath, fileName);

            file.transferTo(saveFile);

            lostPost.setFilename(fileName);
            lostPost.setFilepath("/files/" + fileName);

        }

        lostPostRepository.save(lostPost);

    }

    public LostPost new_create(String subject, String content, String username, Boolean isLost, String encodePassword) throws Exception {
        LostPost q = new LostPost();
        q.setSubject(subject); // 제목
        q.setContent(content); // 내용
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")); // 작성 시간 포멧팅
        q.setCreateDate(currentTime); // 작성 일시 저장
        q.setUsername(username); // 사용자 이름
        q.setPassword(encodePassword); // 암호화된 비밀 번호
        q.setIsLost(isLost);

        return q;
    }

    public Boolean delete(LostPost lostPost) {

        this.lostPostRepository.delete(lostPost);
        return true;
    }

    public Boolean isSuccessModify() {

        return true;
    }

    // 파일 삭제
    public void deleteFile(LostPost lostPost) {

        // 파일의 경로 + 파일명
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files\\" + lostPost.getFilename();
        File deleteFile = new File(filePath);

        // 파일이 존재하는지 체크 존재할경우 true, 존재하지않을경우 false
        if(deleteFile.exists()) {

            // 파일을 삭제합니다.
            deleteFile.delete();
        }

        lostPost.setFilename(null);
        lostPost.setFilepath(null);
    }

    public String getFilePath(LostPost lostPost) {
        String filePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files\\" + lostPost.getFilename();

        return filePath;
    }

    public LostPost getQuestion(Long id) {
        Optional<LostPost> question = this.lostPostRepository.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

}
