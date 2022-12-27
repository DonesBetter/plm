<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="物料编码" prop="itemCode">
        <el-input v-model="queryParams.itemCode" placeholder="请输入物料编码" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="物料版本" prop="version">
        <el-input v-model="queryParams.version" placeholder="请输入物料版本" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="物料名称" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入物料名称" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="物料分类ID" prop="categoryId">
        <el-input v-model="queryParams.categoryId" placeholder="请输入物料分类ID" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="物料分类代码" prop="categoryCode">
        <el-input v-model="queryParams.categoryCode" placeholder="请输入物料分类代码" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="材料" prop="material">
        <el-input v-model="queryParams.material" placeholder="请输入材料" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="成本" prop="cost">
        <el-input v-model="queryParams.cost" placeholder="请输入成本" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="重量" prop="weight">
        <el-input v-model="queryParams.weight" placeholder="请输入重量" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="计量单位" prop="unit">
        <el-input v-model="queryParams.unit" placeholder="请输入计量单位" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="库存" prop="stock">
        <el-input v-model="queryParams.stock" placeholder="请输入库存" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="图标" prop="picUrl">
        <el-input v-model="queryParams.picUrl" placeholder="请输入图标" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="状态;0-启用，1-停用" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态;0-启用，1-停用" clearable size="small">
          <el-option label="请选择字典生成" value="" />
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker v-model="queryParams.createTime" style="width: 240px" value-format="yyyy-MM-dd HH:mm:ss" type="daterange"
                        range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期" :default-time="['00:00:00', '23:59:59']" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 操作工具栏 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                   v-hasPermi="['plm:item:create']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" :loading="exportLoading"
                   v-hasPermi="['plm:item:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 列表 -->
    <el-table v-loading="loading" :data="list">
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="物料编码" align="center" prop="itemCode" />
      <el-table-column label="物料版本" align="center" prop="version" />
      <el-table-column label="物料名称" align="center" prop="name" />
      <el-table-column label="物料分类ID" align="center" prop="categoryId" />
      <el-table-column label="物料分类代码" align="center" prop="categoryCode" />
      <el-table-column label="材料" align="center" prop="material" />
      <el-table-column label="成本" align="center" prop="cost" />
      <el-table-column label="重量" align="center" prop="weight" />
      <el-table-column label="计量单位" align="center" prop="unit" />
      <el-table-column label="库存" align="center" prop="stock" />
      <el-table-column label="图标" align="center" prop="picUrl" />
      <el-table-column label="状态;0-启用，1-停用" align="center" prop="status" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template v-slot="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template v-slot="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['plm:item:update']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                     v-hasPermi="['plm:item:delete']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页组件 -->
    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNo" :limit.sync="queryParams.pageSize"
                @pagination="getList"/>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="500px" v-dialogDrag append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="物料编码" prop="itemCode">
          <el-input v-model="form.itemCode" placeholder="请输入物料编码" />
        </el-form-item>
        <el-form-item label="物料版本" prop="version">
          <el-input v-model="form.version" placeholder="请输入物料版本" />
        </el-form-item>
        <el-form-item label="物料名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入物料名称" />
        </el-form-item>
        <el-form-item label="物料分类ID" prop="categoryId">
          <el-input v-model="form.categoryId" placeholder="请输入物料分类ID" />
        </el-form-item>
        <el-form-item label="物料分类代码" prop="categoryCode">
          <el-input v-model="form.categoryCode" placeholder="请输入物料分类代码" />
        </el-form-item>
        <el-form-item label="材料" prop="material">
          <el-input v-model="form.material" placeholder="请输入材料" />
        </el-form-item>
        <el-form-item label="成本" prop="cost">
          <el-input v-model="form.cost" placeholder="请输入成本" />
        </el-form-item>
        <el-form-item label="重量" prop="weight">
          <el-input v-model="form.weight" placeholder="请输入重量" />
        </el-form-item>
        <el-form-item label="计量单位" prop="unit">
          <el-input v-model="form.unit" placeholder="请输入计量单位" />
        </el-form-item>
        <el-form-item label="库存" prop="stock">
          <el-input v-model="form.stock" placeholder="请输入库存" />
        </el-form-item>
        <el-form-item label="图标" prop="picUrl">
          <el-input v-model="form.picUrl" placeholder="请输入图标" />
        </el-form-item>
        <el-form-item label="状态;0-启用，1-停用" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
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
import { createItem, updateItem, deleteItem, getItem, getItemPage, exportItemExcel } from "@/api/plm/item";

export default {
  name: "Item",
  components: {
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 物料列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        itemCode: null,
        version: null,
        name: null,
        categoryId: null,
        categoryCode: null,
        material: null,
        cost: null,
        weight: null,
        unit: null,
        stock: null,
        picUrl: null,
        status: null,
        createTime: [],
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        itemCode: [{ required: true, message: "物料编码不能为空", trigger: "blur" }],
        version: [{ required: true, message: "物料版本不能为空", trigger: "blur" }],
        name: [{ required: true, message: "物料名称不能为空", trigger: "blur" }],
        categoryId: [{ required: true, message: "物料分类ID不能为空", trigger: "blur" }],
        categoryCode: [{ required: true, message: "物料分类代码不能为空", trigger: "blur" }],
        status: [{ required: true, message: "状态;0-启用，1-停用不能为空", trigger: "blur" }],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 执行查询
      getItemPage(this.queryParams).then(response => {
        this.list = response.data.list;
        this.total = response.data.total;
        this.loading = false;
      });
    },
    /** 取消按钮 */
    cancel() {
      this.open = false;
      this.reset();
    },
    /** 表单重置 */
    reset() {
      this.form = {
        id: undefined,
        itemCode: undefined,
        version: undefined,
        name: undefined,
        categoryId: undefined,
        categoryCode: undefined,
        material: undefined,
        cost: undefined,
        weight: undefined,
        unit: undefined,
        stock: undefined,
        picUrl: undefined,
        status: undefined,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNo = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加物料";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getItem(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改物料";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (!valid) {
          return;
        }
        // 修改的提交
        if (this.form.id != null) {
          updateItem(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createItem(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除物料编号为"' + id + '"的数据项?').then(function() {
          return deleteItem(id);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      // 处理查询参数
      let params = {...this.queryParams};
      params.pageNo = undefined;
      params.pageSize = undefined;
      this.$modal.confirm('是否确认导出所有物料数据项?').then(() => {
          this.exportLoading = true;
          return exportItemExcel(params);
        }).then(response => {
          this.$download.excel(response, '物料.xls');
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
