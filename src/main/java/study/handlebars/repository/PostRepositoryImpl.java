package study.handlebars.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import study.handlebars.dto.post.PostRequestDto;
import study.handlebars.dto.post.QPostRequestDto;

import java.util.List;

import static study.handlebars.model.QMember.member;
import static study.handlebars.model.QPost.post;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    public List<PostRequestDto> findByMemberIdToDto(Long id){
        return jpaQueryFactory.select(new QPostRequestDto(
                        member.id.as("member_id"),
                        member.name.as("member_name"),
                        post.id.as("post_id"),
                        post.title,
                        post.value.as("content")
                ))
                .from(member)
                .where(member.id.eq(id))
                .join(member.posts, post)
                .fetch();
    }
}
