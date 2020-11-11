package com.tcc.account.service.impl;

import com.tcc.account.service.AccountService;
import org.mengyun.tcctransaction.api.Compensable;
import org.mengyun.tcctransaction.api.TransactionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 功能说明：
 *
 * @author wyq
 * @email 342622023@qq.com
 * @phone 13155318100
 * @date 2020/11/3
 */
@Service
public class AccountServiceImpl implements AccountService {


    @Compensable(confirmMethod = "confirmRedAccount", cancelMethod = "cancelRedAccount")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void redAccount(TransactionContext transactionContext, Long userId, Integer money) {

    }

    @Override
    public void confirmRedAccount(TransactionContext transactionContext, Long userId, Integer money) {

    }

    @Override
    public void cancelRedAccount(TransactionContext transactionContext, Long userId, Integer money) {

    }
}
