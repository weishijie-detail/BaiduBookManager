import request from '@/utils/request'

// 查询项目信息列表
export function listTable(query) {
  return request({
    url: '/project/table/list',
    method: 'get',
    params: query
  })
}

// 查询项目信息详细
export function getTable(projectId) {
  return request({
    url: '/project/table/' + projectId,
    method: 'get'
  })
}

// 新增项目信息
export function addTable(data) {
  return request({
    url: '/project/table',
    method: 'post',
    data: data
  })
}

// 修改项目信息
export function updateTable(data) {
  return request({
    url: '/project/table',
    method: 'put',
    data: data
  })
}

// 删除项目信息
export function delTable(projectId) {
  return request({
    url: '/project/table/' + projectId,
    method: 'delete'
  })
}
