## 保护接口

### Cost 消费

- POST `/cost/add` 创建
  - price 价格
  - tid 标签
  - note 笔记

- POST `/cost/del` 删除
  - cid

- POST `/cost/edit` 修改
  - cid
  - price 价格
  - tid 标签
  - note 笔记

- POST `/cost/get` 查询
  - filter 筛选条件 (JSON Array)
  
    示例：`[{ "name": "note", "value":"KeyWords" }]`

### Tag 标签

- POST `/tag/add` 创建
  - name 标签名
  - desc 描述
  - icon 图标

- POST `/tag/del` 删除
  - tid

- POST `/tag/edit` 修改
  - tid
  - name 标签名
  - desc 描述
  - icon 图标

- POST `/tag/get` 查询

### User

- POST `/user/get` 获取用户个人数据
- POST `/user/logout` 注销当前用户

## 公开接口

- POST `/user/login` 登录
  - username 用户名
  - password 密码

- POST `/user/signup` 注册
  - username 用户名
  - email 邮箱
  - password 密码
  - re_password 确认密码

- POST `/user/status` 登录状态
