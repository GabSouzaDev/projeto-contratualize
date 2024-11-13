-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 26/10/2024 às 06:49
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `contratualize`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `contract`
--

CREATE TABLE `contract` (
  `id` bigint(20) NOT NULL,
  `clientName` varchar(255) DEFAULT NULL,
  `contractLink` varchar(500) DEFAULT NULL,
  `contractNumber` varchar(255) DEFAULT NULL,
  `numberOfInstallments` int(11) NOT NULL,
  `startDate` date DEFAULT NULL,
  `bankCode` int(32) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `contract`
--

INSERT INTO `contract` (`id`, `clientName`, `contractLink`, `contractNumber`, `numberOfInstallments`, `startDate`, `bankCode`) VALUES
(9, 'Cliente exemplo', 'https://onedrive.live.com/?id=ec0b3dd21386994d%210%5ERG9jdW1lbnRvcy9HYWJyaWVsIFNvdXphL2NvbnRyYXRvcy9FRElORVNJQSBYQVZJRVIvZW5jb3V2aWRvcmFjYWl4YW9jb3JybmNpYW4xNDQ4NzYzMWVkaW5lc2lhZGFjL2NvbnNpZ25hZG9fcHVibGljb18xNi4yNzc2LjExMC4wMDMyMDg3LTAwL2NvbnNpZ25hZG9fcHVibGljb18xNi4yNzc2LjExMC4wMDMyMDg3LTAwLnBkZg', '12345', 12, '2023-12-16', NULL),
(10, 'CRISTIANA ALVES DE SOUZA', 'https://onedrive.live.com/?cid=EC0B3DD21386994D&id=EC0B3DD21386994D%21s6da009176dcb476eae69c1a56509c056&parId=EC0B3DD21386994D%21s045350fe93f74278ab087c6ca60aed96&o=OneUp', '503355049', 84, '2024-08-05', NULL),
(11, 'EDINESIA XAVIER', 'https://www.teste/link.pdf', '16.2776.110.0013443-05', 144, '2020-10-10', 104),
(12, 'ILMAR RODRIGUES DA SILVA', 'https://teste.com/envio.pdf', '365671494', 84, '2022-10-19', 623),
(14, 'TESTE MALS', 'https://teste.pdf', '123456', 10, '2010-10-10', 12);

-- --------------------------------------------------------

--
-- Estrutura para tabela `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `user`
--

INSERT INTO `user` (`id`, `password`, `role`, `username`, `email`) VALUES
(13, '1234567', 'GESTOR', 'Gabriel', ''),
(14, 'admin1234', 'GESTOR', 'Gabriel183', 'gabrielfreelasouza@gmail.com'),
(20, '1234567890', 'ADMIN', 'Malu', 'mlb.ribeiro20@gmail.com'),
(21, '23e', 'ADMIN', 'Gabriel478', 'ireine@gmail.com'),
(22, '12345', 'GESTOR', 'Malu34', 'mlb.ribeiro20@gmail.com');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `contract`
--
ALTER TABLE `contract`
  ADD PRIMARY KEY (`id`);

--
-- Índices de tabela `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `contract`
--
ALTER TABLE `contract`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de tabela `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
