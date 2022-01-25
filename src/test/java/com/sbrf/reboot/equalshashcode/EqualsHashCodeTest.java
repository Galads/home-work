package com.sbrf.reboot.equalshashcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

public class EqualsHashCodeTest {

    class Car {
        String model;
        String color;
        Calendar releaseDate;
        int maxSpeed;

        @Override
        public boolean equals(Object o) {

            //Рефлексивность: объект должен равняться самому себе
            if (o == this)
                return true;
            //Сравнение null
            if (o == null)
                return false;
            //Симметричность: два объекта должны иметь одно и то же мнение
            //относительно своего равенства или неравенства
            //Транзитивность: если 1 объект равен 2, а 2-й равен 3-му, то 1-й равен 3-му
            if (o instanceof Car) {
                Car other = (Car) o;
                //Согласованность: два объекта эквивалентны всегда, пока один из них не будет изменен
                return model.equals(other.model) && color.equals(other.color)
                        && releaseDate.equals(other.releaseDate) && maxSpeed == other.maxSpeed;
            }
            return false;
        }

        //1. Вызов метода над одним объектом должен возвращать одно значение, если поля не менялись
        //2. Вызов метода должен возвращать одно число, если объекты равны
        //3. Вызов метода возвращает разные числа для разных объектов (не обязательно)
        @Override
        public int hashCode() {
            int result = Integer.hashCode(maxSpeed);
            result = 31 * result + model.hashCode();
            result = 31 * result + color.hashCode();
            result = 31 * result + releaseDate.hashCode();

            return result;
        }
    }

    class CollectionCar extends Car {
        int yearsOld;

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof CollectionCar))
                return false;
            return super.equals(o) && ((CollectionCar) o).yearsOld == yearsOld;
        }
    }

    @Test
    public void assertTrueEquals() {
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 10;

        Car car2 = new Car();
        car2.model = "Mercedes";
        car2.color = "black";
        car2.releaseDate = new GregorianCalendar(2020, 0, 25);
        car2.maxSpeed = 10;


        Assertions.assertTrue(car1.equals(car2));
    }

    @Test
    public void assertFalseEquals() {
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 10;

        Car car2 = new Car();
        car2.model = "Audi";
        car2.color = "white";
        car2.releaseDate = new GregorianCalendar(2017, 0, 25);
        car2.maxSpeed = 10;

        Assertions.assertFalse(car1.equals(car2));
    }

    @Test
    public void successEqualsHashCode() {
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 10;

        Car car2 = new Car();
        car2.model = "Mercedes";
        car2.color = "black";
        car2.releaseDate = new GregorianCalendar(2020, 0, 25);
        car2.maxSpeed = 10;

        Assertions.assertEquals(car1.hashCode(), car2.hashCode());

    }

    @Test
    public void failEqualsHashCode() {
        Car car1 = new Car();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 10;

        Car car2 = new Car();
        car2.model = "Audi";
        car2.color = "white";
        car2.releaseDate = new GregorianCalendar(2017, 0, 25);
        car2.maxSpeed = 10;

        Assertions.assertNotEquals(car1.hashCode(), car2.hashCode());

    }

    @Test
    public void assertTrueEqualsWrongClass() {
        CollectionCar car1 = new CollectionCar();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 10;
        car1.yearsOld = 20;

        Car car2 = new Car();
        car2.model = "Mercedes";
        car2.color = "black";
        car2.releaseDate = new GregorianCalendar(2020, 0, 25);
        car2.maxSpeed = 10;

        Assertions.assertTrue(car2.equals(car1)); // игнорируется возраст
    }

    @Test
    public void assertFalseEqualsWrongClass() {
        CollectionCar car1 = new CollectionCar();
        car1.model = "Mercedes";
        car1.color = "black";
        car1.releaseDate = new GregorianCalendar(2020, 0, 25);
        car1.maxSpeed = 10;
        car1.yearsOld = 20;

        Car car2 = new Car();
        car2.model = "Mercedes";
        car2.color = "black";
        car2.releaseDate = new GregorianCalendar(2020, 0, 25);
        car2.maxSpeed = 10;

        Assertions.assertFalse(car1.equals(car2)); // не игнорируется возраст
    }

    @Test
    public void successEqualsHashCodeSecond() {
        Car car = new Car();
        car.model = "Mercedes";
        car.color = "white";
        car.releaseDate = new GregorianCalendar(2022, 0, 25);
        car.maxSpeed = 200;

        int result1 = car.hashCode();

        List<Integer> list = new LinkedList<Integer>() {{
            add(result1);
            add(car.hashCode());
            add(car.hashCode());
        }};

        list.forEach(e -> Assertions.assertEquals(result1, e));
    }

    @Test
    public void failEqualsHashCodeSecond() {
        Car car = new Car();
        car.model = "Mercedes";
        car.color = "white";
        car.releaseDate = new GregorianCalendar(2022, 0, 25);
        car.maxSpeed = 200;

        List<Integer> list = new LinkedList<Integer>() {{
            add(car.hashCode());
            add(car.hashCode());
            add(car.hashCode());
        }};

        car.maxSpeed = 300;

        list.forEach(e -> Assertions.assertNotEquals(car.hashCode(), e));
    }
}
