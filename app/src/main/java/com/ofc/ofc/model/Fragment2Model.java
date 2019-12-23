package com.ofc.ofc.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zyz on 2019/6/4.
 */
public class Fragment2Model implements Serializable {

    /**
     * symbol : btc
     * response : [1.5770196E12,7168.75,7194.05,7156.5,7168,968.3623]
     * type : kline
     */

    private String symbol;
    private String type;
    private List<Double> response;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Double> getResponse() {
        return response;
    }

    public void setResponse(List<Double> response) {
        this.response = response;
    }
}
