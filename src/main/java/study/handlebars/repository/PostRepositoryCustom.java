package study.handlebars.repository;

import study.handlebars.dto.post.PostRequestDto;

import java.util.List;

public interface PostRepositoryCustom {
    List<PostRequestDto> findByMemberIdToDto(Long id);
}
