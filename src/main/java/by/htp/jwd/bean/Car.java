package by.htp.jwd.bean;


import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.sql.Blob;

public class Car implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String brand;
	private String describeBrand;
	private String bodywork;
	private String power;
	private String transmission;
	private String numberDoors;
	private String year;
	private String photoPath;
	private Blob photoB;
	private String status;
	private Adress adressCar;
	private BufferedImage photo;
	private String photoByte;
	
	public Car() {
		super();
	}

	
	
	public Car(String brand, String describe_of_brand, String bodywork, String power, String transmission,
			String number_of_doors, String year, String photoPath,String status,Adress adressCar ) {
		super();
		this.brand = brand;
		this.describeBrand = describe_of_brand;
		this.bodywork = bodywork;
		this.power = power;
		this.transmission = transmission;
		this.numberDoors = number_of_doors;
		this.year = year;
		this.photoPath = photoPath;
		this.status = status;
		this.adressCar = adressCar;
	}

	public Car(int id, String brand, String describe_of_brand, String bodywork, String power, String transmission, String status) {
		super();
		this.id = id;
		this.brand = brand;
		this.describeBrand = describe_of_brand;
		this.bodywork = bodywork;
		this.power = power;
		this.transmission = transmission;
		this.status = status;
	}
	
	public Car(int id, String brand, String describe_of_brand, String bodywork, String power, String transmission,
			String number_of_doors, String year, String photoPath, String status ) {
		super();
		this.id = id;
		this.brand = brand;
		this.describeBrand = describe_of_brand;
		this.bodywork = bodywork;
		this.power = power;
		this.transmission = transmission;
		this.numberDoors = number_of_doors;
		this.year = year;
		this.photoPath = photoPath;
		this.status = status;
		
	}

	public Car(int id, String brand, String describe_of_brand, String bodywork, String power, String transmission,
			String number_of_doors, String year, String photoPath, String status,  String photoByte, Adress adressCar) {
		super();
		this.id = id;
		this.brand = brand;
		this.describeBrand = describe_of_brand;
		this.bodywork = bodywork;
		this.power = power;
		this.transmission = transmission;
		this.numberDoors = number_of_doors;
		this.year = year;
		this.photoPath = photoPath;
		this.status = status;	
		this.photoByte = photoByte;
		this.adressCar = adressCar;
	}
	public Car(int id, String brand, String describe_of_brand, String bodywork, String power, String transmission,
			String number_of_doors, String year, String photoPath, String status, Blob photoB) {
		super();
		this.id = id;
		this.brand = brand;
		this.describeBrand = describe_of_brand;
		this.bodywork = bodywork;
		this.power = power;
		this.transmission = transmission;
		this.numberDoors = number_of_doors;
		this.year = year;
		this.photoPath = photoPath;
		this.status = status;
		this.photoB = photoB;
		
	}

	public Car(int id, String brand, String describe_of_brand, String bodywork, String power, String transmission,
			String number_of_doors, String year, String photoPath, String status, Blob photoB, BufferedImage photo) {
		super();
		this.id = id;
		this.brand = brand;
		this.describeBrand = describe_of_brand;
		this.bodywork = bodywork;
		this.power = power;
		this.transmission = transmission;
		this.numberDoors = number_of_doors;
		this.year = year;
		this.photoPath = photoPath;
		this.status = status;
		this.photoB = photoB;
		this.photo = photo;
	}



	public String getPhotoByte() {
		return photoByte;
	}



	public void setPhotoByte(String photoByte) {
		this.photoByte = photoByte;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getBrand() {
		return brand;
	}



	public void setBrand(String brand) {
		this.brand = brand;
	}



	public String getDescribeBrand() {
		return describeBrand;
	}



	public void setDescribeBrand(String describeBrand) {
		this.describeBrand = describeBrand;
	}



	public String getBodywork() {
		return bodywork;
	}



	public void setBodywork(String bodywork) {
		this.bodywork = bodywork;
	}



	public String getPower() {
		return power;
	}



	public void setPower(String power) {
		this.power = power;
	}



	public String getTransmission() {
		return transmission;
	}



	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}



	public String getNumberDoors() {
		return numberDoors;
	}



	public void setNumberDoors(String numberDoors) {
		this.numberDoors = numberDoors;
	}



	public String getYear() {
		return year;
	}



	public void setYear(String year) {
		this.year = year;
	}



	public String getPhotoPath() {
		return photoPath;
	}



	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}



	public Blob getPhotoB() {
		return photoB;
	}



	public void setPhotoB(Blob photoB) {
		this.photoB = photoB;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public Adress getAdressCar() {
		return adressCar;
	}



	public void setAdressCar(Adress adressCar) {
		this.adressCar = adressCar;
	}



	public BufferedImage getPhoto() {
		return photo;
	}



	public void setPhoto(BufferedImage photo) {
		this.photo = photo;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adressCar == null) ? 0 : adressCar.hashCode());
		result = prime * result + ((bodywork == null) ? 0 : bodywork.hashCode());
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + ((describeBrand == null) ? 0 : describeBrand.hashCode());
		result = prime * result + id;
		result = prime * result + ((numberDoors == null) ? 0 : numberDoors.hashCode());
		result = prime * result + ((photo == null) ? 0 : photo.hashCode());
		result = prime * result + ((photoB == null) ? 0 : photoB.hashCode());
		result = prime * result + ((photoPath == null) ? 0 : photoPath.hashCode());
		result = prime * result + ((power == null) ? 0 : power.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((transmission == null) ? 0 : transmission.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (adressCar == null) {
			if (other.adressCar != null)
				return false;
		} else if (!adressCar.equals(other.adressCar))
			return false;
		if (bodywork == null) {
			if (other.bodywork != null)
				return false;
		} else if (!bodywork.equals(other.bodywork))
			return false;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (describeBrand == null) {
			if (other.describeBrand != null)
				return false;
		} else if (!describeBrand.equals(other.describeBrand))
			return false;
		if (id != other.id)
			return false;
		if (numberDoors == null) {
			if (other.numberDoors != null)
				return false;
		} else if (!numberDoors.equals(other.numberDoors))
			return false;
		if (photo == null) {
			if (other.photo != null)
				return false;
		} else if (!photo.equals(other.photo))
			return false;
		if (photoB == null) {
			if (other.photoB != null)
				return false;
		} else if (!photoB.equals(other.photoB))
			return false;
		if (photoPath == null) {
			if (other.photoPath != null)
				return false;
		} else if (!photoPath.equals(other.photoPath))
			return false;
		if (power == null) {
			if (other.power != null)
				return false;
		} else if (!power.equals(other.power))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (transmission == null) {
			if (other.transmission != null)
				return false;
		} else if (!transmission.equals(other.transmission))
			return false;
		if (year == null) {
			if (other.year != null)
				return false;
		} else if (!year.equals(other.year))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Car [id=" + id + ", brand=" + brand + ", describeBrand=" + describeBrand + ", bodywork=" + bodywork
				+ ", power=" + power + ", transmission=" + transmission + ", numberDoors=" + numberDoors + ", year="
				+ year + ", photoPath=" + photoPath + ", photoB=" + photoB + ", status=" + status + ", adressCar="
				+ adressCar + ", photo=" + photo + "]";
	}


}
