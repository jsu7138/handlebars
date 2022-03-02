package study.handlebars.model;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass   //속성을 내려줘서 컬럼으로 인식하도록 한다.
@EntityListeners(AuditingEntityListener.class)  //Auditing 기능을 포함시킨다.
public abstract class BaseTimeEntity {
    @CreatedDate
    private LocalDateTime createDateTime;

    @LastModifiedDate
    private LocalDateTime modifiedDateTime;
}
