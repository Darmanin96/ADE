-- Creación de base de datos
DROP DATABASE IF EXISTS proyecto_gestion;
CREATE DATABASE IF NOT EXISTS proyecto_gestion;
USE proyecto_gestion;

-- Tabla 'alumno'
CREATE TABLE `alumno` (
  `Id_Alumno` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(25) COLLATE utf8mb4_spanish_ci NOT NULL,
  `Apellidos` varchar(35) COLLATE utf8mb4_spanish_ci NOT NULL,
  `Tutor_Empresa` varchar(35) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `Tutor_Grupo` varchar(35) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `Pendiente` tinyint(1) DEFAULT 0,
  `Programa` varchar(50) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `Contacto` varchar(50) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`Id_Alumno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- Tabla 'empresas'
CREATE TABLE `empresas` (
  `Id_Empresa` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(100) COLLATE utf8mb4_spanish_ci NOT NULL,
  `Direccion` text COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `Telefono` varchar(15) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `Correo` varchar(100) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `Participa` tinyint(1) DEFAULT 0,
  `Plazas` int(11) DEFAULT NULL,
  `Especialidad` varchar(50) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`Id_Empresa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- Tabla 'comentarios_empresa'
CREATE TABLE `comentarios_empresa` (
  `Id_Comentario` int(11) NOT NULL AUTO_INCREMENT,
  `Comentario` text COLLATE utf8mb4_spanish_ci NOT NULL,
  `Fecha_Comentario` timestamp NOT NULL DEFAULT current_timestamp(),
  `Id_Empresa` int(11) NOT NULL,
  PRIMARY KEY (`Id_Comentario`),
  FOREIGN KEY (`Id_Empresa`) REFERENCES `empresas` (`Id_Empresa`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- Tabla 'tutor_empresa'
CREATE TABLE `tutor_empresa` (
  `Id_Tutor` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) COLLATE utf8mb4_spanish_ci NOT NULL,
  `Contacto` varchar(50) COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`Id_Tutor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- Tabla 'tutor_empresa_rel'
CREATE TABLE `tutor_empresa_rel` (
  `Id_Tutor` int(11) NOT NULL,
  `Id_Empresa` int(11) NOT NULL,
  PRIMARY KEY (`Id_Tutor`,`Id_Empresa`),
  FOREIGN KEY (`Id_Tutor`) REFERENCES `tutor_empresa` (`Id_Tutor`) ON DELETE CASCADE,
  FOREIGN KEY (`Id_Empresa`) REFERENCES `empresas` (`Id_Empresa`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- Tabla 'tutor_grupo'
CREATE TABLE `tutor_grupo` (
  `Id_Tutor` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(35) COLLATE utf8mb4_spanish_ci NOT NULL,
  `Grupo` varchar(10) COLLATE utf8mb4_spanish_ci NOT NULL,
  `Id_Alumno` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id_Tutor`),
  FOREIGN KEY (`Id_Alumno`) REFERENCES `alumno` (`Id_Alumno`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- Tabla 'tutor_alumno'
CREATE TABLE `tutor_alumno` (
  `Id_Tutor` int(11) NOT NULL,
  `Id_Alumno` int(11) NOT NULL,
  PRIMARY KEY (`Id_Tutor`,`Id_Alumno`),
  FOREIGN KEY (`Id_Tutor`) REFERENCES `tutor_empresa` (`Id_Tutor`) ON DELETE CASCADE,
  FOREIGN KEY (`Id_Alumno`) REFERENCES `alumno` (`Id_Alumno`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- Tabla 'visitas'
CREATE TABLE `visitas` (
  `Id_Visita` int(11) NOT NULL AUTO_INCREMENT,
  `Fecha_Visita` date NOT NULL,
  `Observaciones` text COLLATE utf8mb4_spanish_ci DEFAULT NULL,
  `Id_Alumno` int(11) NOT NULL,
  `Id_Tutor` int(11) NOT NULL,
  PRIMARY KEY (`Id_Visita`),
  FOREIGN KEY (`Id_Alumno`) REFERENCES `alumno` (`Id_Alumno`) ON DELETE CASCADE,
  FOREIGN KEY (`Id_Tutor`) REFERENCES `tutor_grupo` (`Id_Tutor`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- Tabla 'comentarios'
CREATE TABLE `comentarios` (
  `Id_Comentario` int(11) NOT NULL AUTO_INCREMENT,
  `Comentario` text COLLATE utf8mb4_spanish_ci NOT NULL,
  `Fecha_Comentario` timestamp NOT NULL DEFAULT current_timestamp(),
  `Id_Alumno` int(11) NOT NULL,
  `Id_Tutor` int(11) NOT NULL,
  PRIMARY KEY (`Id_Comentario`),
  FOREIGN KEY (`Id_Alumno`) REFERENCES `alumno` (`Id_Alumno`) ON DELETE CASCADE,
  FOREIGN KEY (`Id_Tutor`) REFERENCES `tutor_grupo` (`Id_Tutor`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;

-- Tabla 'asignaciones'
CREATE TABLE `alumnos_empresas_rel` (
  `Id_Asignacion` int(11) NOT NULL AUTO_INCREMENT,
  `Id_Alumno` int(11) NOT NULL,
  `Id_Empresa` int(11) NOT NULL,
  `Id_Tutor_Empresa` int(11) DEFAULT NULL,
  `Id_Tutor_Docente` int(11) DEFAULT NULL,
  `Fecha_Asignacion` date NOT NULL,
  PRIMARY KEY (`Id_Asignacion`),
  FOREIGN KEY (`Id_Alumno`) REFERENCES `alumno` (`Id_Alumno`) ON DELETE CASCADE,
  FOREIGN KEY (`Id_Empresa`) REFERENCES `empresas` (`Id_Empresa`) ON DELETE CASCADE,
  FOREIGN KEY (`Id_Tutor_Empresa`) REFERENCES `tutor_empresa` (`Id_Tutor`) ON DELETE SET NULL,
  FOREIGN KEY (`Id_Tutor_Docente`) REFERENCES `tutor_grupo` (`Id_Tutor`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
