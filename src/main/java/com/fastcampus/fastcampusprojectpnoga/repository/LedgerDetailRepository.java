package com.fastcampus.fastcampusprojectpnoga.repository;

import com.fastcampus.fastcampusprojectpnoga.domain.LedgerDetail;
import com.fastcampus.fastcampusprojectpnoga.domain.QLedger;
import com.fastcampus.fastcampusprojectpnoga.domain.QLedgerDetail;
import com.querydsl.core.types.dsl.DateExpression;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface LedgerDetailRepository extends
    JpaRepository<LedgerDetail, String>,
    QuerydslPredicateExecutor<LedgerDetail>,
    QuerydslBinderCustomizer<QLedgerDetail> {
    @Override
    // 검색 가능한 필드를 제한하고 싶을 때 사용
    default void customize(QuerydslBindings bindings, QLedgerDetail root){
        // 기본적으로 QuerydslPredicateExecutor의 검색 기능을 제한 하기 위한 설정
        // 가계부 상세의 경우 모든 필드에 대한 검색이 가능해야하기 때문에 사용하지 않는다.
        /*
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.baseDate, root.createdBy, root.createAt);
        */
//        bindings.bind(root.createdBy).first(StringExpression::likeIgnoreCase); // like '${v}'
//        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase); // like '%${v}%'

        // 여기서 부터는 Like 검색이 가능해야하는 컬럼들만 내용을 정의
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.remark).first(StringExpression::containsIgnoreCase);
    }
}