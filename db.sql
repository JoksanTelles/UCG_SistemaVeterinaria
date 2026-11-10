-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema veterinaria
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema veterinaria
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `veterinaria` DEFAULT CHARACTER SET utf8 ;
USE `veterinaria` ;

-- -----------------------------------------------------
-- Table `veterinaria`.`clientes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `veterinaria`.`clientes` (
  `id_cliente` INT NOT NULL AUTO_INCREMENT,
  `fecha_registro` VARCHAR(45) NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `telefono` VARCHAR(45) NOT NULL,
  `correo` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(45) NOT NULL,
  `apellido_p` VARCHAR(45) NOT NULL,
  `apellido_m` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_cliente`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `veterinaria`.`mascotas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `veterinaria`.`mascotas` (
  `id_mascota` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(65) NOT NULL,
  `especie` VARCHAR(45) NOT NULL,
  `raza` VARCHAR(45) NOT NULL,
  `sexo` VARCHAR(45) NOT NULL,
  `peso` DECIMAL(5,2) NOT NULL,
  `color` VARCHAR(45) NOT NULL,
  `fecha_nacimiento` DATE NOT NULL,
  `id_cliente` INT NOT NULL,
  PRIMARY KEY (`id_mascota`),
  INDEX `fk_mascotas_clientes1_idx` (`id_cliente` ASC) VISIBLE,
  CONSTRAINT `fk_mascotas_clientes1`
    FOREIGN KEY (`id_cliente`)
    REFERENCES `veterinaria`.`clientes` (`id_cliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `veterinaria`.`servicios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `veterinaria`.`servicios` (
  `id_servicio` INT NOT NULL AUTO_INCREMENT,
  `tipo_servicio` VARCHAR(45) NOT NULL,
  `descripcion` VARCHAR(145) NOT NULL,
  `costo` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id_servicio`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `veterinaria`.`citas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `veterinaria`.`citas` (
  `id_cita` INT NOT NULL AUTO_INCREMENT,
  `fecha` DATE NOT NULL,
  `hora` VARCHAR(45) NOT NULL,
  `motivo` VARCHAR(245) NOT NULL,
  `id_mascota` INT NOT NULL,
  `id_servicios` INT NOT NULL,
  PRIMARY KEY (`id_cita`),
  INDEX `fk_citas_mascotas1_idx` (`id_mascota` ASC) VISIBLE,
  INDEX `fk_citas_servicios1_idx` (`id_servicios` ASC) VISIBLE,
  CONSTRAINT `fk_citas_mascotas1`
    FOREIGN KEY (`id_mascota`)
    REFERENCES `veterinaria`.`mascotas` (`id_mascota`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_citas_servicios1`
    FOREIGN KEY (`id_servicios`)
    REFERENCES `veterinaria`.`servicios` (`id_servicio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `veterinaria`.`consultas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `veterinaria`.`consultas` (
  `id_consulta` INT NOT NULL AUTO_INCREMENT,
  `fecha_consulta` DATETIME NOT NULL,
  `sintomas` VARCHAR(45) NOT NULL,
  `diagnostico` VARCHAR(65) NOT NULL,
  `tratamiento` VARCHAR(65) NOT NULL,
  `observaciones` VARCHAR(200) NOT NULL,
  `id_cita` INT NOT NULL,
  PRIMARY KEY (`id_consulta`),
  INDEX `fk_consultas_citas_idx` (`id_cita` ASC) VISIBLE,
  CONSTRAINT `fk_consultas_citas`
    FOREIGN KEY (`id_cita`)
    REFERENCES `veterinaria`.`citas` (`id_cita`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
