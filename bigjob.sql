-- phpMyAdmin SQL Dump
-- version 4.4.15.10
-- https://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: 2022-05-27 23:03:42
-- 服务器版本： 5.7.37-log
-- PHP Version: 5.6.40

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bigjob`
--

-- --------------------------------------------------------

--
-- 表的结构 `applications`
--

CREATE TABLE IF NOT EXISTS `applications` (
  `u_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `browses`
--

CREATE TABLE IF NOT EXISTS `browses` (
  `u_id` int(11) NOT NULL,
  `b_id` int(11) NOT NULL,
  `p_id` varchar(100) NOT NULL,
  `begintime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `endtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `during` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `browses`
--

INSERT INTO `browses` (`u_id`, `b_id`, `p_id`, `begintime`, `endtime`, `during`) VALUES
(3, 4, '3dfe2fa9-7e1d-4c6f-88f5-dd4082e073d4', '2022-05-27 13:36:37', '2022-05-27 13:36:38', 1),
(3, 4, '3dfe2fa9-7e1d-4c6f-88f5-dd4082e073d4', '2022-05-27 13:37:01', '2022-05-27 13:37:02', 1),
(3, 7, '26163aa4-8fc9-421c-9e09-16ad60d4fdce', '2022-05-27 03:48:42', '2022-05-27 03:48:44', 1),
(3, 7, '3adcc70b-259a-4777-8df7-28aea3c0f6dc', '2022-05-27 03:38:29', '2022-05-27 03:38:31', 2),
(3, 8, '1c4a90e7-bbc2-4ae5-90f2-ffcc6418a01a', '2022-05-27 03:36:22', '2022-05-27 03:38:06', 103),
(3, 10, '91f1d32e-022e-43ac-948b-336c5967c9bf', '2022-05-27 03:48:51', '2022-05-27 03:48:54', 3),
(5, 4, '1d4af238-b1cc-4303-b28b-caf812313438', '2022-05-27 13:39:07', '2022-05-27 13:39:08', 1),
(10, 2, 'e7860188-0025-4df1-97ca-4df95a2ab99f', '2022-05-27 01:26:47', '2022-05-27 01:26:53', 6);

-- --------------------------------------------------------

--
-- 表的结构 `loginout`
--

CREATE TABLE IF NOT EXISTS `loginout` (
  `u_id` int(11) NOT NULL,
  `logintime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `logouttime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `role` varchar(10) DEFAULT NULL,
  `ip` varchar(17) DEFAULT NULL,
  `province` varchar(12) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `loginout`
--

INSERT INTO `loginout` (`u_id`, `logintime`, `logouttime`, `role`, `ip`, `province`) VALUES
(1, '2022-05-26 13:27:15', '2022-05-26 13:28:50', 'admin', '221.4.34.187', '广东省'),
(1, '2022-05-26 15:49:31', '2022-05-26 15:56:19', 'admin', '221.4.34.187', '广东省'),
(1, '2022-05-26 15:57:33', '2022-05-26 16:11:54', 'admin', '221.4.34.187', '广东省'),
(1, '2022-05-26 16:12:25', '2022-05-26 16:18:07', 'admin', '221.4.34.187', '广东省'),
(1, '2022-05-26 19:46:24', '2022-05-26 20:00:18', 'admin', '221.4.34.187', '广东省'),
(1, '2022-05-26 20:31:55', '2022-05-26 20:37:09', 'admin', '221.4.34.187', '广东省'),
(1, '2022-05-27 00:47:37', '2022-05-27 00:53:35', 'admin', '221.4.34.187', '广东省'),
(1, '2022-05-27 01:04:30', '2022-05-27 01:22:14', 'admin', '221.4.34.187', '广东省'),
(2, '2022-05-23 20:29:29', '2022-05-23 20:43:35', 'business', '116.56.144.73', '广东省'),
(2, '2022-05-23 20:45:27', '2022-05-23 20:45:56', 'business', '116.56.144.73', '广东省'),
(2, '2022-05-25 09:02:03', '2022-05-25 09:02:15', 'business', '221.4.34.187', ''),
(2, '2022-05-25 13:24:19', '2022-05-25 13:27:18', 'business', '221.4.34.187', ''),
(2, '2022-05-25 20:22:07', '2022-05-25 20:35:56', 'business', '221.4.34.187', '广东省'),
(2, '2022-05-26 13:13:24', '2022-05-26 13:23:32', 'business', '221.4.34.187', ''),
(2, '2022-05-26 15:10:37', '2022-05-26 15:10:53', 'business', '221.4.34.187', '广东省'),
(2, '2022-05-26 20:00:52', '2022-05-26 20:03:26', 'business', '221.4.34.187', ''),
(2, '2022-05-27 01:27:56', '2022-05-27 01:30:00', 'business', '221.4.34.187', '广东省'),
(3, '2022-05-26 15:32:21', '2022-05-26 15:49:17', 'user', '221.4.34.187', ''),
(3, '2022-05-26 20:00:22', '2022-05-26 20:00:27', 'user', '221.4.34.187', '广东省'),
(3, '2022-05-27 01:22:49', '2022-05-27 01:23:55', 'user', '221.4.34.187', '广东省'),
(3, '2022-05-27 01:36:26', '2022-05-27 01:37:36', 'user', '221.4.34.187', '广东省'),
(3, '2022-05-27 02:38:48', '2022-05-27 02:38:56', 'user', '221.4.34.187', '广东省'),
(4, '2022-05-26 13:07:42', '2022-05-26 13:12:34', 'business', '221.4.34.187', '未知'),
(4, '2022-05-27 01:30:16', '2022-05-27 01:31:02', 'business', '221.4.34.187', '广东省'),
(4, '2022-05-27 01:37:50', '2022-05-27 01:38:52', 'business', '221.4.34.187', '广东省'),
(4, '2022-05-27 01:39:43', '2022-05-27 01:47:56', 'business', '221.4.34.187', '广东省'),
(4, '2022-05-27 01:51:34', '2022-05-27 02:08:12', 'business', '221.4.34.187', '广东省'),
(4, '2022-05-27 02:37:23', '2022-05-27 02:38:07', 'business', '221.4.34.187', '广东省'),
(5, '2022-05-26 19:19:18', '2022-05-26 19:24:43', 'user', '221.4.34.187', '广东省'),
(5, '2022-05-27 01:39:00', '2022-05-27 01:39:30', 'user', '221.4.34.187', '广东省'),
(7, '2022-05-26 13:12:50', '2022-05-26 13:13:10', 'business', '221.4.34.187', '广东省'),
(7, '2022-05-26 19:43:02', '2022-05-26 19:46:14', 'user', '221.4.34.187', '未知'),
(7, '2022-05-26 20:37:26', '2022-05-26 20:53:14', 'business', '221.4.34.187', '广东省'),
(7, '2022-05-27 01:31:19', '2022-05-27 01:32:18', 'business', '221.4.34.187', '广东省'),
(8, '2022-05-26 13:24:03', '2022-05-26 13:24:21', 'user', '221.4.34.187', '未知'),
(8, '2022-05-26 14:45:08', '2022-05-26 15:10:23', 'business', '221.4.34.187', '广东省'),
(8, '2022-05-27 01:32:32', '2022-05-27 01:33:45', 'business', '221.4.34.187', '广东省'),
(9, '2022-05-26 13:25:30', '2022-05-26 13:25:40', 'user', '221.4.34.187', '未知'),
(9, '2022-05-26 15:11:10', '2022-05-26 15:22:43', 'business', '221.4.34.187', '广东省'),
(9, '2022-05-27 01:34:18', '2022-05-27 01:35:11', 'business', '221.4.34.187', '广东省'),
(10, '2022-05-26 13:26:06', '2022-05-26 13:27:06', 'user', '221.4.34.187', '未知'),
(10, '2022-05-26 15:23:04', '2022-05-26 15:31:34', 'business', '221.4.34.187', '广东省'),
(10, '2022-05-27 01:35:26', '2022-05-27 01:36:17', 'business', '221.4.34.187', '广东省');

-- --------------------------------------------------------

--
-- 表的结构 `operations`
--

CREATE TABLE IF NOT EXISTS `operations` (
  `u_id` int(11) DEFAULT NULL,
  `role` varchar(10) DEFAULT NULL,
  `op_name` varchar(14) DEFAULT NULL,
  `op_content` varchar(100) DEFAULT NULL,
  `ip` varchar(17) DEFAULT NULL,
  `op_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `operations`
--

INSERT INTO `operations` (`u_id`, `role`, `op_name`, `op_content`, `ip`, `op_time`) VALUES
(2, 'business', '添加商品', 'ac8adffd-29e9-4a33-a56e-9916af2a6a7a', '116.56.144.73', '2022-05-23 20:31:48'),
(2, 'business', '添加商品', '28dcdd7e-ad96-495f-9f68-2dd9429b1289', '116.56.144.73', '2022-05-23 20:34:33'),
(2, 'business', '查询商品', '编号,名字含'',类别为,单价不低于40,单价不高于60', '116.56.144.73', '2022-05-23 20:41:19'),
(2, 'business', '添加商品', 'f44dac6f-7443-42fc-92a3-81a4115852d5', '221.4.34.187', '2022-05-25 20:26:14'),
(2, 'business', '添加商品', '53a481cb-755e-402a-8400-0eb81b20de78', '221.4.34.187', '2022-05-25 20:29:21'),
(2, 'business', '修改商品', '', '221.4.34.187', '2022-05-25 20:34:12'),
(2, 'business', '修改商品', '', '221.4.34.187', '2022-05-25 20:35:47'),
(2, 'business', '修改商品', 'e7860188-0025-4df1-97ca-4df95a2ab99f', '221.4.34.187', '2022-05-26 01:26:01'),
(2, 'business', '添加商品', '1a1c853a-a920-45b0-9d7e-aedfedf78ef6', '221.4.34.187', '2022-05-26 08:02:38'),
(2, 'business', '删除商品', '1a1c853a-a920-45b0-9d7e-aedfedf78ef6', '221.4.34.187', '2022-05-26 08:03:19'),
(1, 'admin', '添加销售', '7', '221.4.34.187', '2022-05-26 19:49:07'),
(1, 'admin', '查询销售', '销售人员名单', '221.4.34.187', '2022-05-26 19:54:30'),
(1, 'admin', '查询销售', '销售人员名单', '221.4.34.187', '2022-05-26 19:56:03'),
(1, 'admin', '查询销售', '销售人员名单', '221.4.34.187', '2022-05-26 19:56:39'),
(7, 'business', '添加商品', 'c5a3d9a7-09aa-4fb8-a6f7-bac43a107988', '221.4.34.187', '2022-05-26 20:39:47'),
(7, 'business', '添加商品', '37e10024-4339-4688-a52b-4744efcc1801', '221.4.34.187', '2022-05-26 20:41:36'),
(7, 'business', '添加商品', '73f12c3d-d0a6-4cfb-bccb-169ce48c85be', '221.4.34.187', '2022-05-26 20:44:06'),
(7, 'business', '添加商品', '26163aa4-8fc9-421c-9e09-16ad60d4fdce', '221.4.34.187', '2022-05-26 20:48:24'),
(7, 'business', '添加商品', '4d843293-ca53-4156-ab9c-f9c09f468c4b', '221.4.34.187', '2022-05-26 20:50:55'),
(7, 'business', '添加商品', '3adcc70b-259a-4777-8df7-28aea3c0f6dc', '221.4.34.187', '2022-05-26 20:53:01'),
(4, 'business', '添加商品', '1d4af238-b1cc-4303-b28b-caf812313438', '221.4.34.187', '2022-05-27 01:09:13'),
(4, 'business', '添加商品', '40ab8ee3-f55d-4789-83e0-e8e3e7165609', '221.4.34.187', '2022-05-27 01:12:13'),
(2, 'business', '添加商品', 'a9675b89-4e6d-4159-9557-564e2ec31f1e', '221.4.34.187', '2022-05-27 01:15:51'),
(2, 'business', '添加商品', 'eac0c00d-f8a6-400d-bb48-17da545f9cc6', '221.4.34.187', '2022-05-27 01:17:34'),
(2, 'business', '添加商品', '1ba4a4cd-ccb2-4ffa-994d-97a43a31e214', '221.4.34.187', '2022-05-27 01:19:34'),
(2, 'business', '添加商品', 'c7b54c42-ee28-4f47-977f-91abd57d77dd', '221.4.34.187', '2022-05-27 01:21:46'),
(2, 'business', '添加商品', '6a7a1a3f-9934-4c28-8d18-e1df4910705f', '221.4.34.187', '2022-05-27 01:23:28'),
(1, 'admin', '添加销售', '8', '221.4.34.187', '2022-05-27 01:27:35'),
(1, 'admin', '添加销售', '9', '221.4.34.187', '2022-05-27 01:28:06'),
(1, 'admin', '添加销售', '10', '221.4.34.187', '2022-05-27 01:28:23'),
(1, 'admin', '查询销售', '销售人员名单', '221.4.34.187', '2022-05-27 01:28:42'),
(8, 'business', '添加商品', 'bf6833bc-3dd2-4f4d-842a-a86ec0f9ee5d', '221.4.34.187', '2022-05-27 02:46:33'),
(8, 'business', '添加商品', '99f66a12-c7d3-408f-aea8-030dd524fdbd', '221.4.34.187', '2022-05-27 02:50:02'),
(8, 'business', '添加商品', '3b8adbb9-2b19-4e5d-88a6-835c2f728537', '221.4.34.187', '2022-05-27 02:57:15'),
(8, 'business', '添加商品', '1c4a90e7-bbc2-4ae5-90f2-ffcc6418a01a', '221.4.34.187', '2022-05-27 03:04:36'),
(8, 'business', '添加商品', '04e4c409-d8d8-455b-a99a-f80f5cfa2067', '221.4.34.187', '2022-05-27 03:05:54'),
(8, 'business', '添加商品', '363d0999-1753-4bc7-93c1-b44bcb79dbf8', '221.4.34.187', '2022-05-27 03:10:20'),
(2, 'business', '修改商品', 'e7860188-0025-4df1-97ca-4df95a2ab99f', '221.4.34.187', '2022-05-27 03:10:49'),
(9, 'business', '添加商品', 'ab1bcb73-84bb-4906-a6d0-d888b2459249', '221.4.34.187', '2022-05-27 03:13:15'),
(9, 'business', '添加商品', 'dcfb0cec-f040-41b6-9229-12d67c8f4da3', '221.4.34.187', '2022-05-27 03:14:26'),
(9, 'business', '添加商品', '40a12f0e-e45e-4275-b962-12b053152610', '221.4.34.187', '2022-05-27 03:17:23'),
(9, 'business', '添加商品', 'd22aec98-3f11-4553-92e4-b698745f293c', '221.4.34.187', '2022-05-27 03:20:50'),
(9, 'business', '添加商品', '06bc8db3-ee31-4b13-818b-8d9343d3fe9c', '221.4.34.187', '2022-05-27 03:22:38'),
(10, 'business', '添加商品', 'b0db4b35-396f-43d7-9b22-57bb533de60c', '221.4.34.187', '2022-05-27 03:26:06'),
(10, 'business', '添加商品', '91f1d32e-022e-43ac-948b-336c5967c9bf', '221.4.34.187', '2022-05-27 03:27:18'),
(10, 'business', '添加商品', '8186a4eb-0d34-4453-bb95-2229cf9a7ba9', '221.4.34.187', '2022-05-27 03:28:27'),
(10, 'business', '添加商品', '656acf9f-bb6e-4f04-9956-b08359a1ad94', '221.4.34.187', '2022-05-27 03:29:50'),
(10, 'business', '添加商品', '09b06556-e2c6-4cc3-b0f6-17c658c63653', '221.4.34.187', '2022-05-27 03:31:30'),
(1, 'admin', '查询日志', '2022年05月操作日志', '221.4.34.187', '2022-05-27 04:03:16'),
(1, 'admin', '查询日志', '2022年05月登入登出日志', '221.4.34.187', '2022-05-27 04:12:28'),
(1, 'admin', '查询日志', '2022年05月浏览日志', '221.4.34.187', '2022-05-27 04:13:21'),
(1, 'admin', '查询用户画像', '', '221.4.34.187', '2022-05-27 04:15:11'),
(1, 'admin', '下载用户画像', '', '221.4.34.187', '2022-05-27 04:15:17'),
(1, 'admin', '查询用户画像', '', '221.4.34.187', '2022-05-27 04:18:03'),
(1, 'admin', '查询用户画像', '', 'local', '2022-05-27 12:25:03'),
(1, 'admin', '下载用户画像', '', 'local', '2022-05-27 12:26:43'),
(1, 'admin', '查询用户画像', '', '221.4.34.187', '2022-05-27 12:47:39'),
(1, 'admin', '查询销售', '销售人员名单', '221.4.34.187', '2022-05-27 12:47:45'),
(1, 'admin', '查询用户画像', '', '221.4.34.187', '2022-05-27 12:48:06'),
(1, 'admin', '查询日志', '2022年05月操作日志', '221.4.34.187', '2022-05-27 13:04:40'),
(1, 'admin', '下载日志', '2022年05月操作日志', '221.4.34.187', '2022-05-27 13:06:09'),
(1, 'admin', '查询日志', '2022年05月操作日志', '221.4.34.187', '2022-05-27 13:06:11'),
(1, 'admin', '查询日志', '2022年05月登入登出日志', '221.4.34.187', '2022-05-27 13:17:13'),
(1, 'admin', '查询日志', '2022年05月浏览日志', '221.4.34.187', '2022-05-27 13:18:20'),
(1, 'admin', '查询用户画像', '', '221.4.34.187', '2022-05-27 13:19:46'),
(2, 'business', '查询商品', '', '221.4.34.187', '2022-05-27 13:27:59'),
(2, 'business', '修改商品', '1ba4a4cd-ccb2-4ffa-994d-97a43a31e214', '221.4.34.187', '2022-05-27 13:28:14'),
(2, 'business', '修改商品', '53069e80-814c-4efe-a0a6-9ed540c26bbe', '221.4.34.187', '2022-05-27 13:28:24'),
(2, 'business', '修改商品', '53a481cb-755e-402a-8400-0eb81b20de78', '221.4.34.187', '2022-05-27 13:28:30'),
(2, 'business', '修改商品', '6a7a1a3f-9934-4c28-8d18-e1df4910705f', '221.4.34.187', '2022-05-27 13:28:37'),
(2, 'business', '修改商品', 'a9675b89-4e6d-4159-9557-564e2ec31f1e', '221.4.34.187', '2022-05-27 13:28:44'),
(2, 'business', '修改商品', 'c7b54c42-ee28-4f47-977f-91abd57d77dd', '221.4.34.187', '2022-05-27 13:28:58'),
(2, 'business', '修改商品', 'e7860188-0025-4df1-97ca-4df95a2ab99f', '221.4.34.187', '2022-05-27 13:29:06'),
(2, 'business', '修改商品', 'eac0c00d-f8a6-400d-bb48-17da545f9cc6', '221.4.34.187', '2022-05-27 13:29:18'),
(2, 'business', '修改商品', 'ecc97a2b-6fc4-44f5-a511-c358c60904e8', '221.4.34.187', '2022-05-27 13:29:26'),
(2, 'business', '修改商品', 'f44dac6f-7443-42fc-92a3-81a4115852d5', '221.4.34.187', '2022-05-27 13:29:45'),
(4, 'business', '查询商品', '', '221.4.34.187', '2022-05-27 13:30:19'),
(4, 'business', '修改商品', '1d4af238-b1cc-4303-b28b-caf812313438', '221.4.34.187', '2022-05-27 13:30:26'),
(4, 'business', '修改商品', '3158bed3-ea45-4f07-818a-d4ff3e4e7281', '221.4.34.187', '2022-05-27 13:30:38'),
(4, 'business', '修改商品', '3dfe2fa9-7e1d-4c6f-88f5-dd4082e073d4', '221.4.34.187', '2022-05-27 13:30:49'),
(4, 'business', '修改商品', '40ab8ee3-f55d-4789-83e0-e8e3e7165609', '221.4.34.187', '2022-05-27 13:31:00'),
(7, 'business', '查询商品', '', '221.4.34.187', '2022-05-27 13:31:22'),
(7, 'business', '修改商品', '26163aa4-8fc9-421c-9e09-16ad60d4fdce', '221.4.34.187', '2022-05-27 13:31:32'),
(7, 'business', '修改商品', '37e10024-4339-4688-a52b-4744efcc1801', '221.4.34.187', '2022-05-27 13:31:43'),
(7, 'business', '修改商品', '3adcc70b-259a-4777-8df7-28aea3c0f6dc', '221.4.34.187', '2022-05-27 13:31:55'),
(7, 'business', '修改商品', '4d843293-ca53-4156-ab9c-f9c09f468c4b', '221.4.34.187', '2022-05-27 13:32:01'),
(7, 'business', '修改商品', '73f12c3d-d0a6-4cfb-bccb-169ce48c85be', '221.4.34.187', '2022-05-27 13:32:09'),
(7, 'business', '修改商品', 'c5a3d9a7-09aa-4fb8-a6f7-bac43a107988', '221.4.34.187', '2022-05-27 13:32:16'),
(8, 'business', '查询商品', '', '221.4.34.187', '2022-05-27 13:32:34'),
(8, 'business', '修改商品', '04e4c409-d8d8-455b-a99a-f80f5cfa2067', '221.4.34.187', '2022-05-27 13:32:42'),
(8, 'business', '修改商品', '1c4a90e7-bbc2-4ae5-90f2-ffcc6418a01a', '221.4.34.187', '2022-05-27 13:32:58'),
(8, 'business', '修改商品', '363d0999-1753-4bc7-93c1-b44bcb79dbf8', '221.4.34.187', '2022-05-27 13:33:08'),
(8, 'business', '修改商品', '3b8adbb9-2b19-4e5d-88a6-835c2f728537', '221.4.34.187', '2022-05-27 13:33:24'),
(8, 'business', '修改商品', '99f66a12-c7d3-408f-aea8-030dd524fdbd', '221.4.34.187', '2022-05-27 13:33:33'),
(8, 'business', '修改商品', 'bf6833bc-3dd2-4f4d-842a-a86ec0f9ee5d', '221.4.34.187', '2022-05-27 13:33:42'),
(9, 'business', '查询商品', '', '221.4.34.187', '2022-05-27 13:34:21'),
(9, 'business', '修改商品', '06bc8db3-ee31-4b13-818b-8d9343d3fe9c', '221.4.34.187', '2022-05-27 13:34:32'),
(9, 'business', '修改商品', '40a12f0e-e45e-4275-b962-12b053152610', '221.4.34.187', '2022-05-27 13:34:41'),
(9, 'business', '修改商品', 'ab1bcb73-84bb-4906-a6d0-d888b2459249', '221.4.34.187', '2022-05-27 13:34:49'),
(9, 'business', '修改商品', 'd22aec98-3f11-4553-92e4-b698745f293c', '221.4.34.187', '2022-05-27 13:34:58'),
(9, 'business', '修改商品', 'dcfb0cec-f040-41b6-9229-12d67c8f4da3', '221.4.34.187', '2022-05-27 13:35:07'),
(10, 'business', '查询商品', '', '221.4.34.187', '2022-05-27 13:35:28'),
(10, 'business', '修改商品', '09b06556-e2c6-4cc3-b0f6-17c658c63653', '221.4.34.187', '2022-05-27 13:35:34'),
(10, 'business', '修改商品', '656acf9f-bb6e-4f04-9956-b08359a1ad94', '221.4.34.187', '2022-05-27 13:35:45'),
(10, 'business', '修改商品', '8186a4eb-0d34-4453-bb95-2229cf9a7ba9', '221.4.34.187', '2022-05-27 13:35:53'),
(10, 'business', '修改商品', '91f1d32e-022e-43ac-948b-336c5967c9bf', '221.4.34.187', '2022-05-27 13:36:01'),
(10, 'business', '修改商品', 'b0db4b35-396f-43d7-9b22-57bb533de60c', '221.4.34.187', '2022-05-27 13:36:13');

-- --------------------------------------------------------

--
-- 表的结构 `orderitems`
--

CREATE TABLE IF NOT EXISTS `orderitems` (
  `order_id` varchar(100) NOT NULL,
  `product_id` varchar(100) NOT NULL,
  `buynum` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `orderitems`
--

INSERT INTO `orderitems` (`order_id`, `product_id`, `buynum`) VALUES
('17e318f3-f41c-4a5c-a286-6615aa0ab86e', '1d4af238-b1cc-4303-b28b-caf812313438', 2),
('46c57e41-f751-4aed-be68-bf1de5642b52', '3dfe2fa9-7e1d-4c6f-88f5-dd4082e073d4', 3),
('5cd52908-1ed7-4e64-b60c-f1440aac834d', '3dfe2fa9-7e1d-4c6f-88f5-dd4082e073d4', 5),
('720b632f-edc3-4a09-b75e-098203530e9f', '3dfe2fa9-7e1d-4c6f-88f5-dd4082e073d4', 3),
('c7ee5cc8-4838-4380-8a77-1f4635462dee', '1c4a90e7-bbc2-4ae5-90f2-ffcc6418a01a', 1),
('c7ee5cc8-4838-4380-8a77-1f4635462dee', '3adcc70b-259a-4777-8df7-28aea3c0f6dc', 3),
('cf3ed1ac-19c6-4c14-99a4-c7341cd40540', 'e7860188-0025-4df1-97ca-4df95a2ab99f', 1),
('d7c89e44-553a-484e-ad84-223fa5319441', '26163aa4-8fc9-421c-9e09-16ad60d4fdce', 2),
('d7c89e44-553a-484e-ad84-223fa5319441', '91f1d32e-022e-43ac-948b-336c5967c9bf', 1);

-- --------------------------------------------------------

--
-- 表的结构 `orders`
--

CREATE TABLE IF NOT EXISTS `orders` (
  `id` varchar(100) NOT NULL,
  `money` double DEFAULT NULL,
  `receiverAddress` varchar(255) DEFAULT NULL,
  `receiverName` varchar(20) DEFAULT NULL,
  `receiverEmail` varchar(20) DEFAULT NULL,
  `ordertime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `orders`
--

INSERT INTO `orders` (`id`, `money`, `receiverAddress`, `receiverName`, `receiverEmail`, `ordertime`, `user_id`) VALUES
('17e318f3-f41c-4a5c-a286-6615aa0ab86e', 146, '广东省广州市番禺区小谷围华南理工大学', 'yonghu2', '1186159971@qq.com', '2022-05-27 13:39:27', 5),
('46c57e41-f751-4aed-be68-bf1de5642b52', 120, '广东省佛山市南海区', 'yonghu2', '1186159971@qq.com', '2022-05-21 07:40:02', 5),
('5cd52908-1ed7-4e64-b60c-f1440aac834d', 200, '广东省广州市番禺区小谷围华南理工大学', 'yonghu', '1186159971@qq.com', '2022-05-27 13:37:28', 3),
('720b632f-edc3-4a09-b75e-098203530e9f', 120, '广东省佛山市南海区', 'yonghu2', '1186159971@qq.com', '2022-05-21 07:28:37', 5),
('c7ee5cc8-4838-4380-8a77-1f4635462dee', 106.5, '广东省广州市番禺区小谷围华南理工大学', 'yonghu', '1186159971@qq.com', '2022-05-27 03:44:45', 3),
('cf3ed1ac-19c6-4c14-99a4-c7341cd40540', 35, '广东省广州市番禺区小谷围派出所', 'yonghu3', '1186159971@qq.com', '2022-05-21 07:42:16', 6),
('d7c89e44-553a-484e-ad84-223fa5319441', 703, '广东省广州市番禺区小谷围华南理工大学', 'yonghu', '1186159971@qq.com', '2022-05-27 03:49:13', 3);

-- --------------------------------------------------------

--
-- 表的结构 `products`
--

CREATE TABLE IF NOT EXISTS `products` (
  `p_id` varchar(100) NOT NULL,
  `b_id` int(11) NOT NULL,
  `name` varchar(40) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `category` varchar(40) DEFAULT NULL,
  `pnum` int(10) unsigned DEFAULT NULL,
  `imgurl` varchar(100) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `products`
--

INSERT INTO `products` (`p_id`, `b_id`, `name`, `price`, `category`, `pnum`, `imgurl`, `description`) VALUES
('04e4c409-d8d8-455b-a99a-f80f5cfa2067', 8, '小煮锅', 39, '日用品', 800, '/productImg/1/4/f5c9c029-ea02-47fa-b926-db89b153c719.PNG', '最高800W，充分满足一般宿舍用电需求。一人锅，可蒸可炒，小巧玲珑。'),
('06bc8db3-ee31-4b13-818b-8d9343d3fe9c', 9, '荧光沙滩裤', 68, '服装', 200, '/productImg/5/11/d960c56a-4c02-41a7-bd57-c5db95a8565a.PNG', '穿上它，你就是街上最靓的仔！'),
('09b06556-e2c6-4cc3-b0f6-17c658c63653', 10, '无线蓝牙耳机', 180, '数码产品', 500, '/productImg/10/3/88d988f2-ebf3-460d-ab5f-b0adcf49e110.PNG', '充电仓兼有充电和收纳的功能，连接牢固，音质1k元以下无对手'),
('1ba4a4cd-ccb2-4ffa-994d-97a43a31e214', 2, '计算机408王道考研套装', 125, '书籍', 60, '/productImg/13/1/97e30f0a-36f5-48cc-a0ef-f2338e5bf90d.PNG', '知识点整理得非常清晰，每个知识点附带大量习题及详细的解答。不仅适合考研人群使用，也适合想在平时的课程期末考试上取得高分的朋友使用'),
('1c4a90e7-bbc2-4ae5-90f2-ffcc6418a01a', 8, '保温壶', 99, '日用品', 199, '/productImg/14/1/179672db-3ddc-4396-87dc-956f3d40d590.jpg', '304不锈钢内胆，持续保温10小时'),
('1d4af238-b1cc-4303-b28b-caf812313438', 4, '亚狮龙7号球', 73, '运动器材', 298, '/productImg/15/10/95db20ab-8a36-49c1-bdb2-cc14dc729eeb.PNG', '耐打，略轻，飞行比较稳定。推荐羽毛球爱好者使用'),
('26163aa4-8fc9-421c-9e09-16ad60d4fdce', 7, '小天使直尺', 2, '文具', 1998, '/productImg/2/5/6e34a253-4450-4c0f-a1ee-6f56639119e5.PNG', '15cm小巧玲珑，这款尺子不会轻易跟橡皮粘在一起，刻度清晰且难以磨损，推荐入手。'),
('3158bed3-ea45-4f07-818a-d4ff3e4e7281', 4, '小鬼斩', 700, '运动器材', 20, '/productImg/8/3/f19b1b25-20bb-4c02-aabe-58afa54c1377.jpg', '是鬼斩的简配版，涂装非常地炫酷，是一款进攻拍，配重为4U，打高远球非常的轻松'),
('363d0999-1753-4bc7-93c1-b44bcb79dbf8', 8, '酷毙灯', 39, '日用品', 1000, '/productImg/6/4/9aad50d7-8abb-40eb-bd0d-8f1619715d62.jpg', '充电款，配合磁吸使用，三档光照强度可调，节省空间的同时，给你最均匀的光照。'),
('37e10024-4339-4688-a52b-4744efcc1801', 7, '多彩荧光笔', 3, '文具', 600, '/productImg/6/12/2316e2cf-512b-460f-92e5-8d86ac7df6e6.jpg', '帮助你标记重难点，妈妈再也不用担心找不到重点啦！'),
('3adcc70b-259a-4777-8df7-28aea3c0f6dc', 7, '2B铅笔', 2.5, '文具', 497, '/productImg/11/14/098ff1f8-5595-44c9-a2db-9e2e88d95703.jpg', '大考必备2B铅笔，按键顺滑，握感舒适'),
('3b8adbb9-2b19-4e5d-88a6-835c2f728537', 8, '百雀羚面霜', 12, '日用品', 500, '/productImg/4/0/6905aee0-fdc2-43ae-8fff-44879ada9b74.PNG', '精品国货，物美价廉，给你的脸足够的滋润！'),
('3dfe2fa9-7e1d-4c6f-88f5-dd4082e073d4', 4, '川崎T75耐打球', 40, '运动器材', 238, '/productImg/12/15/342e7787-f7e4-4b0d-9ecb-6411056ce0bf.jpg', '一款入门级羽毛球，飞行稳定'),
('40a12f0e-e45e-4275-b962-12b053152610', 9, '洞洞鞋', 25, '服装', 200, '/productImg/2/7/04ac8265-63ac-44d8-b963-549481023731.PNG', '让你的脚趾透透气。南方雨天必备佳品'),
('40ab8ee3-f55d-4789-83e0-e8e3e7165609', 4, '泳帽', 15, '运动器材', 50, '/productImg/7/14/392fa37a-1501-474e-ba6f-c20c8202edbc.jpg', '结实耐用，防水效果好。配色简约，有黑、白两色可选。'),
('4d843293-ca53-4156-ab9c-f9c09f468c4b', 7, '全铝自动铅笔', 7, '文具', 300, '/productImg/3/9/16d5f153-fa62-4d1d-9507-c74cf5355e9e.PNG', '全铝笔身，手感十足。搭配0.7mm的笔芯使用'),
('53069e80-814c-4efe-a0a6-9ed540c26bbe', 2, 'JavaWeb程序设计任务教程', 50, '书籍', 100, '/productImg/4/2/ddcac4a0-e909-452b-abce-dd93611e5af3.jpg', '内含一个完整的网上书城项目，随书附赠源代码。技术不是最新的，但也非常值得一看'),
('53a481cb-755e-402a-8400-0eb81b20de78', 2, '深度学习花书', 68, '书籍', 500, '/productImg/9/15/2b2cba8d-5de6-4670-9bb8-77ad9200d3a4.jpg', '深度学习入门圣经，超级厚，内含大量数学原理，建议配合网课食用'),
('656acf9f-bb6e-4f04-9956-b08359a1ad94', 10, '光影精灵5', 5699, '数码产品', 60, '/productImg/8/5/e6037340-1433-42eb-8554-d4ecfa330947.PNG', '有紫色、绿色两种灯效可选，是一台伪装成商务本的游戏本，功能强大，外表低调有内涵'),
('6a7a1a3f-9934-4c28-8d18-e1df4910705f', 2, 'Pytorch快速开发与实战', 45, '书籍', 300, '/productImg/10/6/4b7603eb-b9cb-4530-9ced-7633fc27aaf0.jpg', 'Pytorch是目前主流的深度学习框架之一，本书帮助初学者了解Pytorch。看完本书后，能独立写出些小demo来'),
('73f12c3d-d0a6-4cfb-bccb-169ce48c85be', 7, '按键啫喱笔', 10.5, '文具', 700, '/productImg/11/14/63d71968-3ce6-4ed8-ae70-057a4386c284.jpg', '纤细的按键笔，东西易掉到地上的人的福音。五种颜色一组，做笔记一流'),
('8186a4eb-0d34-4453-bb95-2229cf9a7ba9', 10, '运动手环', 109, '数码产品', 600, '/productImg/3/0/1c96a9b2-353f-4d67-a3b0-df6f3b27fc3e.PNG', '一次充电可用一个星期。带有测心率、时间、距离等多个功能，是分析运动能力的利器。'),
('91f1d32e-022e-43ac-948b-336c5967c9bf', 10, 'ipad触控笔', 699, '数码产品', 399, '/productImg/12/13/b01ba6c5-ccea-46a4-8380-2755b6e7601a.png', '有了ipad pencil，能更充分地利用你的ipad。给你不一样的书写感受'),
('99f66a12-c7d3-408f-aea8-030dd524fdbd', 8, 'S号抽纸', 3.5, '日用品', 10000, '/productImg/8/12/4e698694-aec3-4710-ad6e-f324282ef5c8.jpg', '小巧玲珑，可以在包中携带。触感柔软，呵护你的肌肤。'),
('a9675b89-4e6d-4159-9557-564e2ec31f1e', 2, '数学建模算法与应用第二版', 35, '书籍', 400, '/productImg/9/2/08b2c130-ceb7-4e56-91bc-6eca8f340d89.jpg', '涵盖内容比较全面，包括时间序列预测、数据拟合等，内含大量基本例子，并附带可运行的matlab/lingo程序，是入门实操数学建模的经典'),
('ab1bcb73-84bb-4906-a6d0-d888b2459249', 9, '渔夫帽', 10, '服装', 400, '/productImg/13/11/b8b7a1f2-7cbb-4c90-88d1-b96fb159b798.PNG', '潮流时尚，给你带来炎夏中的一抹清凉'),
('b0db4b35-396f-43d7-9b22-57bb533de60c', 10, 'Ipad', 2999, '数码产品', 100, '/productImg/13/11/d433a2dd-ff8f-441c-8648-a946875b3ba3.jpg', '买前生产力，买后爱奇艺！(bushi)办公、学习神器，有Goodnotes、notability等多款成熟笔记软件可用。'),
('bf6833bc-3dd2-4f4d-842a-a86ec0f9ee5d', 8, '纯棉毛巾', 8, '日用品', 1000, '/productImg/10/3/2fea5162-acbb-4bf7-8a57-a9f2a073a7bb.PNG', '触感柔软，多种青春亮色可选。'),
('c5a3d9a7-09aa-4fb8-a6f7-bac43a107988', 7, '方格纸', 8, '文具', 1000, '/productImg/1/0/cacf0c96-7530-4f48-9b1a-fa253111ceda.jpg', '能比较有效地改善笔记的工整程度，适合课后整理。配合几种不同颜色的笔使用效果更佳。'),
('c7b54c42-ee28-4f47-977f-91abd57d77dd', 2, '高等数学同济大学版', 40, '书籍', 200, '/productImg/0/8/e951a224-b2ab-447a-8485-5b83a90c383e.jpg', '含上下册两套，排版清晰，将数学讲得明白'),
('d22aec98-3f11-4553-92e4-b698745f293c', 9, '创意T-shirt', 49, '服装', 500, '/productImg/3/14/fed834c8-87ea-4a6d-988b-daab49ec7927.PNG', 'T-shirt上印有个性十足的短句，可以体现你的性格，诠释你的价值观'),
('dcfb0cec-f040-41b6-9229-12d67c8f4da3', 9, '健走鞋', 299, '服装', 50, '/productImg/7/12/2bd4f815-22d6-4ce4-91b4-f62b63eda801.PNG', '用料扎实，给你足够的支撑，走再久脚都不累'),
('e7860188-0025-4df1-97ca-4df95a2ab99f', 2, '数据库系统概论', 30, '书籍', 299, '/productImg/2/9/ad2d68c7-643a-4e2b-9234-0c73818851b7.jpg', '里面有非常多的数据库语句编写示例，包括数据库中的插入、修改、删除、触发等操作，非常适合初学者使用'),
('eac0c00d-f8a6-400d-bb48-17da545f9cc6', 2, 'Python语言程序设计基础', 25, '书籍', 600, '/productImg/3/5/b5b94761-2029-4ba7-9285-b1034f8147b1.jpg', '内含大量简单短小的例子，将Python的内置函数及部分常用库的使用方式介绍得非常明白'),
('ecc97a2b-6fc4-44f5-a511-c358c60904e8', 2, '线性代数（同济）', 40, '书籍', 198, '/productImg/2/1/dee1bff6-6ccf-4c53-989d-7dc8ff01736b.jpg', '教程和习题详解配套，是同济大学数学系出品的精品教材，在教会使用者做题的同时更让使用者体会到数学的奥妙。'),
('f44dac6f-7443-42fc-92a3-81a4115852d5', 2, '数学建模第五版', 42, '书籍', 100, '/productImg/7/3/6e03e0bd-b839-482f-b074-a9cc10d1e8fc.jpg', '想搞数学建模的朋友都建议买来看看，内容略基础，配有大量的简单例子，想要搞实践的朋友学完后建议另外再买一本实践教材');

-- --------------------------------------------------------

--
-- 表的结构 `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL,
  `username` varchar(15) NOT NULL,
  `password` varchar(20) NOT NULL,
  `gender` varchar(5) DEFAULT NULL,
  `email` varchar(35) DEFAULT NULL,
  `role` varchar(10) DEFAULT 'user',
  `map` varchar(24) DEFAULT '未知'
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `gender`, `email`, `role`, `map`) VALUES
(1, 'hexuyi', '123456', 'F', NULL, 'admin', '广东省'),
(2, 'test', 'eqezlypu0ll2', 'F', '1186159971@qq.com', 'business', '广东省'),
(3, 'yonghu', 'iamayonghu', 'M', '1186159971@qq.com', 'user', '广东省'),
(4, 'aile', 'ivut73gdp1tr', 'M', '1186159971@qq.com', 'business', '广东省'),
(5, 'yonghu2', 'iamayonghu2', 'F', '1186159971@qq.com', 'user', '广东省'),
(6, 'yonghu3', 'iamayonghu3', 'F', '1186159971@qq.com', 'user', '广东省'),
(7, 'aimai', 'lcfqo1ymjvqn', 'M', '1186159971@qq.com', 'business', '广东省'),
(8, 'yaomai', 'rv6x64c2jdpb', 'F', '1186159971@qq.com', 'business', '广东省'),
(9, 'xiangmai', '5wvg8t3bwlpp', 'F', '1186159971@qq.com', 'business', '广东省'),
(10, 'kuaimai', 'mw6nkoheceis', 'M', '1186159971@qq.com', 'business', '广东省');

-- --------------------------------------------------------

--
-- 替换视图以便查看 `user_cate`
--
CREATE TABLE IF NOT EXISTS `user_cate` (
`id` int(11)
,`gender` varchar(5)
,`map` varchar(24)
,`category` varchar(40)
,`CSUM` bigint(21)
);

-- --------------------------------------------------------

--
-- 替换视图以便查看 `within_month_browses`
--
CREATE TABLE IF NOT EXISTS `within_month_browses` (
`u_id` int(11)
,`b_id` int(11)
,`p_id` varchar(100)
,`begintime` timestamp
,`endtime` timestamp
,`during` int(11)
);

-- --------------------------------------------------------

--
-- 替换视图以便查看 `within_month_orders`
--
CREATE TABLE IF NOT EXISTS `within_month_orders` (
`id` varchar(100)
,`user_id` int(11)
,`money` double
,`ordertime` timestamp
);

-- --------------------------------------------------------

--
-- 视图结构 `user_cate`
--
DROP TABLE IF EXISTS `user_cate`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `user_cate` AS select `users`.`id` AS `id`,`users`.`gender` AS `gender`,`users`.`map` AS `map`,`products`.`category` AS `category`,count(0) AS `CSUM` from (((`users` join `within_month_orders`) join `products`) join `orderitems`) where ((`users`.`id` = `within_month_orders`.`user_id`) and (`orderitems`.`order_id` = `within_month_orders`.`id`) and (`products`.`p_id` = `orderitems`.`product_id`)) group by `users`.`id`,`products`.`category`;

-- --------------------------------------------------------

--
-- 视图结构 `within_month_browses`
--
DROP TABLE IF EXISTS `within_month_browses`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `within_month_browses` AS select `browses`.`u_id` AS `u_id`,`browses`.`b_id` AS `b_id`,`browses`.`p_id` AS `p_id`,`browses`.`begintime` AS `begintime`,`browses`.`endtime` AS `endtime`,`browses`.`during` AS `during` from `browses` where (`browses`.`begintime` between (select (now() + interval -(1) month)) and now());

-- --------------------------------------------------------

--
-- 视图结构 `within_month_orders`
--
DROP TABLE IF EXISTS `within_month_orders`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `within_month_orders` AS select `orders`.`id` AS `id`,`orders`.`user_id` AS `user_id`,`orders`.`money` AS `money`,`orders`.`ordertime` AS `ordertime` from `orders` where (`orders`.`ordertime` between (select (now() + interval -(1) month)) and now());

--
-- Indexes for dumped tables
--

--
-- Indexes for table `applications`
--
ALTER TABLE `applications`
  ADD KEY `u_id` (`u_id`);

--
-- Indexes for table `browses`
--
ALTER TABLE `browses`
  ADD PRIMARY KEY (`u_id`,`b_id`,`p_id`,`begintime`),
  ADD KEY `b_id` (`b_id`),
  ADD KEY `p_id` (`p_id`);

--
-- Indexes for table `loginout`
--
ALTER TABLE `loginout`
  ADD PRIMARY KEY (`u_id`,`logintime`);

--
-- Indexes for table `orderitems`
--
ALTER TABLE `orderitems`
  ADD PRIMARY KEY (`order_id`,`product_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`p_id`,`b_id`),
  ADD KEY `b_id` (`b_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- 限制导出的表
--

--
-- 限制表 `applications`
--
ALTER TABLE `applications`
  ADD CONSTRAINT `applications_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `users` (`id`);

--
-- 限制表 `browses`
--
ALTER TABLE `browses`
  ADD CONSTRAINT `browses_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `browses_ibfk_2` FOREIGN KEY (`b_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `browses_ibfk_3` FOREIGN KEY (`p_id`) REFERENCES `products` (`p_id`);

--
-- 限制表 `loginout`
--
ALTER TABLE `loginout`
  ADD CONSTRAINT `loginout_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `users` (`id`);

--
-- 限制表 `orderitems`
--
ALTER TABLE `orderitems`
  ADD CONSTRAINT `orderitems_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  ADD CONSTRAINT `orderitems_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `products` (`p_id`);

--
-- 限制表 `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- 限制表 `products`
--
ALTER TABLE `products`
  ADD CONSTRAINT `products_ibfk_1` FOREIGN KEY (`b_id`) REFERENCES `users` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
