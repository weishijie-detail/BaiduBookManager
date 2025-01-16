<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="项目名称" prop="projectName">
        <el-input
          v-model="queryParams.projectName"
          placeholder="请输入项目名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目负责人姓名" prop="projectManager">
        <el-input
          v-model="queryParams.projectManager"
          placeholder="请输入项目负责人姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目开始日期" prop="startDate">
        <el-date-picker clearable
          v-model="queryParams.startDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择项目开始日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="项目预计结束日期" prop="expectedEndDate">
        <el-date-picker clearable
          v-model="queryParams.expectedEndDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择项目预计结束日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="项目实际结束日期，未结束时为NULL" prop="actualEndDate">
        <el-date-picker clearable
          v-model="queryParams.actualEndDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择项目实际结束日期，未结束时为NULL">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="项目预算金额，单位为元" prop="projectBudget">
        <el-input
          v-model="queryParams.projectBudget"
          placeholder="请输入项目预算金额，单位为元"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['project:table:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['project:table:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['project:table:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['project:table:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="tableList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="项目唯一标识符，主键" align="center" prop="projectId" />
      <el-table-column label="项目名称" align="center" prop="projectName" />
      <el-table-column label="项目负责人姓名" align="center" prop="projectManager" />
      <el-table-column label="项目开始日期" align="center" prop="startDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="项目预计结束日期" align="center" prop="expectedEndDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.expectedEndDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="项目实际结束日期，未结束时为NULL" align="center" prop="actualEndDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.actualEndDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="项目预算金额，单位为元" align="center" prop="projectBudget" />
      <el-table-column label="项目状态" align="center" prop="projectStatus" />
      <el-table-column label="项目优先级" align="center" prop="projectPriority" />
      <el-table-column label="项目详细描述，可包含项目目标、范围等内容" align="center" prop="projectDescription" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['project:table:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['project:table:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改项目信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="form.projectName" placeholder="请输入项目名称" />
        </el-form-item>
        <el-form-item label="项目负责人姓名" prop="projectManager">
          <el-input v-model="form.projectManager" placeholder="请输入项目负责人姓名" />
        </el-form-item>
        <el-form-item label="项目开始日期" prop="startDate">
          <el-date-picker clearable
            v-model="form.startDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择项目开始日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="项目预计结束日期" prop="expectedEndDate">
          <el-date-picker clearable
            v-model="form.expectedEndDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择项目预计结束日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="项目实际结束日期，未结束时为NULL" prop="actualEndDate">
          <el-date-picker clearable
            v-model="form.actualEndDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择项目实际结束日期，未结束时为NULL">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="项目预算金额，单位为元" prop="projectBudget">
          <el-input v-model="form.projectBudget" placeholder="请输入项目预算金额，单位为元" />
        </el-form-item>
        <el-form-item label="项目详细描述，可包含项目目标、范围等内容" prop="projectDescription">
          <el-input v-model="form.projectDescription" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listTable, getTable, delTable, addTable, updateTable } from "@/api/project/table";

export default {
  name: "Table",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 项目信息表格数据
      tableList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        projectName: null,
        projectManager: null,
        startDate: null,
        expectedEndDate: null,
        actualEndDate: null,
        projectBudget: null,
        projectStatus: null,
        projectPriority: null,
        projectDescription: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        projectName: [
          { required: true, message: "项目名称不能为空", trigger: "blur" }
        ],
        projectManager: [
          { required: true, message: "项目负责人姓名不能为空", trigger: "blur" }
        ],
        startDate: [
          { required: true, message: "项目开始日期不能为空", trigger: "blur" }
        ],
        expectedEndDate: [
          { required: true, message: "项目预计结束日期不能为空", trigger: "blur" }
        ],
        projectBudget: [
          { required: true, message: "项目预算金额，单位为元不能为空", trigger: "blur" }
        ],
        projectStatus: [
          { required: true, message: "项目状态不能为空", trigger: "change" }
        ],
        projectPriority: [
          { required: true, message: "项目优先级不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询项目信息列表 */
    getList() {
      this.loading = true;
      listTable(this.queryParams).then(response => {
        this.tableList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        projectId: null,
        projectName: null,
        projectManager: null,
        startDate: null,
        expectedEndDate: null,
        actualEndDate: null,
        projectBudget: null,
        projectStatus: null,
        projectPriority: null,
        projectDescription: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.projectId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加项目信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const projectId = row.projectId || this.ids
      getTable(projectId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改项目信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.projectId != null) {
            updateTable(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTable(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const projectIds = row.projectId || this.ids;
      this.$modal.confirm('是否确认删除项目信息编号为"' + projectIds + '"的数据项？').then(function() {
        return delTable(projectIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('project/table/export', {
        ...this.queryParams
      }, `table_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
