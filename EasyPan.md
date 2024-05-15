## 全局公共参数
#### 全局Header参数
参数名 | 示例值 | 参数描述
--- | --- | ---
暂无参数
#### 全局Query参数
参数名 | 示例值 | 参数描述
--- | --- | ---
暂无参数
#### 全局Body参数
参数名 | 示例值 | 参数描述
--- | --- | ---
暂无参数
#### 全局认证方式
```text
noauth
```
#### 全局预执行脚本
```javascript
暂无预执行脚本
```
#### 全局后执行脚本
```javascript
暂无后执行脚本
```
## /EasyPan
```text
暂无描述
```
#### Header参数
参数名 | 示例值 | 参数描述
--- | --- | ---
暂无参数
#### Query参数
参数名 | 示例值 | 参数描述
--- | --- | ---
暂无参数
#### Body参数
参数名 | 示例值 | 参数描述
--- | --- | ---
暂无参数
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
## /EasyPan/登录注册
```text
暂无描述
```
#### Header参数
参数名 | 示例值 | 参数描述
--- | --- | ---
暂无参数
#### Query参数
参数名 | 示例值 | 参数描述
--- | --- | ---
暂无参数
#### Body参数
参数名 | 示例值 | 参数描述
--- | --- | ---
暂无参数
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
## /EasyPan/登录注册/获取验证码
```text
暂无描述
```
#### 接口状态
> 已完成

#### 接口URL
> http://localhost:7090/api/checkCode?type=0

#### 请求方式
> GET

#### Content-Type
> none

#### 请求Query参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
type | 0 | String | 是 | 0:登录注册  1:邮箱验证码发送  默认0
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
## /EasyPan/登录注册/发送邮箱验证码
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/sendEmailCode

#### 请求方式
> POST

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
email | laoluo_coder@qq.com | String | 是 | 邮箱
checkCode | sqhfc | String | 是 | 图片验证码
type | 0 | String | 是 | 0:注册  1:找回密码
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"status": "success",
	"code": 200,
	"info": "请求成功",
	"data": null
}
```
## /EasyPan/登录注册/注册
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/register

#### 请求方式
> POST

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
email | laoluo_coder@qq.com | String | 是 | 邮箱
nickName | 程序员老罗 | String | 是 | 昵称
password | test123456 | String | 是 | 密码 只能是数字，字母，特殊字符 8-18位
checkCode | ycmzz | String | 是 | 图片验证码
emailCode | 41580 | String | 是 | 邮箱验证码
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"status": "success",
	"code": 200,
	"info": "请求成功",
	"data": null
}
```
## /EasyPan/登录注册/账号登录
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/login

#### 请求方式
> POST

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
email | laoluo_coder@qq.com | String | 是 | 邮箱
password | 47ec2dd791e31e2ef2076caf64ed9b3d | String | 是 | md5加密传输
checkCode | p1rob | String | 是 | 图片验证码
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"status": "success",
	"code": 200,
	"info": "请求成功",
	"data": {
		"nickName": "程序员老罗",
		"userId": "3178033358",
		"avatar": null,
		"admin": false
	}
}
```
## /EasyPan/登录注册/重置密码
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/resetPwd

#### 请求方式
> POST

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
email | laoluo_coder@qq.com | String | 是 | 邮箱
password | test1234561 | String | 是 | md5加密传输
checkCode | 12333 | String | 是 | 图片验证码
emailCode | 22233 | String | 是 | -
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"status": "success",
	"code": 200,
	"info": "请求成功",
	"data": null
}
```
## /EasyPan/登录注册/获取用户头像
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/getAvatar/{userId}

#### 请求方式
> GET

#### Content-Type
> none

#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
## /EasyPan/登录注册/获取用户空间
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/getUseSpace

#### 请求方式
> GET

#### Content-Type
> none

#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"status": "success",
	"code": 200,
	"info": "请求成功",
	"data": {
		"useSpace": 0,
		"totalSpace": 5242880
	}
}
```
## /EasyPan/登录注册/退出
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/logout

#### 请求方式
> GET

#### Content-Type
> none

#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"status": "success",
	"code": 200,
	"info": "请求成功",
	"data": null
}
```
## /EasyPan/登录注册/更新用户头像
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/updateUserAvatar

#### 请求方式
> POST

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
avatar | - | String | 是 | 头像
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"status": "success",
	"code": 200,
	"info": "请求成功",
	"data": null
}
```
## /EasyPan/登录注册/修改密码
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/updatePassword

#### 请求方式
> POST

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
password | test123456 | String | 是 | 密码 只能是数字，字母，特殊字符 8-18位
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"status": "success",
	"code": 200,
	"info": "请求成功",
	"data": null
}
```
## /EasyPan/登录注册/QQ登录
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/qqlogin

#### 请求方式
> POST

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
callbackUrl | /index | String | 否 | 登录成功后回调地址
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"status": "success",
	"code": 200,
	"info": "请求成功",
	"data": "https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id=xxxs&redirect_uri=xxxx&state=xxxxxxx"
}
```
## /EasyPan/登录注册/QQ登录回调
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/qqlogin/callback

#### 请求方式
> POST

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
code | - | String | 是 | qq官方回调的code
state | - | String | 是 | 获取登录信息后端传入的状态码
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"status": "success",
	"code": 200,
	"info": "请求成功",
	"data": {
		"callbackUrl": "xxxx",
		"userInfo": {
			"nickName": "程序员老罗",
			"userId": "3178033358",
			"avatar": null,
			"admin": false
		}
	}
}
```
## /EasyPan/首页
```text
暂无描述
```
#### Header参数
参数名 | 示例值 | 参数描述
--- | --- | ---
暂无参数
#### Query参数
参数名 | 示例值 | 参数描述
--- | --- | ---
暂无参数
#### Body参数
参数名 | 示例值 | 参数描述
--- | --- | ---
暂无参数
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
## /EasyPan/首页/文件列表
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/file/loadDataList

#### 请求方式
> POST

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
category | all | String | 是 | 分类
filePid | 0 | String | 是 | 文件父id
fileNameFuzzy | 资料 | String | 否 | 文件名
pageNo | 1 | String | 否 | 页码
pageSize | 15 | String | 否 | 分页大小
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"status": "success",
	"code": 200,
	"info": "请求成功",
	"data": {
		"totalCount": 1,
		"pageSize": 15,
		"pageNo": 1,
		"pageTotal": 1,
		"list": [
			{
				"fileId": "FBXdDvhLHg",//文件ID
				"filePid": "0",//父文件ID
				"fileSize": 15175,//文件大小
				"fileName": "xx2.jpg",//文件名
				"fileCover": "202304/102522FBXdDvhLHg_.jpg",//封面
				"createTime": "2023-04-12 09:11:41",//创建时间
				"lastUpdateTime": "2023-04-12 09:11:41",//最后更新时间
				"folderType": 0,//文件目录类型 0:文件 1目录
				"fileCategory": 3,//分类
				"fileType": 3,//文件类型
				"status": 2//状态
			}
		]
	}
}
```
## /EasyPan/首页/文件分片上传
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/file/uploadFile

#### 请求方式
> POST

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
fileId | - | String | 否 | 文件ID
file | - | String | 是 | 文件流
fileName | - | String | 是 | 文件名
filePid | - | String | 是 | 文件父id
fileMd5 | - | String | 是 | 文件MD5值
chunkIndex | - | String | 是 | 当前分片索引
chunks | - | String | 是 | 总分片数
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"status": "success",
	"code": 200,
	"info": "请求成功",
	"data": {
            "fileId":"文件ID",
            "status":"当前文件上传状态"
    }
}
```
## /EasyPan/首页/显示封面
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/file/getImage/{imageFolder}/{imageName}

#### 请求方式
> GET

#### Content-Type
> none

#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
## /EasyPan/首页/获取文件信息
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/file/getFile/{fileId}

#### 请求方式
> GET

#### Content-Type
> none

#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
## /EasyPan/首页/新建目录
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/file/newFoloder

#### 请求方式
> POST

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
filePid | - | String | 是 | 文件父id
fileName | - | String | 是 | 目录名
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"status": "success",
	"code": 200,
	"info": "请求成功",
	"data": null
}
```
## /EasyPan/首页/获取当前目录
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/file/getFolderInfo

#### 请求方式
> POST

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
path | o4rnme9FB3/lYf7edaqUi | String | 是 | 完整路径
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"status": "success",
	"code": 200,
	"info": "请求成功",
	"data": [
		{
			"fileName": "22222",
			"fileId": "o4rnme9FB3"
		},
		{
			"fileName": "23232323",
			"fileId": "lYf7edaqUi"
		}
	]
}
```
## /EasyPan/首页/文件重命名
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/file/rename

#### 请求方式
> POST

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
fileId | 111 | String | 是 | 文件ID
fileName | 文件名 | String | 是 | 文件名
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"status": "success",
	"code": 200,
	"info": "请求成功",
	"data": null
}
```
## /EasyPan/首页/获取所有目录
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/file/loadAllFolder

#### 请求方式
> POST

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
filePid | 0 | String | 是 | 文件父id
currentFileIds | - | String | 否 | 当前目录PID
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"status": "success",
	"code": 200,
	"info": "请求成功",
	"data": [
		{
			"fileId": "o4rnme9FB3",
			"filePid": "0",
			"fileSize": null,
			"fileName": "22222",
			"fileCover": null,
			"lastUpdateTime": "2023-04-12 17:09:36",
			"folderType": 1,
			"fileCategory": null,
			"fileType": null,
			"status": 2
		},
		{
			"fileId": "FPeMB7KxVf",
			"filePid": "0",
			"fileSize": null,
			"fileName": "yrdy",
			"fileCover": null,
			"lastUpdateTime": "2023-04-12 17:08:38",
			"folderType": 1,
			"fileCategory": null,
			"fileType": null,
			"status": 2
		}
	]
}
```
## /EasyPan/首页/修改文件目录、移动文件
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/file/changeFileFolder

#### 请求方式
> POST

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
fileIds | 111,1111 | String | 是 | 所选文件ID
filePid | 0 | String | 是 | 文件父id
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"status": "success",
	"code": 200,
	"info": "请求成功",
	"data": null
}
```
## /EasyPan/首页/创建下载链接
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/file/createDownloadUrl/{fileId}

#### 请求方式
> GET

#### Content-Type
> form-data

#### 路径变量
参数名 | 示例值 | 参数描述
--- | --- | ---
fileId | - | -
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"status": "success",
	"code": 200,
	"info": "请求成功",
	"data": "UEr0bAmOoi0NLui6CvRaBFtn3SLyvIf02g4XqnyVK8G5iDxZ0i"
}
```
## /EasyPan/首页/下载文件
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/file/download/{code}

#### 请求方式
> GET

#### Content-Type
> form-data

#### 路径变量
参数名 | 示例值 | 参数描述
--- | --- | ---
code | - | -
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
## /EasyPan/首页/删除文件
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/file/delFile

#### 请求方式
> POST

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
fileIds | FBXdDvhLHg,vWVCc9FxVf | String | 是 | 需要删除的文件ID
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"status": "success",
	"code": 200,
	"info": "请求成功",
	"data": null
}
```
## /EasyPan/分享
```text
暂无描述
```
#### Header参数
参数名 | 示例值 | 参数描述
--- | --- | ---
暂无参数
#### Query参数
参数名 | 示例值 | 参数描述
--- | --- | ---
暂无参数
#### Body参数
参数名 | 示例值 | 参数描述
--- | --- | ---
暂无参数
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
## /EasyPan/分享/获取分享文件列表
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/share/loadShareList

#### 请求方式
> POST

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
pageNo | - | String | 是 | 页码
pageSize | - | String | 是 | 分页大小
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"status": "success",
	"code": 200,
	"info": "请求成功",
	"data": {
		"totalCount": 1,
		"pageSize": 15,
		"pageNo": 1,
		"pageTotal": 1,
		"list": [
			{
				"shareId": "oiaWphfrb972hmtvC7B8",
				"fileId": "FBXdDvhLHg",
				"userId": "102522",
				"validType": 0,
				"expireTime": "2023-04-13 17:49:16",
				"shareTime": "2023-04-12 17:49:16",
				"code": "BAqxk",
				"showCount": 0,
				"fileName": "xx212222.jpg",
				"folderType": 0,
				"fileCategory": 3,
				"fileType": 3,
				"fileCover": "202304/102522FBXdDvhLHg_.jpg"
			}
		]
	}
}
```
## /EasyPan/分享/分享文件
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/share/shareFile

#### 请求方式
> POST

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
fileId | FBXdDvhLHg | String | 是 | 文件ID
validType | 0 | String | 是 | 分享失效类型
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"status": "success",
	"code": 200,
	"info": "请求成功",
	"data": {
		"shareId": "oiaWphfrb972hmtvC7B8",
		"fileId": "FBXdDvhLHg",
		"userId": "102522",
		"validType": 0,
		"expireTime": "2023-04-13 17:49:15",
		"shareTime": "2023-04-12 17:49:15",
		"code": "BAqxk",
		"showCount": null,
		"fileName": null,
		"folderType": null,
		"fileCategory": null,
		"fileType": null,
		"fileCover": null
	}
}
```
## /EasyPan/分享/取消分享
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/share/cancelShare

#### 请求方式
> POST

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
shareIds | oiaWphfrb972hmtvC7B8 | String | 是 | 取消分享的ID,多个逗号隔开
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"status": "success",
	"code": 200,
	"info": "请求成功",
	"data": null
}
```
## /EasyPan/回收站
```text
暂无描述
```
#### Header参数
参数名 | 示例值 | 参数描述
--- | --- | ---
暂无参数
#### Query参数
参数名 | 示例值 | 参数描述
--- | --- | ---
暂无参数
#### Body参数
参数名 | 示例值 | 参数描述
--- | --- | ---
暂无参数
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
## /EasyPan/回收站/获取回收站文件列表
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/recycle/loadRecycleList

#### 请求方式
> POST

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
pageNo | - | String | 是 | 页码
pageSize | - | String | 是 | 分页大小
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"status": "success",
	"code": 200,
	"info": "请求成功",
	"data": {
		"totalCount": 2,
		"pageSize": 15,
		"pageNo": 1,
		"pageTotal": 1,
		"list": [
			{
				"fileId": "FBXdDvhLHg",
				"filePid": "0",
				"fileSize": 15175,
				"fileName": "xx212222.jpg",
				"fileCover": "202304/102522FBXdDvhLHg_.jpg",
				"lastUpdateTime": "2023-04-12 09:30:21",
				"folderType": 0,
				"fileCategory": 3,
				"fileType": 3,
				"status": 2
			},
			{
				"fileId": "vWVCc9FxVf",
				"filePid": "0",
				"fileSize": 28480,
				"fileName": "xx3.jpg",
				"fileCover": "202304/102522vWVCc9FxVf_.jpg",
				"lastUpdateTime": "2023-04-12 09:30:29",
				"folderType": 0,
				"fileCategory": 3,
				"fileType": 3,
				"status": 2
			}
		]
	}
}
```
## /EasyPan/回收站/恢复文件
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/recycle/recoverFile

#### 请求方式
> POST

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
fileIds | xxx,xxxx | String | 是 | 所选文件ID
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
## /EasyPan/回收站/彻底删除文件
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/recycle/delFile

#### 请求方式
> POST

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
fileIds | xxx,xxx | String | 是 | 所选文件ID
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
## /EasyPan/设置(管理员)
```text
暂无描述
```
#### Header参数
参数名 | 示例值 | 参数描述
--- | --- | ---
暂无参数
#### Query参数
参数名 | 示例值 | 参数描述
--- | --- | ---
暂无参数
#### Body参数
参数名 | 示例值 | 参数描述
--- | --- | ---
暂无参数
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
## /EasyPan/设置(管理员)/获取系统设置
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/admin/getSysSettings

#### 请求方式
> POST

#### Content-Type
> none

#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"status": "success",
	"code": 200,
	"info": "请求成功",
	"data": {
		"registerEmailTitle": "邮箱验证码",
		"registerEmailContent": "你好，您的邮箱验证码是：%s，15分钟有效",
		"userInitUseSpace": 5
	}
}
```
## /EasyPan/设置(管理员)/保存系统设置
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/admin/saveSysSettings

#### 请求方式
> POST

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
registerEmailTitle | - | String | 是 | 发送邮箱标题
registerEmailContent | - | String | 是 | 发送邮箱内容
userInitUseSpace | - | String | 是 | 用户初始化空间配额
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"status": "success",
	"code": 200,
	"info": "请求成功",
	"data": null
}
```
## /EasyPan/设置(管理员)/获取用户列表
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/admin/loadUserList

#### 请求方式
> POST

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
pageNo | - | String | 是 | 页码
pageSize | - | String | 否 | 分页大小
nickNameFuzzy | - | String | 否 | 昵称模糊搜索
status | - | String | 否 | 状态
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"status": "success",
	"code": 200,
	"info": "请求成功",
	"data": {
		"totalCount": 2,
		"pageSize": 15,
		"pageNo": 1,
		"pageTotal": 1,
		"list": [
			{
				"userId": "3178033358",
				"nickName": "程序员老罗",
				"email": "laoluo_coder@qq.com",
				"qqAvatar": null,
				"joinTime": "2023-04-11 14:45:25",
				"lastLoginTime": "2023-04-11 14:47:52",
				"status": 1,
				"useSpace": 0,
				"totalSpace": 5242880
			},
			{
				"userId": "102522",
				"nickName": "程序员老罗1",
				"email": "test@qq.com",
				"qqAvatar": null,
				"joinTime": null,
				"lastLoginTime": "2023-04-12 09:11:19",
				"status": null,
				"useSpace": 0,
				"totalSpace": 104857600
			}
		]
	}
}
```
## /EasyPan/设置(管理员)/修改用户状态
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/admin/updateUserStatus

#### 请求方式
> POST

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
userId | - | String | 是 | 用户ID
status | - | String | 是 | 状态
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
## /EasyPan/设置(管理员)/修改用户空间
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/admin/updateUserSpace

#### 请求方式
> POST

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
userId | - | String | 是 | 用户ID
changeSpace | - | String | 是 | 改变的空间 单位MB
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
## /EasyPan/设置(管理员)/获取所有文件
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/admin/loadFileList

#### 请求方式
> POST

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
pageNo | - | String | 是 | 页码
pageSize | - | String | 是 | -
fileNameFuzzy | - | String | 否 | 文件名模糊搜索
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
## /EasyPan/设置(管理员)/获取当前目录信息
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/admin/getFolderInfo

#### 请求方式
> POST

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
path | - | String | 是 | -
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
## /EasyPan/设置(管理员)/获取文件信息
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/admin/getFile/{userId}/{fileId}

#### 请求方式
> GET

#### Content-Type
> form-data

#### 路径变量
参数名 | 示例值 | 参数描述
--- | --- | ---
userId | - | -
fileId | - | -
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
## /EasyPan/设置(管理员)/创建下载链接
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/admin/createDownloadUrl/{userId}/{fileId}

#### 请求方式
> POST

#### Content-Type
> form-data

#### 路径变量
参数名 | 示例值 | 参数描述
--- | --- | ---
userId | - | -
fileId | - | -
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
## /EasyPan/设置(管理员)/下载文件
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/admin/download

#### 请求方式
> GET

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
code | - | String | 是 | 创建下载链接返回的code
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
## /EasyPan/设置(管理员)/删除文件
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/admin/delFile

#### 请求方式
> POST

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
fileIdAndUserIds | xxx_dddd,xxxxxx_sdwe | String | 是 | 文件和用户ID用_隔开，多个参数用 逗号隔开
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
## /EasyPan/外部分享
```text
暂无描述
```
#### Header参数
参数名 | 示例值 | 参数描述
--- | --- | ---
暂无参数
#### Query参数
参数名 | 示例值 | 参数描述
--- | --- | ---
暂无参数
#### Body参数
参数名 | 示例值 | 参数描述
--- | --- | ---
暂无参数
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
## /EasyPan/外部分享/获取用户登录信息
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/showShare/getShareLoginInfo

#### 请求方式
> POST

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
shareId | Ve5IXOuqxL4hw1NkCR9U | String | 是 | 分享id
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"status": "success",
	"code": 200,
	"info": "请求成功",
	"data": {
		"shareTime": "2023-04-12 18:37:44",
		"expireTime": "2023-04-13 18:37:44",
		"nickName": "程序员老罗1",
		"fileName": "xx212222.jpg",
		"currentUser": true,
		"fileId": "FBXdDvhLHg",
		"avatar": null,
		"userId": "102522"
	}
}
```
## /EasyPan/外部分享/获取分享信息
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/showShare/getShareInfo

#### 请求方式
> POST

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
shareId | AfIMnEObgtFigLhaoCjk | String | 是 | -
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"status": "success",
	"code": 200,
	"info": "请求成功",
	"data": {
		"shareTime": "2023-04-12 18:35:43",
		"expireTime": "2023-04-13 18:35:43",
		"nickName": "程序员老罗1",
		"fileName": "23232323",
		"currentUser": null,
		"fileId": "lYf7edaqUi",
		"avatar": null,
		"userId": "102522"
	}
}
```
## /EasyPan/外部分享/校验分享码
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/showShare/checkShareCode

#### 请求方式
> POST

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
shareId | Ve5IXOuqxL4hw1NkCR9U | String | 是 | 分享id
code | GEm25 | String | 是 | 分享码
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"status": "success",
	"code": 200,
	"info": "请求成功",
	"data": null
}
```
## /EasyPan/外部分享/获取文件列表
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/showShare/loadFileList

#### 请求方式
> POST

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
shareId | Ve5IXOuqxL4hw1NkCR9U | String | 是 | 分享id
filePid | 父级ID | String | 是 | -
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"status": "success",
	"code": 200,
	"info": "请求成功",
	"data": {
		"totalCount": 1,
		"pageSize": 15,
		"pageNo": 1,
		"pageTotal": 1,
		"list": [
			{
				"fileId": "FBXdDvhLHg",
				"filePid": "0",
				"fileSize": 15175,
				"fileName": "xx212222.jpg",
				"fileCover": "202304/102522FBXdDvhLHg_.jpg",
				"lastUpdateTime": "2023-04-12 18:40:33",
				"folderType": 0,
				"fileCategory": 3,
				"fileType": 3,
				"status": 2
			}
		]
	}
}
```
## /EasyPan/外部分享/获取目录信息
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/showShare/getFolderInfo

#### 请求方式
> POST

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
shareId | - | String | 是 | 分享id
path | - | String | 是 | 完整路径
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
## /EasyPan/外部分享/获取文件信息
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/showShare/getFile

#### 请求方式
> GET

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
shareId | Ve5IXOuqxL4hw1NkCR9U | String | 是 | 分享id
fileId | FBXdDvhLHg | String | 是 | 文件ID
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
## /EasyPan/外部分享/创建下载链接
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/showShare/createDownloadUrl

#### 请求方式
> GET

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
shareId | Ve5IXOuqxL4hw1NkCR9U | String | 是 | 分享id
fileId | FBXdDvhLHg | String | 是 | 文件ID
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"status": "success",
	"code": 200,
	"info": "请求成功",
	"data": "xzFp0F9n44SxZirGChgA0RwgtVf9rm5r26LiVuTxpCeouVn2Dg"
}
```
## /EasyPan/外部分享/下载文件
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/showShare/download

#### 请求方式
> GET

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
code | - | String | 是 | 下载链接返回的code
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
## /EasyPan/外部分享/保存到我的网盘
```text
暂无描述
```
#### 接口状态
> 开发中

#### 接口URL
> http://localhost:7090/api/showShare/saveShare

#### 请求方式
> POST

#### Content-Type
> form-data

#### 请求Body参数
参数名 | 示例值 | 参数类型 | 是否必填 | 参数描述
--- | --- | --- | --- | ---
shareId | Ve5IXOuqxL4hw1NkCR9U | String | 是 | 分享id
shareFileIds | 111,qqq | String | 是 | 分享的文件ID,多个逗号隔开
myFolderId | wqwqwqw | String | 是 | 我的网盘目录ID
#### 认证方式
```text
noauth
```
#### 预执行脚本
```javascript
暂无预执行脚本
```
#### 后执行脚本
```javascript
暂无后执行脚本
```
#### 成功响应示例
```javascript
{
	"status": "success",
	"code": 200,
	"info": "请求成功",
	"data": null
}
```