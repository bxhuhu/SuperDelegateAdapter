package asuper.cree.com.demo.bean;

/**
 * Title:
 * Description:
 * Copyright:Copyright(c)2018
 * Company: Cree
 * CreateTime:2018/8/23  14:56
 *
 * @author luyongjiang
 * @version 1.0
 */
public class ContentBean {
    public ContentBean(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
