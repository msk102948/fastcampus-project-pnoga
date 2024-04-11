package com.fastcampus.fastcampusprojectpnoga.domain;

import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.Date;

@Getter
@ToString
@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ledger extends BaseEntity{

    @Column
    @Comment("기준일자")
    @Setter
    private LocalDate baseDate;

    public static Ledger of(LocalDate baseDate){
        return new Ledger(baseDate);
    }


}
