import request from '@/utils/request'

// 创建物料
export function createItem(data) {
  return request({
    url: '/plm/item/create',
    method: 'post',
    data: data
  })
}

// 更新物料
export function updateItem(data) {
  return request({
    url: '/plm/item/update',
    method: 'put',
    data: data
  })
}

// 删除物料
export function deleteItem(id) {
  return request({
    url: '/plm/item/delete?id=' + id,
    method: 'delete'
  })
}

// 获得物料
export function getItem(id) {
  return request({
    url: '/plm/item/get?id=' + id,
    method: 'get'
  })
}

// 获得物料分页
export function getItemPage(query) {
  return request({
    url: '/plm/item/page',
    method: 'get',
    params: query
  })
}

// 导出物料 Excel
export function exportItemExcel(query) {
  return request({
    url: '/plm/item/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
