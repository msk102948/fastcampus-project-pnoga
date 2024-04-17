package com.fastcampus.fastcampusprojectpnoga.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@ToString
@Entity
@Table(indexes = {
    @Index(columnList = "content"),
    @Index(columnList = "actDueDate")
})
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LedgerDetail extends BaseEntity{

    @Comment("가계부")
    @ManyToOne(optional = false)
    @JoinColumn(name = "ledger_id", updatable = false)
    // 기존엔 연관관계 잡지 않으려고 했는데 강의를 똑같이 따라하는 과정에서 연관관계 잡음
    private Ledger ledger;

    @Column(nullable = false)
    @Comment("구분")
    private String financialCategoryId;

    @Column(nullable = false)
    @Comment("경로")
    private String financialTypeId;

    @Column
    @Comment("내용")
    private String content;

    @Column
    @Comment("금액")
    private float budget;

    @Column
    @Comment("입금위치")
    private String depositLocation;

    @Column
    @Comment("출금위치")
    private String withdrawalLocation;

    @Column
    @Comment("집행 여부")
    @ColumnDefault("false")
    private boolean actYn = false;

    @Column(nullable = false)
    @Comment("집행 예정일")
    @ColumnDefault("now()")
    private LocalDate actDueDate = LocalDate.now();

    @Column
    @Comment("비고")
    private String remark;


    public static LedgerDetail of(Ledger ledger, String financialCategoryId, String financialTypeId, String content, float budget, String depositLocation, String withdrawalLocation, boolean actYn, LocalDate actDueDate, String remark){
        return new LedgerDetail(ledger,financialCategoryId,financialTypeId,content,budget,depositLocation,withdrawalLocation,actYn,actDueDate,remark);
    }
}
