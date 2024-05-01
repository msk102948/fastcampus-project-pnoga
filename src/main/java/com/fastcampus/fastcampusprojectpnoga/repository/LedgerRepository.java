package com.fastcampus.fastcampusprojectpnoga.repository;

import com.fastcampus.fastcampusprojectpnoga.domain.Ledger;
import com.fastcampus.fastcampusprojectpnoga.domain.QLedger;
import com.querydsl.core.types.dsl.DateExpression;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.web.querydsl.QuerydslPredicateArgumentResolverSupport;

public interface LedgerRepository extends
        JpaRepository<Ledger, String>,
        QuerydslPredicateExecutor<Ledger>, // 기본 검색 기능 추가
        QuerydslBinderCustomizer<QLedger> // 추가적인 검색 기능
{
    @Override
    // 검색 가능한 필드를 제한하고 싶을 때 사용
    default void customize(QuerydslBindings bindings, QLedger root){
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.baseDate, root.createdBy, root.createAt);
//        bindings.bind(root.createdBy).first(StringExpression::likeIgnoreCase); // like '${v}'
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase); // like '%${v}%'
        bindings.bind(root.baseDate).first(DateExpression::eq);
        bindings.bind(root.createAt).first(DateTimeExpression::eq);
    }
}