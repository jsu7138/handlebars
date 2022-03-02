package study.handlebars.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.handlebars.dto.post.PostRequestDto;
import study.handlebars.model.Member;
import study.handlebars.model.Post;
import study.handlebars.repository.MemberRepository;
import study.handlebars.repository.PostRepository;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    @Transactional
    public Long save(Post post){
        return postRepository.save(post).getId();
    }

    @Transactional
    public Long save(PostRequestDto postRequestDto){
        return postRepository.save(postRequestDto.toEntityByPost()).getId();
    }

    @Transactional
    public PostRequestDto save(PostRequestDto postRequestDto, Member member){
        Post post = postRequestDto.toEntityByPost();
        post.setMember(member);
        postRepository.save(post);

        return PostRequestDto.builder().post_id(post.getId())
                .content(post.getValue())
                .title(post.getTitle())
                .member_id(member.getId())
                .member_name(member.getName())
                .member_id(member.getId())
                .build();
    }

    public Post findById(Long id){
        return postRepository.findById(id).get();
    }

    public List<PostRequestDto> getPostByMemberId(Long id){
        return postRepository.findByMemberIdToDto(id);
    }
}
