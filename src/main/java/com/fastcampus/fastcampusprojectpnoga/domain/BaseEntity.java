package com.fastcampus.fastcampusprojectpnoga.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass // BaseEntity를 상속한 entity들이 아래 필드들을 컬럼으로 인식하게 함
@EntityListeners(AuditingEntityListener.class) // 자동 값 맵핑 설정
public abstract class BaseEntity {
    @Id
    @Column(length = 70)
    @Comment("고유번호")
    @GeneratedValue(generator = "custom-uuid")
    @GenericGenerator(name = "custom-uuid", strategy = "com.fastcampus.fastcampusprojectpnoga.domain.CustomUUIDGenerator")
    private String id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createAt;

    @CreatedBy
    @Column(nullable = false, updatable = false)
    private String createdBy;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updateAt;

    @LastModifiedBy
    @Column(nullable = false)
    private String updatedBy;

    @Column(nullable = false)
    @ColumnDefault("false")
    @Comment("삭제여부")
    // 빌더 기능을 사용할 경우 초기화하는 값이 문제가 생길 수 있기 때문에 빌더 자체 내애서 초기화를 해줘야한다(빌드할 때 발생한 문제로 인해 추가)
    // TODO: 빌드 어노테이션과 변수 초기화 문제에 대해서 추가적으로 공부 필요
    @Builder.Default
    private boolean isDeleted = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity that = (BaseEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
