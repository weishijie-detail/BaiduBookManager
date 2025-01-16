package com.detail.baidudesk.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.detail.common.log.annotation.Log;
import com.detail.common.log.enums.BusinessType;
import com.detail.common.security.annotation.RequiresPermissions;
import com.detail.baidudesk.domain.DeskBook;
import com.detail.baidudesk.service.IDeskBookService;
import com.detail.common.core.web.controller.BaseController;
import com.detail.common.core.web.domain.AjaxResult;
import com.detail.common.core.utils.poi.ExcelUtil;
import com.detail.common.core.web.page.TableDataInfo;

/**
 * 书籍信息管理Controller
 * 
 * @author detail
 * @date 2025-01-16
 */
@RestController
@RequestMapping("/book")
public class DeskBookController extends BaseController
{
    @Autowired
    private IDeskBookService deskBookService;

    /**
     * 查询书籍信息管理列表
     */
    @RequiresPermissions("baidudesk:book:list")
    @GetMapping("/list")
    public TableDataInfo list(DeskBook deskBook)
    {
        startPage();
        List<DeskBook> list = deskBookService.selectDeskBookList(deskBook);
        return getDataTable(list);
    }

    /**
     * 导出书籍信息管理列表
     */
    @RequiresPermissions("baidudesk:book:export")
    @Log(title = "书籍信息管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DeskBook deskBook)
    {
        List<DeskBook> list = deskBookService.selectDeskBookList(deskBook);
        ExcelUtil<DeskBook> util = new ExcelUtil<DeskBook>(DeskBook.class);
        util.exportExcel(response, list, "书籍信息管理数据");
    }

    /**
     * 获取书籍信息管理详细信息
     */
    @RequiresPermissions("baidudesk:book:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(deskBookService.selectDeskBookById(id));
    }

    /**
     * 新增书籍信息管理
     */
    @RequiresPermissions("baidudesk:book:add")
    @Log(title = "书籍信息管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DeskBook deskBook)
    {
        return toAjax(deskBookService.insertDeskBook(deskBook));
    }

    /**
     * 修改书籍信息管理
     */
    @RequiresPermissions("baidudesk:book:edit")
    @Log(title = "书籍信息管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DeskBook deskBook)
    {
        return toAjax(deskBookService.updateDeskBook(deskBook));
    }

    /**
     * 删除书籍信息管理
     */
    @RequiresPermissions("baidudesk:book:remove")
    @Log(title = "书籍信息管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(deskBookService.deleteDeskBookByIds(ids));
    }
}
