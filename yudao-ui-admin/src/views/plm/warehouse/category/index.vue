<template>
  <div class="app-container">

    <!-- 搜索工作栏 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="父分类ID" prop="parentId">
        <el-input v-model="queryParams.parentId" placeholder="请输入父分类ID" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="父分类代码" prop="parentCode">
        <el-input v-model="queryParams.parentCode" placeholder="请输入父分类代码" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="分类代码" prop="categoryCode">
        <el-input v-model="queryParams.categoryCode" placeholder="请输入分类代码" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="分类名称" prop="name">
        <el-input v-model="queryParams.name" placeholder="请输入分类名称" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="图标" prop="picUrl">
        <el-input v-model="queryParams.picUrl" placeholder="请输入图标" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="排序" prop="sort">
        <el-input v-model="queryParams.sort" placeholder="请输入排序" clearable @keyup.enter.native="handleQuery"/>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable size="small">
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
                   v-hasPermi="['item:category:create']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="info" plain icon="el-icon-sort" size="mini" @click="toggleExpandAll">展开/折叠</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" :loading="exportLoading"
                   v-hasPermi="['item:category:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-if="refreshTable" v-loading="loading" :data="list" row-key="id" :default-expand-all="isExpandAll"
              :tree-props="{children: 'children', hasChildren: 'hasChildren'}">
      <el-table-column label="分类代码" align="center" prop="categoryCode" />
      <el-table-column label="分类名称" align="center" prop="name" />
      <el-table-column label="图标" align="center" prop="picUrl" />
      <el-table-column label="排序" align="center" prop="sort" />
      <el-table-column label="状态" align="center" prop="status" >
        <template v-slot="scope">
          <dict-tag :type="DICT_TYPE.COMMON_STATUS" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template v-slot="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                     v-hasPermi="['system:dept:update']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-plus" @click="handleAdd(scope.row)"
                     v-hasPermi="['system:dept:create']">新增</el-button>
          <el-button v-if="scope.row.parentId !== 0" size="mini" type="text" icon="el-icon-delete"
                     @click="handleDelete(scope.row)" v-hasPermi="['system:dept:delete']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 对话框(添加 / 修改) -->
    <el-dialog :title="title" :visible.sync="open" width="600px" v-dialogDrag append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="上级分类" prop="parentId">
          <treeselect v-model="form.parentId" :options="categoryOptions" :normalizer="normalizer" placeholder="选择上级分类"/>
        </el-form-item>
        <el-form-item label="分类代码" prop="categoryCode">
          <el-input v-model="form.categoryCode" placeholder="请输入分类代码" />
        </el-form-item>
        <el-form-item label="分类名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入分类名称" />
        </el-form-item>
        <el-form-item label="描述">
          <editor v-model="form.description" :min-height="192"/>
        </el-form-item>
        <el-form-item label="图标" prop="picUrl">
          <el-input v-model="form.picUrl" placeholder="请输入图标" />
        </el-form-item>
        <el-form-item label="排序" prop="sort">
          <el-input v-model="form.sort" placeholder="请输入排序" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio v-for="dict in statusDictDatas" :key="parseInt(dict.value)" :label="parseInt(dict.value)">
              {{dict.label}}</el-radio>
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
import {
  createCategory,
  updateCategory,
  deleteCategory,
  getCategory,
  getCategoryPage,
  exportCategoryExcel,
  listSimpleCategories
} from '@/api/plm/item/category'
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import Editor from '@/components/Editor';
import { DICT_TYPE, getDictDatas } from '@/utils/dict'

export default {
  name: "Category",
  components: {
    Editor,
    Treeselect
  },
  data() {
    return {
      // 遮罩层
      loading: true,
      // 导出遮罩层
      exportLoading: false,
      // 显示搜索条件
      showSearch: true,
      // 物料分类列表
      list: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 重新渲染表格状态
      refreshTable: true,
      // 是否展开，默认全部展开
      isExpandAll: true,
      // 分类树选项
      categoryOptions: [],

      // 查询参数
      queryParams: {
        pageNo: 1,
        pageSize: 10,
        parentId: null,
        parentCode: null,
        categoryCode: null,
        name: null,
        description: null,
        picUrl: null,
        sort: null,
        status: null,
        createTime: [],
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        parentId: [{ required: true, message: "父分类ID不能为空", trigger: "blur" }],
        parentCode: [{ required: true, message: "父分类代码不能为空", trigger: "blur" }],
        categoryCode: [{ required: true, message: "分类代码不能为空", trigger: "blur" }],
        status: [{ required: true, message: "状态不能为空", trigger: "blur" }],
      },
      // 数据字典
      statusDictDatas: getDictDatas(DICT_TYPE.COMMON_STATUS)
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      // 执行查询  TODO 分页查询改为按层级查询
      getCategoryPage(this.queryParams).then(response => {
        this.list =this.handleTree(response.data.list, "id");

        this.loading = false;
      });
    },
    /** 转换分类数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      let res={
        id: node.id,
        label: node.name,
        children: node.children
      }
      console.log(res)
      return res;
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
        parentId: undefined,
        parentCode: undefined,
        categoryCode: undefined,
        name: undefined,
        description: undefined,
        picUrl: undefined,
        sort: undefined,
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
    handleAdd(row) {
      this.reset();
      if (row !== undefined) {
        this.form.parentId = row.id;
      }
      this.open = true;
      this.title = "添加分类";
      listSimpleCategories().then(response => {
        this.categoryOptions = this.handleTree(response.data, "id");
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id;
      getCategory(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改物料分类";
      });
      listSimpleCategories().then(response => {
        this.categoryOptions = this.handleTree(response.data, "id");
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
          updateCategory(this.form).then(response => {
            this.$modal.msgSuccess("修改成功");
            this.open = false;
            this.getList();
          });
          return;
        }
        // 添加的提交
        createCategory(this.form).then(response => {
          this.$modal.msgSuccess("新增成功");
          this.open = false;
          this.getList();
        });
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const id = row.id;
      this.$modal.confirm('是否确认删除物料分类编号为"' + id + '"的数据项?').then(function() {
          return deleteCategory(id);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
    },
    /** 展开/折叠操作 */
    toggleExpandAll() {
      this.refreshTable = false;
      this.isExpandAll = !this.isExpandAll;
      this.$nextTick(() => {
        this.refreshTable = true;
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      // 处理查询参数
      let params = {...this.queryParams};
      params.pageNo = undefined;
      params.pageSize = undefined;
      this.$modal.confirm('是否确认导出所有物料分类数据项?').then(() => {
          this.exportLoading = true;
          return exportCategoryExcel(params);
        }).then(response => {
          this.$download.excel(response, '物料分类.xls');
          this.exportLoading = false;
        }).catch(() => {});
    }
  }
};
</script>
