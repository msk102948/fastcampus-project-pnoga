package com.fastcampus.fastcampusprojectpnoga.domain;


import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.UUID;

public class CustomUUIDGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        // id 생성 전략: 생성 테이블 + UUID
        return object.getClass().getSimpleName() + UUID.randomUUID().toString().replace("-", "");
    }
}
