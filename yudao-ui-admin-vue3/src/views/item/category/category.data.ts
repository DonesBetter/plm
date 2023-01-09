import { reactive } from 'vue'
import { useI18n } from '@/hooks/web/useI18n'
import { required } from '@/utils/formRules'
import { VxeCrudSchema, useVxeCrudSchemas } from '@/hooks/web/useVxeCrudSchemas'
const { t } = useI18n() // 国际化
// 表单校验
export const rules = reactive({
  parentId: [required],
  parentCode: [required],
  categoryCode: [required],
  status: [required],
})
// CrudSchema
const crudSchemas = reactive<VxeCrudSchema>({
  primaryKey: 'id', // 默认的主键ID
  primaryTitle: t('common.index'), // 默认显示的值
  primaryType: 'seq', // 默认为seq，序号模式
  action: true,
  actionWidth: '200', // 3个按钮默认200，如有删减对应增减即可
  columns: [
    {
      title: '父分类ID',
      field: 'parentId',
      form: {
        component: 'InputNumber',
        value: 0
      },
      isSearch: true,
    },
    {
      title: '父分类代码',
      field: 'parentCode',
      isSearch: true,
    },
    {
      title: '分类代码',
      field: 'categoryCode',
      isSearch: true,
    },
    {
      title: '分类名称',
      field: 'name',
      isSearch: true,
    },
    {
      title: '描述',
      field: 'description',
      form: {
        component: 'Editor',
        colProps: {
          span: 24
        },
        componentProps: {
          valueHtml: ''
        }
      },
    },
    {
      title: '图标',
      field: 'picUrl',
      isSearch: true,
    },
    {
      title: '排序',
      field: 'sort',
      isSearch: true,
    },
    {
      title: '状态;0-启用，1-停用',
      field: 'status',
      form: {
        component: 'InputNumber',
        value: 0
      },
    },
    {
      title: '创建时间',
      field: 'createTime',
      isForm: false,
      search: {
        show: true,
        itemRender: {
          name: 'XDataTimePicker'
        }
      },
    },
  ]
})
export const { allSchemas } = useVxeCrudSchemas(crudSchemas)
