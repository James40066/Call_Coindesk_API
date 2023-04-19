package com.coindesk.call_coindesk_api.dao;

import com.coindesk.call_coindesk_api.bean.DbCoinDesk;
import org.springframework.data.repository.CrudRepository;

public interface DbCoinDeskRepository extends CrudRepository<DbCoinDesk,Integer> {


}
