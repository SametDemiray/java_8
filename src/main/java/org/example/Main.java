package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



public class Main {




    public static void main(String[] args) {

        Person person = new Person();
        List<Person> people = new ArrayList<>();



        List<User> users = new ArrayList<>();
        users.add(new User(1, "Samet"));
        users.add(new User(2, "Hilal"));
        users.add(new User(3, "Berkay"));
        users.add(new User(4, "Elif"));
        users.add(new User(5, "Arda"));
        users.add(new User(6, "Mustafa"));
        users.add(new User(7, "Ali"));
        users.add(new User(8, "Veli"));

        // stream() fonksiyonu kelime anlamı olarak akış, users list in üzerinden sırayla gidip işlem yapmamaızı sağlıyor.
        // forEach() fonksiyonu Herbir eleman için parantez içerisindeki işlem neyse onu yap demek. yani iterate edip execute edecek.
        // yani aşağıdaki işlem her bir user için userın to string metodu ile yazdırılmasını sağlıyor.
        // lambda ifadesinden önce yazılan user ise bu ifadenin bir parametresi oluyor. bu ifadeyi tanımlayan isim gibi gibi...
        // parametrenin yapacağı işlemleri ise sırasıyla ok işaretini sağına yazıyoruz. Burası da bu fonksiyorunun gövdesi olmuş oluyor.
        // Bazen bu fonksiyona lamda function yerine, Array function da denilebiliyor.
        // Birden fazla işlem kullanmak istediğimizde fonksiyonun gövde kısmına süslü parantezler açarak,
        // yapmak istediğimiz işlemleri yazıyoruz. Normal Fonksiyon yerine inline bir fonksiyon yazmış olduk.
        // Bu işlem for döngüsü ile de yapılabilirdi fakat stream ile daha kompakt( kısa ve öz ) ve okunanilir bir kod yazmış olduk.

        System.out.println("Java_8");
        users.stream().forEach(user -> {
            System.out.println(user.toString());
        });


        // Her user için talk methodunu çağırdık.

        //   users.stream().forEach(user -> user.talk());
        // Bu Alttaki ifadenin üstteki ile hiçbir farkı yok. (::) ifadesi User clasının içerisinde talk methodunu çağır demek. Yani
        // stream ile işlem akış sağladık foreach ile herbir User için parantez içindeki ifade ile talk methodunu çağırdık.

        System.out.println("----------forEach-------------");
        users.stream().forEach(User::talk);

        // Kısaca tek bir satır ile method çağırma fonksiyon kullanma işlemi yaptık.

        System.out.println("-----------filter()-------------");


        // filter() metodu
        // user içerisine id si 5 ten küçük olanları al demek bir nevi filtreleme fonksiyonu
        // döndürdükten sonra bunları listeden silmiyor

        long count = users.stream().filter(user -> user.id < 5).count();
        System.out.println("5'ten Küçük Eleman sayısı = " + count + "'tür.");

        // Eleman sayısı değil de elemanları görmek istersek
        // .collect() metodu

        System.out.println("------------filter()-collect()-----------");

        // filter() ile bunun id si 5 ten küçük olanları topladık,
        //collect() ile bunları topladık ve ayrı bir listeye çevirdik bunu da  filteredListe attık.

        List<User> filteredList = users.stream().filter(user -> user.id < 5).collect(Collectors.toList());

        // bakalım bu filteredListin içerisinde neler varmış ?????
        // yine aynı şekilde foreach ile hepsini tek tek geziyoz.
        // User Sınıfından 5ten küçük olanların ismini ekrana talk metodu ile yazdırıyoruz.

        filteredList.stream().forEach(User::talk);


        System.out.println("-----------map()-------------");

        // map() fonksiyonu bu User listesindeki her bir elemana gidip üzerinde değişiklik yapıp,
        //onları yeni bir elemana map lememizi sağlıyor. Örneğin;
        // Mevcut listemizi değiştirmemek için collect metoduyla toplayıp collecttors ile yeni bir listeye atıyoruz.
        //bu listemizin adı da

        List<User> mappedList = users.stream().map(user -> new User(user.id + 100, user.name)).collect(Collectors.toList());

        // şimdi bu maplattiğimiz listeyi tekrar  forEach ile tek tek gezelim.

        mappedList.stream().forEach(user -> System.out.println(user.toString()));

        // filter ve map ile elemanları gezip onları değiştirebilerceğimiz gibi,
        // bir listeyi başka tipe de dönüştürebiliriz
        // yukarıdaki gibi tek bir argüman alınabileceği gibi aşağıdaki gibi birden fazla argüman da alabilir.
        //int ve string gibi... ilk eleman int tipinde ikinci eleman ise string tipinde bir değer alıyor.

        System.out.println("-----------map()-ile-tip-dönüşüm------------");

        Map<Integer, String> userMaplist = users.stream().collect(Collectors.toMap(user -> user.id, user -> user.name));
        userMaplist.forEach((integer, s) -> System.out.println(integer + " : " + s));

    }

    static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

        public void talk() {
            System.out.println("Hi, I am " + this.name);
        }

    }
}