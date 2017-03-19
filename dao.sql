-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema dao
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema dao
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dao` DEFAULT CHARACTER SET utf8 ;
USE `dao` ;

-- -----------------------------------------------------
-- Table `dao`.`oficina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dao`.`oficina` (
  `nombre` INT(11) NULL DEFAULT NULL,
  `direccion` VARCHAR(40) NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `dao`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dao`.`usuario` (
  `id` INT(11) NOT NULL,
  `nombre` VARCHAR(40) NULL DEFAULT NULL,
  `email` VARCHAR(100) NULL DEFAULT NULL,
  `password` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
