import { reactive } from 'vue'
import { useI18n } from '@/hooks/web/useI18n'
import { DICT_TYPE } from '@/utils/dict'
import { required } from '@/utils/formRules'
import { VxeCrudSchema, useVxeCrudSchemas } from '@/hooks/web/useVxeCrudSchemas'
const { t } = useI18n() // 国际化
// 表单校验
export const rules = reactive({
  name: [required],
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
      title: '文档名',
      field: 'name',
      isSearch: true,
    },
    {
      title: '文档版本',
      field: 'version',
      isSearch: true,
    },
    {
      title: '文档描述',
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
      title: '文件大小',
      field: 'size',
      form: {
        component: 'InputNumber',
        value: 0
      },
      isSearch: true,
    },
    {
      title: '路径',
      field: 'path',
      isSearch: true,
    },
    {
      title: '链接',
      field: 'url',
      isSearch: true,
    },
    {
      title: '状态',
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
      formatter: 'formatDate',
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