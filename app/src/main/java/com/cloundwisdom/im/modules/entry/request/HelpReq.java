package com.cloundwisdom.im.modules.entry.request;


/**
 * 帮助请求接口参数
 *
 * @author wangxiangyi
 * @date 2019/03/15
 */
public class HelpReq {
    private String id;
    private String type;
    /**
     * 父级主键<br/>
     * app首页=100<br>
     * 卡生活=310411
     */
    private String parentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
