package com.cree.superdelegate.adapter.dele;

/**
 * Title:
 * Description:
 * Copyright:Copyright(c)2018
 * Company: Cree
 * CreateTime:2018/5/24  17:42
 *
 * @author luyongjiang
 * @version 1.0
 */

public  abstract class TypeDelegate<DA> implements TypeDele<DA> {
    private int type = -1;

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
