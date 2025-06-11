import model.entities.Department;
import model.entities.Seller;

import java.time.LocalDate;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {

        Department obj = new Department(1, "Eletronics");
        System.out.println(obj);

        Seller seller = new Seller(13, "Bob", "bobzinho997@gmail.com", LocalDate.of(2002, 8, 25), 3000.00, obj);
        System.out.println(seller);
    }
}