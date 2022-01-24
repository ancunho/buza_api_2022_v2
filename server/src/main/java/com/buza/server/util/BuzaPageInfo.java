package com.buza.server.util;

import com.github.pagehelper.PageInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BuzaPageInfo extends PageInfo {

    private int count;

    public BuzaPageInfo(Object returnData) {

    }

    public int getCount() {
        return this.getSize();
    }

    public void setCount(int count) {
        this.count = count;
    }
}
