package study.handlebars.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.handlebars.dto.post.PostRequestDto;
import study.handlebars.model.Member;
import study.handlebars.model.Post;
import study.handlebars.repository.MemberRepository;
import study.handlebars.repository.PostRepository;

import javax.persistence.EntityManager;

import java.util.Optional;
import java.util.Random;

@SpringBootTest
@Transactional
@Rollback(value = false)
class PostServiceTest {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final EntityManager em;

    @Autowired
    private MemberSerive memberSerive;
    @Autowired
    private PostService postService;

    @Autowired
    public PostServiceTest(PostRepository postRepository, EntityManager em, MemberRepository memberRepository) {
        this.postRepository = postRepository;
        this.em = em;
        this.memberRepository = memberRepository;
    }

    @Test
    public void 테스트1(){
        Post build = Post.builder().title("").build();
        em.persist(build);
        em.persist(new Post("dd","dd"));
        System.out.println("build = " + build);
    }

    @Test
    public void 테스트2(){
        Post post = Post.builder().title("얌얌").value("ddd").build();
        postRepository.save(post);
        System.out.println("build = " + post);
    }

    @Test
    public void 테스트3(){
        Post post = Post.builder().title("얌얌").value("ddd").build();
        postRepository.save(post);
        System.out.println("build = " + post);
    }

    @Test
    public void 테스트22(){
        PostRequestDto postRequestDto = new PostRequestDto("member9999","titie23992919","asd");

        ResponseEntity<Member> memberResponseEntity = memberSerive.findByName(postRequestDto.getMember_name())
                .map(result -> {
                    
                    return new ResponseEntity<>(result, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        System.out.println("postRequestDtoResponseEntity = " + memberResponseEntity);
    }

    @Test
    public void 테스트4(){
        PostRequestDto postRequestDto = new PostRequestDto("member9999","titie23992919","asd");
        ResponseEntity<PostRequestDto> postRequestDtoResponseEntity = memberSerive.findByName(postRequestDto.getMember_name())
                .map(member -> {
                    PostRequestDto post = postService.save(postRequestDto, member);
                    return new ResponseEntity<>(post, HttpStatus.OK);
                }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

        System.out.println("postRequestDtoResponseEntity = " + postRequestDtoResponseEntity);
    }

    @Test
    void 테스트ITER(){
        Optional<Member> dkoo = memberRepository.findByName("dkoo");
        System.out.println("dkoo = " + dkoo.isPresent());

        String[] text = new String[]{"",""};
        //iter 단축 커맨드
        for (String s : text) {

        }
    }
}