package study.handlebars.controller;

import com.fasterxml.jackson.databind.exc.InvalidNullException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import study.handlebars.dto.post.PostRequestDto;
import study.handlebars.model.Member;
import study.handlebars.service.MemberSerive;
import study.handlebars.service.PostService;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ApiController {
    private PostService postService;
    private MemberSerive memberSerive;

    public ApiController(PostService postService, MemberSerive memberSerive) {
        this.postService = postService;
        this.memberSerive = memberSerive;
    }

    @PostMapping("/posts")
    @ResponseBody
    public ResponseEntity<PostRequestDto> savePosts(@RequestBody PostRequestDto dto){
        return memberSerive.findByName(dto.getMember_name())
                .map(member -> {
                    PostRequestDto post = postService.save(dto, member);
                    return new ResponseEntity<>(post, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
