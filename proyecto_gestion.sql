-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-11-2024 a las 14:42:53
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyecto_gestion`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumno`
--

CREATE TABLE `alumno` (
  `Id_Alumno` int(11) NOT NULL,
  `Nombre` varchar(25) NOT NULL,
  `Apellidos` varchar(35) NOT NULL,
  `Tutor_Empresa` varchar(35) DEFAULT NULL,
  `Tutor_Grupo` varchar(35) DEFAULT NULL,
  `Pendiente` tinyint(1) DEFAULT 0,
  `Programa` varchar(50) DEFAULT NULL,
  `Contacto` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `alumno`
--

INSERT INTO `alumno` (`Id_Alumno`, `Nombre`, `Apellidos`, `Tutor_Empresa`, `Tutor_Grupo`, `Pendiente`, `Programa`, `Contacto`) VALUES
(1, 'Daniel', 'Darmanin', 'Fran', 'vr', 1, '2 DAM', 'OFEP');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumnos_empresas_rel`
--

CREATE TABLE `alumnos_empresas_rel` (
  `Id_Asignacion` int(11) NOT NULL,
  `Id_Alumno` int(11) NOT NULL,
  `Id_Empresa` int(11) NOT NULL,
  `Id_Tutor_Empresa` int(11) DEFAULT NULL,
  `Id_Tutor_Docente` int(11) DEFAULT NULL,
  `Fecha_Asignacion` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentarios`
--

CREATE TABLE `comentarios` (
  `Id_Comentario` int(11) NOT NULL,
  `Comentario` text NOT NULL,
  `Fecha_Comentario` timestamp NOT NULL DEFAULT current_timestamp(),
  `Id_Alumno` int(11) NOT NULL,
  `Id_Tutor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentarios_empresa`
--

CREATE TABLE `comentarios_empresa` (
  `Id_Comentario` int(11) NOT NULL,
  `Comentario` text NOT NULL,
  `Fecha_Comentario` timestamp NOT NULL DEFAULT current_timestamp(),
  `Id_Empresa` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresas`
--

CREATE TABLE `empresas` (
  `Id_Empresa` int(11) NOT NULL,
  `Nombre` varchar(100) NOT NULL,
  `Direccion` text DEFAULT NULL,
  `Telefono` varchar(15) DEFAULT NULL,
  `Correo` varchar(100) DEFAULT NULL,
  `Participa` tinyint(1) DEFAULT 0,
  `Plazas` int(11) DEFAULT NULL,
  `Especialidad` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Volcado de datos para la tabla `empresas`
--

INSERT INTO `empresas` (`Id_Empresa`, `Nombre`, `Direccion`, `Telefono`, `Correo`, `Participa`, `Plazas`, `Especialidad`) VALUES
(1, 'qw', '', 'qw', 'qw', 1, 7, '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tutor_empresa`
--

CREATE TABLE `tutor_empresa` (
  `Id_Tutor` int(11) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Contacto` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tutor_grupo`
--

CREATE TABLE `tutor_grupo` (
  `Id_Tutor` int(11) NOT NULL,
  `Nombre` varchar(35) NOT NULL,
  `Grupo` varchar(10) NOT NULL,
  `Id_Alumno` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `visitas`
--

CREATE TABLE `visitas` (
  `Id_Visita` int(11) NOT NULL,
  `Fecha_Visita` date NOT NULL,
  `Observaciones` text DEFAULT NULL,
  `Id_Alumno` int(11) NOT NULL,
  `Id_Tutor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumno`
--
ALTER TABLE `alumno`
  ADD PRIMARY KEY (`Id_Alumno`);

--
-- Indices de la tabla `alumnos_empresas_rel`
--
ALTER TABLE `alumnos_empresas_rel`
  ADD PRIMARY KEY (`Id_Asignacion`),
  ADD KEY `Id_Alumno` (`Id_Alumno`),
  ADD KEY `Id_Empresa` (`Id_Empresa`),
  ADD KEY `Id_Tutor_Empresa` (`Id_Tutor_Empresa`),
  ADD KEY `Id_Tutor_Docente` (`Id_Tutor_Docente`);

--
-- Indices de la tabla `comentarios`
--
ALTER TABLE `comentarios`
  ADD PRIMARY KEY (`Id_Comentario`),
  ADD KEY `Id_Alumno` (`Id_Alumno`),
  ADD KEY `Id_Tutor` (`Id_Tutor`);

--
-- Indices de la tabla `comentarios_empresa`
--
ALTER TABLE `comentarios_empresa`
  ADD PRIMARY KEY (`Id_Comentario`),
  ADD KEY `Id_Empresa` (`Id_Empresa`);

--
-- Indices de la tabla `empresas`
--
ALTER TABLE `empresas`
  ADD PRIMARY KEY (`Id_Empresa`);

--
-- Indices de la tabla `tutor_empresa`
--
ALTER TABLE `tutor_empresa`
  ADD PRIMARY KEY (`Id_Tutor`);

--
-- Indices de la tabla `tutor_grupo`
--
ALTER TABLE `tutor_grupo`
  ADD PRIMARY KEY (`Id_Tutor`),
  ADD KEY `Id_Alumno` (`Id_Alumno`);

--
-- Indices de la tabla `visitas`
--
ALTER TABLE `visitas`
  ADD PRIMARY KEY (`Id_Visita`),
  ADD KEY `Id_Alumno` (`Id_Alumno`),
  ADD KEY `Id_Tutor` (`Id_Tutor`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alumno`
--
ALTER TABLE `alumno`
  MODIFY `Id_Alumno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `alumnos_empresas_rel`
--
ALTER TABLE `alumnos_empresas_rel`
  MODIFY `Id_Asignacion` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `comentarios`
--
ALTER TABLE `comentarios`
  MODIFY `Id_Comentario` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `comentarios_empresa`
--
ALTER TABLE `comentarios_empresa`
  MODIFY `Id_Comentario` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `empresas`
--
ALTER TABLE `empresas`
  MODIFY `Id_Empresa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `tutor_empresa`
--
ALTER TABLE `tutor_empresa`
  MODIFY `Id_Tutor` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `tutor_grupo`
--
ALTER TABLE `tutor_grupo`
  MODIFY `Id_Tutor` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `visitas`
--
ALTER TABLE `visitas`
  MODIFY `Id_Visita` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `alumnos_empresas_rel`
--
ALTER TABLE `alumnos_empresas_rel`
  ADD CONSTRAINT `alumnos_empresas_rel_ibfk_1` FOREIGN KEY (`Id_Alumno`) REFERENCES `alumno` (`Id_Alumno`) ON DELETE CASCADE,
  ADD CONSTRAINT `alumnos_empresas_rel_ibfk_2` FOREIGN KEY (`Id_Empresa`) REFERENCES `empresas` (`Id_Empresa`) ON DELETE CASCADE,
  ADD CONSTRAINT `alumnos_empresas_rel_ibfk_3` FOREIGN KEY (`Id_Tutor_Empresa`) REFERENCES `tutor_empresa` (`Id_Tutor`) ON DELETE SET NULL,
  ADD CONSTRAINT `alumnos_empresas_rel_ibfk_4` FOREIGN KEY (`Id_Tutor_Docente`) REFERENCES `tutor_grupo` (`Id_Tutor`) ON DELETE SET NULL;

--
-- Filtros para la tabla `comentarios`
--
ALTER TABLE `comentarios`
  ADD CONSTRAINT `comentarios_ibfk_1` FOREIGN KEY (`Id_Alumno`) REFERENCES `alumno` (`Id_Alumno`) ON DELETE CASCADE,
  ADD CONSTRAINT `comentarios_ibfk_2` FOREIGN KEY (`Id_Tutor`) REFERENCES `tutor_grupo` (`Id_Tutor`) ON DELETE CASCADE;

--
-- Filtros para la tabla `comentarios_empresa`
--
ALTER TABLE `comentarios_empresa`
  ADD CONSTRAINT `comentarios_empresa_ibfk_1` FOREIGN KEY (`Id_Empresa`) REFERENCES `empresas` (`Id_Empresa`) ON DELETE CASCADE;

--
-- Filtros para la tabla `tutor_grupo`
--
ALTER TABLE `tutor_grupo`
  ADD CONSTRAINT `tutor_grupo_ibfk_1` FOREIGN KEY (`Id_Alumno`) REFERENCES `alumno` (`Id_Alumno`) ON DELETE SET NULL;

--
-- Filtros para la tabla `visitas`
--
ALTER TABLE `visitas`
  ADD CONSTRAINT `visitas_ibfk_1` FOREIGN KEY (`Id_Alumno`) REFERENCES `alumno` (`Id_Alumno`) ON DELETE CASCADE,
  ADD CONSTRAINT `visitas_ibfk_2` FOREIGN KEY (`Id_Tutor`) REFERENCES `tutor_grupo` (`Id_Tutor`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
