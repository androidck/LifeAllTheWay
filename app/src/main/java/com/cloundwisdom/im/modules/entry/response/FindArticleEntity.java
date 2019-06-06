package com.cloundwisdom.im.modules.entry.response;

import java.io.Serializable;

/**
 * 全部文章列表返回实体
 *
 * @author wangxiangyi
 * @date 2019/03/16
 */
public class FindArticleEntity {
    /**
     * 是否显示<br>
     * true：是<br>
     * true：否
     */
    private boolean isShowTitle;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 创建时间
     */
    private String createDate;
    /**
     * 更新时间
     */
    private String updateDate;
    /**
     * 主键
     */
    private String id;
    /**
     * 标题
     */
    private String titile;
    /**
     * 简介
     */
    private String summary;
    /**
     * 内容
     */
    private String content;
    /**
     * 文章图片
     */
    private String coverPhoto;
    /**
     * 阅读次数
     */
    private String readCount;
    /**
     * 是否置顶1是0否
     */
    private String isTop;
    /**
     * 是否推荐1是0否
     */
    private String isRecommend;

    public boolean isShowTitle() {
        return isShowTitle;
    }

    public void setShowTitle(boolean showTitle) {
        isShowTitle = showTitle;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public String getReadCount() {
        return readCount;
    }

    public void setReadCount(String readCount) {
        this.readCount = readCount;
    }

    public String getIsTop() {
        return isTop;
    }

    public void setIsTop(String isTop) {
        this.isTop = isTop;
    }

    public String getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(String isRecommend) {
        this.isRecommend = isRecommend;
    }

    @Override
    public String toString() {
        return "FindArticleEntity{" +
                "isShowTitle=" + isShowTitle +
                ", remarks='" + remarks + '\'' +
                ", createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", id='" + id + '\'' +
                ", titile='" + titile + '\'' +
                ", summary='" + summary + '\'' +
                ", content='" + content + '\'' +
                ", coverPhoto='" + coverPhoto + '\'' +
                ", readCount='" + readCount + '\'' +
                ", isTop='" + isTop + '\'' +
                ", isRecommend='" + isRecommend + '\'' +
                '}';
    }
}
