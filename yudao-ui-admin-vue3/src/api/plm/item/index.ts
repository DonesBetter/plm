import request from '@/config/axios'

export interface ItemVO {
  id: number
  itemCode: string
  version: string
  name: string
  material: string
  count: string
  unit: string
  icon: string
}

export interface ItemPageReqVO extends PageParam {
  itemCode?: string
  version?: string
  name?: string
  material?: string
  count?: string
  unit?: string
  icon?: string
  createTime?: localdatetime
}

export interface ItemExcelReqVO {
  itemCode?: string
  version?: string
  name?: string
  material?: string
  count?: string
  unit?: string
  icon?: string
  createTime?: localdatetime
}

// 查询物料列表
export const getItemPageApi = async (params: ItemPageReqVO) => {
  return await request.get({ url: '/plm/item/page', params })
}

// 查询物料详情
export const getItemApi = async (id: number) => {
  return await request.get({ url: '/plm/item/get?id=' + id })
}

// 新增物料
export const createItemApi = async (data: ItemVO) => {
  return await request.post({ url: '/plm/item/create', data })
}

// 修改物料
export const updateItemApi = async (data: ItemVO) => {
  return await request.put({ url: '/plm/item/update', data })
}

// 删除物料
export const deleteItemApi = async (id: number) => {
  return await request.delete({ url: '/plm/item/delete?id=' + id })
}

// 导出物料 Excel
export const exportItemApi = async (params: ItemExcelReqVO) => {
  return await request.download({ url: '/plm/item/export-excel', params })
}
