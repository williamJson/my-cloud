package com.tcc.account.service;

import org.mengyun.tcctransaction.api.TransactionContext;

/**
 * 功能说明：
 *
 * @author wyq
 * @email 342622023@qq.com
 * @phone 13155318100
 * @date 2020/11/3
 */
public interface AccountService {


    void redAccount(TransactionContext transactionContext, Long userId, Integer money);

    void confirmRedAccount(TransactionContext transactionContext, Long userId, Integer money);

    void cancelRedAccount(TransactionContext transactionContext, Long userId, Integer money);

}
