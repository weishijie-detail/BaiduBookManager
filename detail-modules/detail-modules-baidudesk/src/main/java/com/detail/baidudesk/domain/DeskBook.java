package com.detail.baidudesk.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.detail.common.core.annotation.Excel;
import com.detail.common.core.web.domain.BaseEntity;

/**
 * 书籍信息管理对象 desk_book
 * 
 * @author detail
 * @date 2025-01-16
 */
public class DeskBook extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 书籍 */
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String bookName;

    /** 标签 */
    @Excel(name = "标签")
    private String bookTag;

    /** 来源 */
    @Excel(name = "来源")
    private String source;

    /** 备注 */
    @Excel(name = "备注")
    private String notes;

    /** 百度云链接 */
    @Excel(name = "百度云链接")
    private String baiduLink;

    /** 夸克网盘链接 */
    @Excel(name = "夸克网盘链接")
    private String kuaishouLink;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBookName(String bookName) 
    {
        this.bookName = bookName;
    }

    public String getBookName() 
    {
        return bookName;
    }
    public void setBookTag(String bookTag) 
    {
        this.bookTag = bookTag;
    }

    public String getBookTag() 
    {
        return bookTag;
    }
    public void setSource(String source) 
    {
        this.source = source;
    }

    public String getSource() 
    {
        return source;
    }
    public void setNotes(String notes) 
    {
        this.notes = notes;
    }

    public String getNotes() 
    {
        return notes;
    }
    public void setBaiduLink(String baiduLink) 
    {
        this.baiduLink = baiduLink;
    }

    public String getBaiduLink() 
    {
        return baiduLink;
    }
    public void setKuaishouLink(String kuaishouLink) 
    {
        this.kuaishouLink = kuaishouLink;
    }

    public String getKuaishouLink() 
    {
        return kuaishouLink;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("bookName", getBookName())
            .append("bookTag", getBookTag())
            .append("source", getSource())
            .append("updateTime", getUpdateTime())
            .append("createTime", getCreateTime())
            .append("notes", getNotes())
            .append("baiduLink", getBaiduLink())
            .append("kuaishouLink", getKuaishouLink())
            .toString();
    }
}
