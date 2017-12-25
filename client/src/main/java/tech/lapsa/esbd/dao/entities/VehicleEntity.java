package tech.lapsa.esbd.dao.entities;

import java.time.LocalDate;

import com.lapsa.insurance.elements.SteeringWheelLocation;
import com.lapsa.insurance.elements.VehicleClass;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(31)
public class VehicleEntity extends Domain {

    private static final long serialVersionUID = 1L;

    // TF_ID s:int Идентификатор ТС
    private Integer id;

    // TF_TYPE_ID s:int Тип ТС (справочник TF_TYPES)
    private VehicleClass vehicleClass;

    // VIN s:string VIN код (номер кузова) (обязательно)
    private String vinCode;

    // MODEL_ID s:int Марка\Модель (справочник VOITURE_MODELS) (обязательно)
    private VehicleModelEntity vehicleModel;

    // RIGHT_HAND_DRIVE_BOOL s:int Признак расположения руля (0 - слева; 1 -
    // справа)
    private SteeringWheelLocation steeringWheelLocation;

    // ENGINE_VOLUME s:int Объем двигателя (куб.см.)
    private int engineVolume;

    // ENGINE_NUMBER s:string Номер двигателя
    private String enineNumber;

    // ENGINE_POWER s:int Мощность двигателя (квт.)
    private int enginePower;

    // COLOR s:string Цвет ТС
    private String color;

    // BORN s:string Год выпуска (обязательно)
    // BORN_MONTH s:int Месяц выпуска ТС
    private LocalDate realeaseDate;

    private String regNum;

    // GENERATED

    public Integer getId() {
	return id;
    }

    public void setId(final Integer id) {
	this.id = id;
    }

    public VehicleClass getVehicleClass() {
	return vehicleClass;
    }

    public void setVehicleClass(final VehicleClass vehicleClass) {
	this.vehicleClass = vehicleClass;
    }

    public String getVinCode() {
	return vinCode;
    }

    public void setVinCode(final String vinCode) {
	this.vinCode = vinCode;
    }

    public VehicleModelEntity getVehicleModel() {
	return vehicleModel;
    }

    public void setVehicleModel(final VehicleModelEntity vehicleModel) {
	this.vehicleModel = vehicleModel;
    }

    public SteeringWheelLocation getSteeringWheelLocation() {
	return steeringWheelLocation;
    }

    public void setSteeringWheelLocation(final SteeringWheelLocation steeringWheelLocation) {
	this.steeringWheelLocation = steeringWheelLocation;
    }

    public int getEngineVolume() {
	return engineVolume;
    }

    public void setEngineVolume(final int engineVolume) {
	this.engineVolume = engineVolume;
    }

    public String getEnineNumber() {
	return enineNumber;
    }

    public void setEnineNumber(final String enineNumber) {
	this.enineNumber = enineNumber;
    }

    public int getEnginePower() {
	return enginePower;
    }

    public void setEnginePower(final int enginePower) {
	this.enginePower = enginePower;
    }

    public String getColor() {
	return color;
    }

    public void setColor(final String color) {
	this.color = color;
    }

    public LocalDate getRealeaseDate() {
	return realeaseDate;
    }

    public void setRealeaseDate(final LocalDate realeaseDate) {
	this.realeaseDate = realeaseDate;
    }

    public void setRegNum(final String regNum) {
	this.regNum = regNum;
    }

    public String getRegNum() {
	return regNum;
    }
}
