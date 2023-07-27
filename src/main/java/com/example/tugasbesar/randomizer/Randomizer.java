package com.example.tugasbesar.randomizer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Randomizer {
//	String jam_tayang;

	Random random = new Random( );
	static String[] address1Randomizer = {
			"123 Main Street",
			"456 Elm Avenue",
			"789 Oak Lane",
			"321 Maple Drive",
			"987 Pine Court",
			"654 Cedar Road",
			"876 Spruce Street",
			"543 Birch Avenue",
			"210 Walnut Lane",
			"789 Cherry Drive",
			"234 Willow Court",
			"567 Poplar Road",
			"890 Ash Street",
			"123 Oak Lane",
			"456 Elm Avenue",
			"789 Maple Drive",
			"321 Pine Court",
			"654 Cedar Road",
			"876 Spruce Street",
			"543 Birch Avenue"
	};

	String[] address2Randomizer = {
			"123 Main Street",
			"456 Elm Avenue",
			"789 Oak Lane",
			"321 Maple Drive",
			"987 Pine Court",
			"654 Cedar Road",
			"876 Spruce Street",
			"543 Birch Avenue",
			"210 Walnut Lane",
			"789 Cherry Drive",
			"234 Willow Court",
			"567 Poplar Road",
			"890 Ash Street",
			"123 Oak Lane",
			"456 Elm Avenue",
			"789 Maple Drive",
			"321 Pine Court",
			"654 Cedar Road",
			"876 Spruce Street",
			"543 Birch Avenue"
	};

	String[] companyNamesRandomizer = {
			"Apple",
			"Microsoft",
			"Google",
			"Amazon",
			"Facebook",
			"Tesla",
			"Netflix",
			"Adobe",
			"Samsung",
			"IBM",
			"Intel",
			"Oracle",
			"HP",
			"Dell",
			"Sony",
			"Cisco",
			"Nvidia",
			"Uber",
			"Airbnb",
			"Twitter"
	};

	String[] lastNameRandomizer = {
			"Smith",
			"Johnson",
			"Williams",
			"Brown",
			"Jones",
			"Miller",
			"Davis",
			"Garcia",
			"Rodriguez",
			"Wilson",
			"Martinez",
			"Anderson",
			"Taylor",
			"Thomas",
			"Moore",
			"Jackson",
			"Martin",
			"Lee",
			"Harris",
			"Clark"
	};

	String[] contactNumberRandomizer = {
			"+1 123-456-7890",
			"+44 1234 567890",
			"+61 12 3456 7890",
			"+49 1234 567890",
			"+33 1 23 45 67 89",
			"+81 123-456-7890",
			"+55 12 3456-7890",
			"+91 12345 67890",
			"+52 123 456 7890",
			"+86 123 4567 8901"
	};


	String[] firstNamesRandomizer = {
			"John",
			"Jane",
			"Michael",
			"Emily",
			"William",
			"Olivia",
			"James",
			"Sophia",
			"Robert",
			"Isabella",
			"David",
			"Mia",
			"Joseph",
			"Charlotte",
			"Daniel",
			"Amelia",
			"Matthew",
			"Harper",
			"Andrew",
			"Evelyn"
	};

	String[] countriesRandomizer = {
			"USA",
			"Canada",
			"Germany",
			"Australia",
			"France",
			"Japan",
			"Brazil",
			"India",
			"Mexico",
			"China",
			"Spain",
			"Italy",
			"Netherlands",
			"Sweden",
			"South Korea",
			"United Kingdom",
			"Switzerland",
			"Russia",
			"Argentina",
			"South Africa"
	};

	//	todo: 1.
	String[] statusOrder = {
			"Cancelled",
			"Disputed",
			"In Process",
			"Resolved",
			"Shipped",
	};

	String [] jam_tayang = {
			"Siang",
			"Sore",
			"Malam"
	};

	String[] statusShippingComments = {
			"Order processed and ready for shipment.",
			"Package picked up by the carrier.",
			"Shipping label created.",
			"Items packed and prepared for shipping.",
			"Package in transit to destination.",
			"Shipping in progress.",
			"Package departed from origin.",
			"In transit to delivery location.",
			"Out for delivery.",
			"Package arrived at local distribution center.",
			"Package held at customs for clearance.",
			"Delayed due to weather conditions.",
			"Delivery rescheduled.",
			"Address correction needed.",
			"Package rerouted for faster delivery.",
			"Delivery attempted, recipient not available.",
			"Delivery exception, further action required.",
			"Package returned to sender.",
			"Delivery completed with signature.",
			"Package delivered to alternative address."
	};




//	todo :
//   1. Buat status order menjadi sebuah random generator dan
//	 menjadi pilihan enum untuk input manual.
//	 -
//	 2. Order Barang harus mengaitkan customerID,
//	 maka lakukan pengecekan terhadap adanya customerID, harus dikaitkan secara default.

	public String generateRandomCompanyName () {
		int index = random.nextInt(companyNamesRandomizer.length);
		return companyNamesRandomizer[index];
	}

	public String generateRandomJamTayang () {
		int index = random.nextInt(jam_tayang.length);
		return jam_tayang[index];
	}

	public String generateRandomLastName () {


		int index = random.nextInt(lastNameRandomizer.length);
		return lastNameRandomizer[index];
	}

	public String generateRandomFirstName () {


		int index = random.nextInt(firstNamesRandomizer.length);
		return firstNamesRandomizer[index];
	}

	public String generateRandomCountry () {
		int index = random.nextInt(countriesRandomizer.length);
		return countriesRandomizer[index];
	}

	public String generateRandomAddress1 () {
		int index = random.nextInt(address1Randomizer.length);
		return address1Randomizer[index];
	}

	public String generateRandomAddress2 () {
		int index = random.nextInt(address2Randomizer.length);
		return address2Randomizer[index];
	}

	public String generateRandomContact () {
		int index = random.nextInt(contactNumberRandomizer.length);
		return contactNumberRandomizer[index];
	}

	public String generateRandomStatus () {
		int index = random.nextInt(statusOrder.length);
		return statusOrder[index];
	}

	public String generateRandomComments () {
		int index = random.nextInt(statusShippingComments.length);
		return statusShippingComments[index];
	}

	public String dateRandomizer() {
		LocalDate startDate = LocalDate.of(2023, 6, 1);
		LocalDate endDate = LocalDate.of(2023, 9, 30);
		List<String> dateOrderList = new ArrayList<>();

		while (!startDate.isAfter(endDate)) {
			String formattedDate = startDate.toString();
			dateOrderList.add(formattedDate);
			startDate = startDate.plusDays(1);
		}

		Random random = new Random();
		int randomIndex = random.nextInt(dateOrderList.size());
		return dateOrderList.get(randomIndex);
	}
}
