package tech.lapsa.esbd.dao.entities;

import java.time.LocalDate;

import com.lapsa.insurance.elements.SteeringWheelLocation;
import com.lapsa.insurance.elements.VehicleClass;

import tech.lapsa.esbd.dao.Domain;
import tech.lapsa.kz.vehicle.VehicleRegNumber;
import tech.lapsa.patterns.domain.HashCodePrime;

@HashCodePrime(31)
public class VehicleEntity extends Domain {

    private static final long serialVersionUID = 1L;

    // TF_ID s:int Идентификатор ТС
    Integer id;

    public Integer getId() {
	return id;
    }

    // TF_TYPE_ID s:int Тип ТС (справочник TF_TYPES)
    public int vehicleClassId;
    VehicleClass vehicleClass;

    public VehicleClass getVehicleClass() {
	return vehicleClass;
    }
    
    void setVehicleClass(VehicleClass vehicleClass) {
        this.vehicleClass = vehicleClass;
    }

    // VIN s:string VIN код (номер кузова) (обязательно)
    String vinCode;

    public String getVinCode() {
	return vinCode;
    }

    // MODEL_ID s:int Марка\Модель (справочник VOITURE_MODELS) (обязательно)
    int vehicleModelId;
    VehicleModelEntity vehicleModel;

    public VehicleModelEntity getVehicleModel() {
	return vehicleModel;
    }
    
    void setVehicleModel(VehicleModelEntity vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    // RIGHT_HAND_DRIVE_BOOL s:int Признак расположения руля (0 - слева; 1 -
    // справа)
    SteeringWheelLocation steeringWheelLocation;

    public SteeringWheelLocation getSteeringWheelLocation() {
	return steeringWheelLocation;
    }

    // ENGINE_VOLUME s:int Объем двигателя (куб.см.)
    int engineVolume;

    public int getEngineVolume() {
	return engineVolume;
    }

    // ENGINE_NUMBER s:string Номер двигателя
    String enineNumber;

    public String getEnineNumber() {
	return enineNumber;
    }

    // ENGINE_POWER s:int Мощность двигателя (квт.)
    int enginePower;

    public int getEnginePower() {
	return enginePower;
    }

    // COLOR s:string Цвет ТС
    String color;

    public String getColor() {
	return color;
    }

    // BORN s:string Год выпуска (обязательно)
    // BORN_MONTH s:int Месяц выпуска ТС
    LocalDate realeaseDate;

    public LocalDate getRealeaseDate() {
	return realeaseDate;
    }

    VehicleRegNumber regNum;

    public VehicleRegNumber getRegNum() {
	return regNum;
    }

    void setRegNum(VehicleRegNumber regNum) {
        this.regNum = regNum;
    }
}
