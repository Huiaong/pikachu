/*
 Navicat Premium Data Transfer

 Source Server         : mine
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : 180.76.142.30:3306
 Source Schema         : pikachu

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 09/06/2020 22:01:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pikachu_address
-- ----------------------------
DROP TABLE IF EXISTS `pikachu_address`;
CREATE TABLE `pikachu_address` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `create_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人(id)',
  `created_at` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updated_id` bigint(20) DEFAULT NULL COMMENT '更新人(id)',
  `updated_at` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` smallint(1) NOT NULL DEFAULT '0' COMMENT '删除标识',
  `pid` bigint(20) DEFAULT NULL COMMENT '父级ID',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `level` int(11) DEFAULT NULL COMMENT '级别',
  `pinyin` varchar(100) DEFAULT NULL COMMENT '拼音',
  `english_name` varchar(100) DEFAULT NULL COMMENT '英文名',
  `unicode_code` varchar(200) DEFAULT NULL COMMENT 'ASCII码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=659004503 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for pikachu_article
-- ----------------------------
DROP TABLE IF EXISTS `pikachu_article`;
CREATE TABLE `pikachu_article` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_id` bigint(20) NOT NULL COMMENT '创建人(id)',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_id` bigint(20) DEFAULT NULL COMMENT '更新人(id)',
  `updated_at` timestamp NOT NULL DEFAULT '1970-01-01 16:00:01' COMMENT '更新时间',
  `del_flag` smallint(1) NOT NULL DEFAULT '0' COMMENT '删除标识',
  `type` int(2) NOT NULL COMMENT '类型',
  `status` int(2) NOT NULL COMMENT '状态',
  `title` varchar(255) NOT NULL COMMENT '文章标题',
  `description` text NOT NULL COMMENT '文章描述',
  `content` text NOT NULL COMMENT '文章内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pikachu_category
-- ----------------------------
DROP TABLE IF EXISTS `pikachu_category`;
CREATE TABLE `pikachu_category` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `create_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建人(id)',
  `created_at` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updated_id` bigint(20) DEFAULT NULL COMMENT '更新人(id)',
  `updated_at` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` smallint(1) NOT NULL DEFAULT '0' COMMENT '删除标识',
  `name` varchar(255) NOT NULL COMMENT '类目名',
  `has_children` smallint(1) NOT NULL COMMENT '有下级类目',
  `status` smallint(2) NOT NULL COMMENT '类目状态',
  `parent_id` bigint(20) NOT NULL COMMENT '父类目ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for pikachu_goods
-- ----------------------------
DROP TABLE IF EXISTS `pikachu_goods`;
CREATE TABLE `pikachu_goods` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_id` bigint(20) NOT NULL COMMENT '创建人(id)',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_id` bigint(20) DEFAULT NULL COMMENT '更新人(id)',
  `updated_at` timestamp NOT NULL DEFAULT '1970-01-01 16:00:01' COMMENT '更新时间',
  `del_flag` smallint(1) NOT NULL DEFAULT '0' COMMENT '删除标识',
  `name` varchar(255) NOT NULL COMMENT '商品名',
  `category` int(2) NOT NULL COMMENT '类目',
  `status` int(2) NOT NULL COMMENT '状态',
  `price` bigint(20) NOT NULL COMMENT '价格',
  `discount` bigint(20) NOT NULL COMMENT '折扣',
  `price_type` int(2) NOT NULL COMMENT '折扣类型',
  `desc` varchar(255) NOT NULL COMMENT '商品简介',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=891 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pikachu_goods_kind
-- ----------------------------
DROP TABLE IF EXISTS `pikachu_goods_kind`;
CREATE TABLE `pikachu_goods_kind` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_id` bigint(20) NOT NULL COMMENT '创建人(id)',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_id` bigint(20) DEFAULT NULL COMMENT '更新人(id)',
  `updated_at` timestamp NOT NULL DEFAULT '1970-01-01 16:00:01' COMMENT '更新时间',
  `del_flag` smallint(1) NOT NULL DEFAULT '0' COMMENT '删除标识',
  `goods_id` bigint(20) unsigned NOT NULL COMMENT '商品id',
  `name` varchar(255) NOT NULL COMMENT '商品名',
  `nick_name` varchar(255) NOT NULL COMMENT '商品昵称',
  `img_path` varchar(255) NOT NULL COMMENT '图片地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1061 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pikachu_purchase_order
-- ----------------------------
DROP TABLE IF EXISTS `pikachu_purchase_order`;
CREATE TABLE `pikachu_purchase_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_id` bigint(20) NOT NULL COMMENT '创建人(id)',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_id` bigint(20) DEFAULT NULL COMMENT '更新人(id)',
  `updated_at` timestamp NOT NULL DEFAULT '1970-01-01 16:00:01' COMMENT '更新时间',
  `del_flag` smallint(1) NOT NULL DEFAULT '0' COMMENT '删除标识',
  `purchase_order_code` varchar(10) NOT NULL COMMENT '采购单Code',
  `contract_code` varchar(10) DEFAULT NULL COMMENT '合同Code',
  `buyer_id` bigint(20) NOT NULL COMMENT '买家(id)',
  `buyer_note` varchar(255) DEFAULT NULL COMMENT '买家留言',
  `status` int(2) NOT NULL COMMENT '状态',
  `type` int(2) NOT NULL COMMENT '类型',
  `category_id` bigint(20) NOT NULL COMMENT '类目',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for pikachu_purchase_order_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `pikachu_purchase_order_operation_log`;
CREATE TABLE `pikachu_purchase_order_operation_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_id` bigint(20) NOT NULL COMMENT '创建人(id)',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_id` bigint(20) DEFAULT NULL COMMENT '更新人(id)',
  `updated_at` timestamp NOT NULL DEFAULT '1970-01-01 16:00:01' COMMENT '更新时间',
  `del_flag` smallint(1) NOT NULL DEFAULT '0' COMMENT '删除标识',
  `purchase_order_id` bigint(20) NOT NULL COMMENT '采购单(id)',
  `operation_type` smallint(2) NOT NULL COMMENT '操作类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for pikachu_purchase_sku_order
-- ----------------------------
DROP TABLE IF EXISTS `pikachu_purchase_sku_order`;
CREATE TABLE `pikachu_purchase_sku_order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_id` bigint(20) NOT NULL COMMENT '创建人(id)',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_id` bigint(20) DEFAULT NULL COMMENT '更新人(id)',
  `updated_at` timestamp NOT NULL DEFAULT '1970-01-01 16:00:01' COMMENT '更新时间',
  `del_flag` smallint(1) NOT NULL DEFAULT '0' COMMENT '删除标识',
  `purchase_order_id` bigint(20) NOT NULL COMMENT '采购单(id)',
  `item_id` bigint(20) NOT NULL COMMENT '商品(id)',
  `buyer_id` bigint(20) NOT NULL COMMENT '买家(id)',
  `buyer_note` varchar(255) DEFAULT NULL COMMENT '买家留言',
  `status` int(2) NOT NULL COMMENT '状态',
  `quantity` int(11) NOT NULL COMMENT '数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for pikachu_role
-- ----------------------------
DROP TABLE IF EXISTS `pikachu_role`;
CREATE TABLE `pikachu_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_id` bigint(20) NOT NULL COMMENT '创建人(id)',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_id` bigint(20) DEFAULT NULL COMMENT '更新人(id)',
  `updated_at` timestamp NOT NULL DEFAULT '1970-01-01 16:00:01' COMMENT '更新时间',
  `del_flag` smallint(1) NOT NULL DEFAULT '0' COMMENT '删除标识',
  `name` varchar(20) NOT NULL COMMENT '权限名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pikachu_trade_mq_response
-- ----------------------------
DROP TABLE IF EXISTS `pikachu_trade_mq_response`;
CREATE TABLE `pikachu_trade_mq_response` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `create_id` bigint(20) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_id` bigint(20) DEFAULT NULL COMMENT '更新人(id)',
  `updated_at` timestamp NOT NULL DEFAULT '1970-01-01 16:00:01',
  `del_flag` smallint(1) NOT NULL DEFAULT '0' COMMENT '删除标识',
  `message_id` varchar(40) NOT NULL,
  `content` varchar(255) NOT NULL,
  `exchange` varchar(255) NOT NULL,
  `routing_key` varchar(255) DEFAULT NULL,
  `status` smallint(2) NOT NULL,
  `retry_count` smallint(1) NOT NULL,
  `next_retry` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for pikachu_user
-- ----------------------------
DROP TABLE IF EXISTS `pikachu_user`;
CREATE TABLE `pikachu_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_id` bigint(20) NOT NULL COMMENT '创建人(id)',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_id` bigint(20) DEFAULT NULL COMMENT '更新人(id)',
  `updated_at` timestamp NOT NULL DEFAULT '1970-01-01 16:00:01' COMMENT '更新时间',
  `del_flag` smallint(1) NOT NULL DEFAULT '0' COMMENT '删除标识',
  `name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(11) DEFAULT NULL COMMENT '移动电话',
  `password` varchar(255) NOT NULL COMMENT '密码（加密）',
  `type` smallint(2) NOT NULL COMMENT '类型',
  `status` smallint(2) NOT NULL COMMENT '状态',
  `portrait` varchar(255) DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pikachu_user_role
-- ----------------------------
DROP TABLE IF EXISTS `pikachu_user_role`;
CREATE TABLE `pikachu_user_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_id` bigint(20) NOT NULL COMMENT '创建人(id)',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_id` bigint(20) DEFAULT NULL COMMENT '更新人(id)',
  `updated_at` timestamp NOT NULL DEFAULT '1970-01-01 16:00:01' COMMENT '更新时间',
  `del_flag` smallint(1) NOT NULL DEFAULT '0' COMMENT '删除标识',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
