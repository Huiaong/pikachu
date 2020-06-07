# Pikachu

Pikachu，是宠物小精灵商城的后台系统，整个后端项目采用分布式微服务架构进行开发，采用了SpringBoot、MyBatis、Dubbo、Redis、Rabbit MQ等核心技术。对于初学分布式微服务的小伙伴来说，是个不错的例子

[![License](https://img.shields.io/badge/SpringBoot-v2.1.8.RELEASE-green.svg)](https://github.com/Huiaong/pikachu)
[![License](https://img.shields.io/badge/Mysql-v5.6.47-blue.svg)](https://github.com/Huiaong/pikachu)
[![License](https://img.shields.io/badge/dubbo-green.svg)](https://github.com/Huiaong/pikachu)
[![License](https://img.shields.io/badge/redis-4.0.14-red.svg)](https://github.com/Huiaong/pikachu)
[![License](https://img.shields.io/badge/rabbitMQ-3.5.6-orange.svg)](https://github.com/Huiaong/pikachu)
[![License](https://img.shields.io/badge/erlang-18.1-9cf.svg)](https://github.com/Huiaong/pikachu)


如果大家喜欢、或是对大家的学习有所帮助，请点击右上角star、fork 给作者一些鼓励。

## 写在前面

学习了此项目，能带给你：

1. 了解基于Dubbo的分布式微服务项目的基本架构
2. 学习在基于Redis缓存的情况下如何处理雪崩，击穿，穿透等突发情况
3. 清楚一个分布式微服务电商商城的开发流程和设计思路
4. 总而言之，这是一个不错的分布式微服务项目示例，通俗易懂的代码设计和业务流程，带给你更愉快的开发体验。

## 运行前

运行前请先确认您的本地环境是否安装了以下：


| 应用      | 版本   |
| --------- | ------ |
| JDK       | 1.8    |
| MySQL     | 5.7 |
| Redis     | 6.0 |
| Nacos(注册中心) | 3.4.5  |
| Erlang    | 22.3.4 |
| Rabbit MQ | 3.8.3 |

运行环境搭建好之后，复制`application.yml.bak`重命名为`application.yml`并修改配置文件

## 运行

此仓库实际包含多个子模块：

- `Pikachu-admin` - 后台API接口
- `Pikachu-web` - 商城API接口
- `Pikachu-web-common` - 商城与后台通用接口
- `Pikachu-crontab` - 定时任务
- `Pikachu-receiver` - 消费者
- `Pikachu-article` - 文章服务
- `Pikachu-article-api` - 文章服务API
- `Pikachu-item` - 商品服务
- `Pikachu-item-api` - 商品服务API
- `Pikachu-log` - 日志服务
- `Pikachu-log-api` - 日志服务API
- `Pikachu-user` - 用户服务
- `Pikachu-user-api` - 用户服务API
- `Pikachu-trade` - 交易服务
- `Pikachu-trade-api` - 交易服务API
- `Pikachu-common` - 通用模块

请先运行这几个模块：

- `Pikachu-trade` - 运行`PikachuTradeApplication.java`的main方法即可
- `Pikachu-log` - 运行`PikachuLogApplication.java`的main方法即可
- `Pikachu-item` - 运行`PikachuItemApplication.java`的main方法即可
- `Pikachu-article` - 运行`PikachuArticleApplication.java`的main方法即可
- `Pikachu-user` - 运行`PikachuUserApplication.java`的main方法即可
- `Pikachu-resources` - 运行`PikachuResourcesApplication.java`的main方法即可

接下来可以选择运行这几个模块之一或全部：

- `Pikachu-receiver` - 运行`PikachuReceiverApplication.java`的main方法即可
- `Pikachu-admin` - 运行`PikachuAdminApplication.java`的main方法即可
- `Pikachu-crontab` - 运行`PikachuCrontabApplication.java`的main方法即可
- `Pikachu-web` - 运行`PikachuWebApplication.java`的main方法即可


