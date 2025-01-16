package com.detail.baidudesk.service;

import java.util.List;
import com.detail.baidudesk.domain.DeskBook;

/**
 * 书籍信息管理Service接口
 * 
 * @author detail
 * @date 2025-01-16
 */
public interface IDeskBookService 
{
    /**
     * 查询书籍信息管理
     * 
     * @param id 书籍信息管理主键
     * @return 书籍信息管理
     */
    public DeskBook selectDeskBookById(Long id);

    /**
     * 查询书籍信息管理列表
     * 
     * @param deskBook 书籍信息管理
     * @return 书籍信息管理集合
     */
    public List<DeskBook> selectDeskBookList(DeskBook deskBook);

    /**
     * 新增书籍信息管理
     * 
     * @param deskBook 书籍信息管理
     * @return 结果
     */
    public int insertDeskBook(DeskBook deskBook);

    /**
     * 修改书籍信息管理
     * 
     * @param deskBook 书籍信息管理
     * @return 结果
     */
    public int updateDeskBook(DeskBook deskBook);

    /**
     * 批量删除书籍信息管理
     * 
     * @param ids 需要删除的书籍信息管理主键集合
     * @return 结果
     */
    public int deleteDeskBookByIds(Long[] ids);

    /**
     * 删除书籍信息管理信息
     * 
     * @param id 书籍信息管理主键
     * @return 结果
     */
    public int deleteDeskBookById(Long id);
}
