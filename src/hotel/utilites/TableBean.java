package hotel.utilites;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TableBean implements Serializable {
	
	
	
	private List<Car> carsSmall;

	private List<Car> droppedCars;
	
	 private final static String[] color = new String[10];  
	  
	 private final static String[] manufacturer = new String[10];  
	  

	public TableBean() {
		carsSmall = new ArrayList<Car>();
		droppedCars = new ArrayList<Car>();

		populateRandomCars(carsSmall, 9);
	}

	private void populateRandomCars(List<Car> list, int size) {
		for (int i = 0; i < size; i++)
			list.add(new Car(getRandomModel(), getRandomYear(),
					getRandomManufacturer(), getRandomColor()));
	}

	public List<Car> getCarsSmall() {
		return carsSmall;
	}
	
	 private Car selectedCar;  
	  

	private int getRandomYear() {
		return (int) (Math.random() * 50 + 1960);
	}

	private String getRandomColor() {
		return color[(int) (Math.random() * 10)];
	}

	private String getRandomManufacturer() {
		return manufacturer[(int) (Math.random() * 10)];
	}

	private String getRandomModel() {
		return UUID.randomUUID().toString().substring(0, 8);
	}

	public List<Car> getDroppedCars() {
		return droppedCars;
	}
	
	public void deleteCar() {  
        carsSmall.remove(selectedCar);  
    }  

}
