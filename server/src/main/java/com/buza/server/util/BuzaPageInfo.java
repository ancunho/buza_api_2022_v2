package com.buza.server.util;

import com.buza.server.dto.SysMenuDto;
import com.github.pagehelper.PageInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

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
