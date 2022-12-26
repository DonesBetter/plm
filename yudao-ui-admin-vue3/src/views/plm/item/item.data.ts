import { reactive } from 'vue'
import { useI18n } from '@/hooks/web/useI18n'
import { DICT_TYPE } from '@/utils/dict'
import { required } from '@/utils/formRules'
import { VxeCrudSchema, useVxeCrudSchemas } from '@/hooks/web/useVxeCrudSchemas'
const { t } = useI18n() // 国际化
// 表单校验
export const rules = reactive({
  itemCode: [required],
})
// CrudSchema
const crudSchemas = reactive<VxeCrudSchema>({
  primaryKey: 'id', // 默认的主键ID
  primaryTitle: t('common.index'), // 默认显示的值
  primaryType: 'seq', // 默认为seq，序号模式
  action: true,
  actionWidth: '200', // 3个按钮默认200，如有删减对应增减即可
  columns: [
    },
    {
      title: '物料代码',
      field: 'itemCode',
      isSearch: true,
    },
    {
      title: 'version',
      field: 'version',
      isSearch: true,
    },
    {
      title: 'name',
      field: 'name',
      isSearch: true,
    },
    {
      title: '材料',
      field: 'material',
      isSearch: true,
    },
    {
      title: '数量',
      field: 'count',
      isSearch: true,
    },
    {
      title: '计量单位',
      field: 'unit',
      isSearch: true,
    },
    {
      title: '图标',
      field: 'icon',
      isSearch: true,
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