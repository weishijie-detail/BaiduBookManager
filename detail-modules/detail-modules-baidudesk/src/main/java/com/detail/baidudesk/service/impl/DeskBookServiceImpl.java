package com.detail.baidudesk.service.impl;

import java.util.List;
import com.detail.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.detail.baidudesk.mapper.DeskBookMapper;
import com.detail.baidudesk.domain.DeskBook;
import com.detail.baidudesk.service.IDeskBookService;

/**
 * 书籍信息管理Service业务层处理
 * 
 * @author detail
 * @date 2025-01-16
 */
@Service
public class DeskBookServiceImpl implements IDeskBookService 
{
    @Autowired
    private DeskBookMapper deskBookMapper;

    /**
     * 查询书籍信息管理
     * 
     * @param id 书籍信息管理主键
     * @return 书籍信息管理
     */
    @Override
    public DeskBook selectDeskBookById(Long id)
    {
        return deskBookMapper.selectDeskBookById(id);
    }

    /**
     * 查询书籍信息管理列表
     * 
     * @param deskBook 书籍信息管理
     * @return 书籍信息管理
     */
    @Override
    public List<DeskBook> selectDeskBookList(DeskBook deskBook)
    {
        return deskBookMapper.selectDeskBookList(deskBook);
    }

    /**
     * 新增书籍信息管理
     * 
     * @param deskBook 书籍信息管理
     * @return 结果
     */
    @Override
    public int insertDeskBook(DeskBook deskBook)
    {
        deskBook.setCreateTime(DateUtils.getNowDate());
        return deskBookMapper.insertDeskBook(deskBook);
    }

    /**
     * 修改书籍信息管理
     * 
     * @param deskBook 书籍信息管理
     * @return 结果
     */
    @Override
    public int updateDeskBook(DeskBook deskBook)
    {
        deskBook.setUpdateTime(DateUtils.getNowDate());
        return deskBookMapper.updateDeskBook(deskBook);
    }

    /**
     * 批量删除书籍信息管理
     * 
     * @param ids 需要删除的书籍信息管理主键
     * @return 结果
     */
    @Override
    public int deleteDeskBookByIds(Long[] ids)
    {
        return deskBookMapper.deleteDeskBookByIds(ids);
    }

    /**
     * 删除书籍信息管理信息
     * 
     * @param id 书籍信息管理主键
     * @return 结果
     */
    @Override
    public int deleteDeskBookById(Long id)
    {
        return deskBookMapper.deleteDeskBookById(id);
    }
}
