package com.coindesk.call_coindesk_api.controller;

import com.coindesk.call_coindesk_api.bean.CoinDesk;
import com.coindesk.call_coindesk_api.bean.DbCoinDesk;
import com.coindesk.call_coindesk_api.bean.NewCoinDesk;
import com.coindesk.call_coindesk_api.service.CoindeskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/coindesk")
@Api(tags = "CoinDesk相關API")
public class CoinDeskController {
    //http://localhost:8081/swagger-ui/index.html

    @Autowired
    CoindeskService coindeskService;

    @GetMapping("/CoinDeskAPI")
    @ApiOperation("呼叫coindesk的API")
    public ResponseEntity<CoinDesk> CoinDeskAPI() throws Exception{
        //呼叫 coindesk 的 API
        //http://localhost:8081/coindesk/CoinDeskAPI

        CoinDesk coinDesk = coindeskService.getCoindesk();

        return ResponseEntity.status(HttpStatus.OK).body(coinDesk);
    }

    @GetMapping("/NewCoinDeskAPI")
    @ApiOperation("呼叫coindesk進行資料轉換後的API")
    public ResponseEntity<NewCoinDesk> NewCoinDeskAPI() throws Exception{
        //呼叫 coindesk 的 API，並進行資料轉換，組成新 API
        //http://localhost:8081/coindesk/NewCoinDeskAPI

        NewCoinDesk newCoinDesk = coindeskService.transCoindesk();

        return ResponseEntity.status(HttpStatus.OK).body(newCoinDesk);
    }

    @Transactional
    @PostMapping("/coin")
    @ApiOperation("新增幣別")
    public ResponseEntity<?> insert(@RequestBody DbCoinDesk dbCoinDesk) throws Exception {
        //insert
        //http://localhost:8081/coindesk/coin
        //{"code":"HKD","symbol":"&#165;","rate":"29,449.8807","description":"港幣","rate_float":29449.8807}

        boolean status = coindeskService.insert(dbCoinDesk);
        if(status){
            return ResponseEntity.status(HttpStatus.CREATED).body("新增成功");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body("新增失敗");
        }

    }

    @Transactional
    @PutMapping("/coin/{dbCoinDeskId}")
    @ApiOperation("修改幣別")
    public ResponseEntity<?> update(@PathVariable Integer dbCoinDeskId, @RequestBody DbCoinDesk dbCoinDesk) throws Exception {
        //update
        //http://localhost:8081/coindesk/coin/5
        //{"code":"NTD","symbol":"&#165;","rate":"29,449.8807","description":"新台幣","rate_float":29449.8807}

        boolean status = coindeskService.update(dbCoinDesk,dbCoinDeskId);
        if(status){
            return ResponseEntity.status(HttpStatus.OK).body("修改成功");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body("修改失敗，無該筆資料");
        }

    }

    @Transactional
    @DeleteMapping ("/coin/{dbCoinDeskId}")
    @ApiOperation("刪除幣別")
    public ResponseEntity<?> delete(@PathVariable Integer dbCoinDeskId) throws Exception {
        //delete
        //http://localhost:8081/coindesk/coin/5

        boolean status = coindeskService.delete(dbCoinDeskId);
        if(status){
            return ResponseEntity.status(HttpStatus.OK).body("刪除成功");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body("刪除失敗，無該筆資料");
        }

    }

    @GetMapping  ("/coin/{dbCoinDeskId}")
    @ApiOperation("取得幣別")
    public ResponseEntity<?> get(@PathVariable Integer dbCoinDeskId) throws Exception {
        //get
        //http://localhost:8081/coindesk/coin/1

        DbCoinDesk dbCoinDesk = coindeskService.get(dbCoinDeskId);

        if(dbCoinDesk != null) {
            return ResponseEntity.status(HttpStatus.OK).body(dbCoinDesk);
        }else{
            return ResponseEntity.status(HttpStatus.OK).body("查無資料");
        }

    }

}
