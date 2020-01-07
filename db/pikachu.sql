/*
 Navicat Premium Data Transfer

 Source Server         : mine
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 180.76.142.30:3306
 Source Schema         : pikachu

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 07/01/2020 23:38:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pikachu_purchase_order
-- ----------------------------
DROP TABLE IF EXISTS `pikachu_purchase_order`;
CREATE TABLE `pikachu_purchase_order`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_id` bigint(20) NOT NULL COMMENT '创建人(id)',
  `created_at` datetime(0) NOT NULL COMMENT '创建时间',
  `updated_at` datetime(0) NOT NULL COMMENT '更新时间',
  `purchase_order_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '采购单Code',
  `contract_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合同Code',
  `buyer_id` bigint(20) NOT NULL COMMENT '买家(id)',
  `buyer_note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '买家留言',
  `status` int(2) NOT NULL COMMENT '状态',
  `type` int(2) NOT NULL COMMENT '类型',
  `category_id` bigint(20) NOT NULL COMMENT '类目',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pikachu_purchase_order_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `pikachu_purchase_order_operation_log`;
CREATE TABLE `pikachu_purchase_order_operation_log`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_id` bigint(20) NOT NULL COMMENT '创建人(id)',
  `created_at` datetime(0) NOT NULL COMMENT '创建时间',
  `updated_at` datetime(0) NOT NULL COMMENT '更新时间',
  `purchase_order_id` bigint(20) NOT NULL COMMENT '采购单(id)',
  `operation_type` smallint(2) NOT NULL COMMENT '操作类型',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pikachu_purchase_sku_order
-- ----------------------------
DROP TABLE IF EXISTS `pikachu_purchase_sku_order`;
CREATE TABLE `pikachu_purchase_sku_order`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `create_id` bigint(20) NOT NULL COMMENT '创建人(id)',
  `created_at` datetime(0) NOT NULL COMMENT '创建时间',
  `updated_at` datetime(0) NOT NULL COMMENT '更新时间',
  `purchase_order_id` bigint(20) NOT NULL COMMENT '采购单(id)',
  `item_id` bigint(20) NOT NULL COMMENT '商品(id)',
  `buyer_id` bigint(20) NOT NULL COMMENT '买家(id)',
  `buyer_note` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '买家留言',
  `status` int(2) NOT NULL COMMENT '状态',
  `quantity` int(11) NOT NULL COMMENT '数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for pikachu_trade_mq_response
-- ----------------------------
DROP TABLE IF EXISTS `pikachu_trade_mq_response`;
CREATE TABLE `pikachu_trade_mq_response`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `create_id` bigint(20) NOT NULL,
  `created_at` datetime(0) NOT NULL,
  `updated_at` datetime(0) NOT NULL,
  `message_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `exchange` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `routing_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` smallint(2) NOT NULL,
  `retry_count` smallint(1) NOT NULL,
  `next_retry` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
