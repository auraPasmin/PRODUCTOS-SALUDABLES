-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 25-11-2020 a las 15:49:39
-- Versión del servidor: 10.4.14-MariaDB
-- Versión de PHP: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyectomaestrocracksupremo`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `NIT` varchar(50) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `direccion` varchar(50) NOT NULL,
  `X` double NOT NULL,
  `Y` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`NIT`, `nombre`, `direccion`, `X`, `Y`) VALUES
('125489', 'Andres', 'cra50-#45-52', 3.415627, -76.884625),
('321565', 'Mario', 'cra102-#76-13', 3.745823, -76.964484),
('456888', 'Hernan', 'cra100-#36-12', 3.789463, -76.798462),
('468200', 'Alejandro', 'cra104-#86-87', 3.741258, -76.78952),
('591709', 'Mauricio', 'cra104-#97-54', 3.963258, -76.745862),
('731923', 'Javier', 'cra103-#74-54', 3.741852, -76.742035),
('748553', 'Antonio', 'cra70-#78-75', 3.978654, -76.713584),
('789645', 'Julio', 'cra60-#56-64', 3.789546, -76.963248),
('852199', 'Alberto', 'cra90-#32-97', 3.481592, -76.761843),
('963588', 'Omar', 'cra80-#98-89', 3.312645, -76.746982);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `materiaprima`
--

CREATE TABLE `materiaprima` (
  `nombre` varchar(50) NOT NULL,
  `cantidad` int(10) NOT NULL,
  `fechaCaducidad` date NOT NULL,
  `NIT_proveedor` varchar(50) NOT NULL,
  `valorUnitario` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `materiaprima`
--

INSERT INTO `materiaprima` (`nombre`, `cantidad`, `fechaCaducidad`, `NIT_proveedor`, `valorUnitario`) VALUES
('arroz', 0, '2020-12-31', '985263', 600),
('carne', 410, '2020-12-03', '985263', 600),
('harina-trigo', 250, '2020-11-05', '985263', 600),
('masa-hojaldre', 400, '2020-11-05', '985263', 600),
('papa', 680, '2021-02-10', '985263', 600),
('pollo', 300, '2020-12-05', '985263', 600),
('queso', 300, '0000-00-00', '985263', 600);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `Nombre` varchar(50) NOT NULL,
  `Cantidad` int(10) NOT NULL,
  `Precio` double NOT NULL,
  `FechaCaducidad` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`Nombre`, `Cantidad`, `Precio`, `FechaCaducidad`) VALUES
('churro', 0, 50, '2020-11-25'),
('dedo', 10, 60, '2020-11-27'),
('empanada', 10, 80, '2020-11-29'),
('hojaldra', 20, 70, '2020-11-20'),
('papaRellena', 34, 90, '2020-11-29'),
('pastelDeCarne', 20, 100, '2020-11-30'),
('pastelDePollo', 10, 70, '2020-11-30');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

CREATE TABLE `proveedor` (
  `NIT` varchar(50) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Ubicacion` varchar(50) NOT NULL,
  `Telefono` int(11) NOT NULL,
  `Email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `proveedor`
--

INSERT INTO `proveedor` (`NIT`, `Nombre`, `Ubicacion`, `Telefono`, `Email`) VALUES
('315468', 'lulu', 'calle#4562', 489798, 'juyj@correo.com'),
('324567', 'lula', 'calle#4756', 462123, 'dtrey@correo.com'),
('455301', 'lalo', 'calle#4950', 456421, 'dasd@correo.com'),
('512245', 'lila', 'calle#7425', 421231, 'jliol@correo.com'),
('985263', 'martha', 'calle#9632', 47446, 'papsa@correo.com@correo.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `receta`
--

CREATE TABLE `receta` (
  `P` varchar(50) NOT NULL,
  `M` varchar(50) NOT NULL,
  `Cantidad` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `receta`
--

INSERT INTO `receta` (`P`, `M`, `Cantidad`) VALUES
('dedo', 'masa-hojaldre', 60),
('dedo', 'queso', 40),
('Empanada', 'carne', 30),
('Empanada', 'papa', 50),
('hojaldra', 'masa-hojaldre', 50),
('papaRellena', 'arroz', 60),
('papaRellena', 'carne', 30),
('papaRellena', 'papa', 40),
('pastelDeCarne', 'carne', 40),
('pastelDeCarne', 'harina-Trigo', 80),
('pastelDePollo', 'harina-Trigo', 80),
('pastelDePollo', 'pollo', 50);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `recibo`
--

CREATE TABLE `recibo` (
  `V` int(10) NOT NULL,
  `C` varchar(50) NOT NULL,
  `P` varchar(50) NOT NULL,
  `Fecha` datetime NOT NULL,
  `Cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `recibo`
--

INSERT INTO `recibo` (`V`, `C`, `P`, `Fecha`, `Cantidad`) VALUES
(456412, '125489', 'dedo', '2020-11-20 18:39:52', 20),
(456412, '125489', 'hojaldra', '2020-11-20 18:39:52', 30),
(456412, '125489', 'pastelDePollo', '2020-11-20 18:39:52', 20),
(456412, '456888', 'churro', '2020-11-20 19:06:29', 30),
(456412, '456888', 'dedo', '2020-11-20 19:06:29', 10),
(456412, '963588', 'churro', '2020-11-20 19:07:22', 30),
(456412, '963588', 'papaRellena', '2020-11-20 19:07:22', 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vendedores`
--

CREATE TABLE `vendedores` (
  `cedula` int(10) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `cargo` varchar(50) NOT NULL,
  `comision` double NOT NULL,
  `Telefono` int(10) NOT NULL,
  `correo` varchar(50) NOT NULL,
  `sexo` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `vendedores`
--

INSERT INTO `vendedores` (`cedula`, `nombre`, `cargo`, `comision`, `Telefono`, `correo`, `sexo`) VALUES
(456412, 'Mario', 'vendedor', 0.12, 312353635, 'dasda@correo.com', 'M'),
(478541, 'Arnold', 'vendedor', 0.11, 31425625, 'ujki@correo.com', 'M'),
(478544, 'Bryan', 'vendedor', 0.13, 3124564, 'lilo@correo.com', 'M'),
(478798, 'Marcus', 'vendedor', 0.12, 31055254, 'htgr@correo.com', 'M'),
(7884532, 'Anton', 'vendedor', 0.14, 31324547, 'kaka@correo.com', 'M');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`NIT`);

--
-- Indices de la tabla `materiaprima`
--
ALTER TABLE `materiaprima`
  ADD PRIMARY KEY (`nombre`),
  ADD KEY `NIT_proveedor` (`NIT_proveedor`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`Nombre`);

--
-- Indices de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD PRIMARY KEY (`NIT`);

--
-- Indices de la tabla `receta`
--
ALTER TABLE `receta`
  ADD PRIMARY KEY (`P`,`M`),
  ADD KEY `M` (`M`);

--
-- Indices de la tabla `recibo`
--
ALTER TABLE `recibo`
  ADD PRIMARY KEY (`V`,`C`,`P`,`Fecha`),
  ADD KEY `C` (`C`),
  ADD KEY `P` (`P`);

--
-- Indices de la tabla `vendedores`
--
ALTER TABLE `vendedores`
  ADD PRIMARY KEY (`cedula`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `materiaprima`
--
ALTER TABLE `materiaprima`
  ADD CONSTRAINT `materiaprima_ibfk_1` FOREIGN KEY (`NIT_proveedor`) REFERENCES `proveedor` (`NIT`);

--
-- Filtros para la tabla `receta`
--
ALTER TABLE `receta`
  ADD CONSTRAINT `receta_ibfk_1` FOREIGN KEY (`P`) REFERENCES `producto` (`Nombre`),
  ADD CONSTRAINT `receta_ibfk_2` FOREIGN KEY (`M`) REFERENCES `materiaprima` (`nombre`);

--
-- Filtros para la tabla `recibo`
--
ALTER TABLE `recibo`
  ADD CONSTRAINT `recibo_ibfk_1` FOREIGN KEY (`V`) REFERENCES `vendedores` (`cedula`),
  ADD CONSTRAINT `recibo_ibfk_2` FOREIGN KEY (`C`) REFERENCES `cliente` (`NIT`),
  ADD CONSTRAINT `recibo_ibfk_3` FOREIGN KEY (`P`) REFERENCES `producto` (`Nombre`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
