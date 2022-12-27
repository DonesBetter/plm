import request from '@/config/axios'

export interface CategoryVO {
  id: number
  parentId: number
  parentCode: string
  categoryCode: string
  name: string
  description: string
  picUrl: string
  sort: string
  status: number
}

export interface CategoryPageReqVO extends PageParam {
  parentId?: number
  parentCode?: string
  categoryCode?: string
  name?: string
  description?: string
  picUrl?: string
  sort?: string
  status?: number
  createTime?: localdatetime
}

export interface CategoryExcelReqVO {
  parentId?: number
  parentCode?: string
  categoryCode?: string
  name?: string
  description?: string
  picUrl?: string
  sort?: string
  status?: number
  createTime?: localdatetime
}

// 查询物料分类列表
export const getCategoryPageApi = async (params: CategoryPageReqVO) => {
  return await request.get({ url: '/item/category/page', params })
}

// 查询物料分类详情
export const getCategoryApi = async (id: number) => {
  return await request.get({ url: '/item/category/get?id=' + id })
}

// 新增物料分类
export const createCategoryApi = async (data: CategoryVO) => {
  return await request.post({ url: '/item/category/create', data })
}

// 修改物料分类
export const updateCategoryApi = async (data: CategoryVO) => {
  return await request.put({ url: '/item/category/update', data })
}

// 删除物料分类
export const deleteCategoryApi = async (id: number) => {
  return await request.delete({ url: '/item/category/delete?id=' + id })
}

// 导出物料分类 Excel
export const exportCategoryApi = async (params: CategoryExcelReqVO) => {
  return await request.download({ url: '/item/category/export-excel', params })
}
