package spring.bean;

import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.FactoryBean;

@Setter
@ToString
public class CarFactoryBean implements FactoryBean<Car> {

    private String carInfo;

    public CarFactoryBean(String carInfo) {
        this.carInfo = carInfo;
    }

    @Override
    public Car getObject() throws Exception {
        Car car = new Car();
        String[] arr = carInfo.split(",");
        car.setMaxSpeed(Integer.parseInt(arr[0]));
        car.setBrand(arr[1]);
        car.setPrice(Double.parseDouble(arr[2]));
        return car;
    }

    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }
}
