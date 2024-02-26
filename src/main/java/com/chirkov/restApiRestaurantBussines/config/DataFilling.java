package com.chirkov.restApiRestaurantBussines.config;

import com.chirkov.restApiRestaurantBussines.models.*;
import com.chirkov.restApiRestaurantBussines.services.*;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
public class DataFilling {
    @Bean
    CommandLineRunner runnerRole(RoleService roleService) {
        return args -> {
            Role user = new Role("USER", RoleEnum.ROLE_USER);
            Role admin = new Role("ADMIN", RoleEnum.ROLE_ADMIN);
            roleService.save(user);
            roleService.save(admin);
        };
    }

    @Bean
    CommandLineRunner runnerDiscount(DiscountService discountService, Faker faker) {
        return args -> {
            Discount zero = new Discount();
            zero.setName("ZERO");
            zero.setSale(DiscountEnum.ZERO);
            Discount five = new Discount();
            five.setName("FIVE");
            five.setSale(DiscountEnum.FIVE);
            Discount ten = new Discount();
            ten.setName("TEN");
            ten.setSale(DiscountEnum.TEN);

            Discount fifteen = new Discount();
            fifteen.setName("FIFTEEN");
            fifteen.setSale(DiscountEnum.FIFTEEN);

            Discount twenty = new Discount();
            twenty.setName("TWENTY");
            twenty.setSale(DiscountEnum.TWENTY);

            discountService.saveAll(List.of(zero, five, ten, fifteen, twenty));
        };
    }

    @Bean
    CommandLineRunner runnerPeople(PeopleService peopleService, Faker faker) {
        return args -> {
            List<Person> fakePeople = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                Person person = new Person();
                person.setName(faker.name().name());
                person.setLastName(faker.name().lastName());
                person.setUsername(faker.name().username());
                person.setEmail(faker.internet().emailAddress());
                person.setPassword(person.getUsername());
                Date between = faker.date().between(peopleService.getStartYear(), peopleService.getEndYear());
                person.setYearOfBirth(between.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear());
                person.setPhoneNumber("89" + faker.number().numberBetween(100000000, 999999999));
                fakePeople.add(person);
            }
            peopleService.saveAll(fakePeople);
        };
    }

    @Bean
    CommandLineRunner runnerStaticPeople(PeopleService peopleService, Faker faker) {
        return args -> {

            Person wch = new Person();
            wch.setName("Valerii");
            wch.setLastName("Chirkov");
            wch.setUsername("vchirkov");
            wch.setEmail("zx84058405@live.com");
            wch.setPassword("vchirkov");
            wch.setYearOfBirth(1990);
            wch.setPhoneNumber("89043568304");
            peopleService.saveAdmin(wch);


            Person ignat = new Person();
            ignat.setName("Ignat");
            ignat.setLastName("Kupryashin");
            ignat.setUsername("ignat");
            ignat.setEmail("kupryashin@gmail.com");
            ignat.setPassword("ignat");
            ignat.setYearOfBirth(1985);
            ignat.setPhoneNumber("89153007949");
            peopleService.saveAdmin(ignat);

        };
    }

    @Bean
    CommandLineRunner runnerIngredients(IngredientsService ingredientsService, Faker faker) {
        return args -> {
            for (int i = 0; i < 30; i++) {
                Ingredients ingredient = new Ingredients();
                ingredient.setIngredientName(faker.food().ingredient());
                ingredient.setRemnant(faker.number().numberBetween(1, 100));
                ingredient.setDescription(faker.lorem().paragraph(3));
                ingredient.setVegan(faker.random().nextBoolean());
                ingredient.setSpicy(faker.random().nextBoolean());
                ingredientsService.save(ingredient);
            }
        };
    }


    @Bean
    CommandLineRunner runnerUnitsOfMeasurement(UnitsOfMeasurementService ofMeasurementService, Faker faker) {
        return args -> {
            UnitsOfMeasurement unit = new UnitsOfMeasurement();
            unit.setName("GRAMMES");
            unit.setUnitOfMeasurement(EnumUnitsOfMeasurement.GRAMMES);
            unit.setCommentary("1 kilogramme = 1000 grammes");
            ofMeasurementService.save(unit);

            unit = new UnitsOfMeasurement();
            unit.setName("KILOGRAMMES");
            unit.setUnitOfMeasurement(EnumUnitsOfMeasurement.KILOGRAMMES);
            unit.setCommentary("1 kilogramme = 1000 grammes");
            ofMeasurementService.save(unit);

            unit = new UnitsOfMeasurement();
            unit.setName("PIECES");
            unit.setUnitOfMeasurement(EnumUnitsOfMeasurement.PIECES);
            unit.setCommentary("1 piece = 1 piece");
            ofMeasurementService.save(unit);

            unit = new UnitsOfMeasurement();
            unit.setName("PINCH");
            unit.setUnitOfMeasurement(EnumUnitsOfMeasurement.PINCH);
            unit.setCommentary("1 pinch = 1 pinch = 1.5 grammes");
            ofMeasurementService.save(unit);

            unit = new UnitsOfMeasurement();
            unit.setName("LITERS");
            unit.setUnitOfMeasurement(EnumUnitsOfMeasurement.LITERS);
            unit.setCommentary("1 liter = 1000 grammes = 1000 ml");
            ofMeasurementService.save(unit);

            unit = new UnitsOfMeasurement();
            unit.setName("MILLILITERS");
            unit.setUnitOfMeasurement(EnumUnitsOfMeasurement.MILLILITERS);
            unit.setCommentary("1 liter = 1 liter = 1000 grammes = 1000 ml");
            ofMeasurementService.save(unit);

            unit = new UnitsOfMeasurement();
            unit.setName("TEASPOON");
            unit.setUnitOfMeasurement(EnumUnitsOfMeasurement.TEASPOON);
            unit.setCommentary("1 teaspoon = 5 grammes");
            ofMeasurementService.save(unit);

            unit = new UnitsOfMeasurement();
            unit.setName("TABLESPOON");
            unit.setUnitOfMeasurement(EnumUnitsOfMeasurement.TABLESPOON);
            unit.setCommentary("1 tablespoon = 15 grammes");
            ofMeasurementService.save(unit);

        };

    }


    @Bean
    CommandLineRunner runnerStateFromTables(StateFromTablesService stateFromTablesService) {
        return args -> {
            StateFromTable stateFromTables = new StateFromTable();
            stateFromTables.setValue(StateFromTableEnum.RESERVE);
            stateFromTables.setName("RESERVE");
            stateFromTablesService.save(stateFromTables);

            stateFromTables = new StateFromTable();
            stateFromTables.setValue(StateFromTableEnum.READY_TO_BOARD);
            stateFromTables.setName("READY_TO_BOARD");
            stateFromTablesService.save(stateFromTables);

            stateFromTables = new StateFromTable();
            stateFromTables.setValue(StateFromTableEnum.GUESTS_AT_THE_TABLE);
            stateFromTables.setName("GUESTS_AT_THE_TABLE");
            stateFromTablesService.save(stateFromTables);

            stateFromTables = new StateFromTable();
            stateFromTables.setValue(StateFromTableEnum.ASKED_FOR_THE_BILL);
            stateFromTables.setName("ASKED_FOR_THE_BILL");
            stateFromTablesService.save(stateFromTables);

            stateFromTables = new StateFromTable();
            stateFromTables.setValue(StateFromTableEnum.PAID_THE_BILL);
            stateFromTables.setName("PAID_THE_BILL");
            stateFromTablesService.save(stateFromTables);

            stateFromTables = new StateFromTable();
            stateFromTables.setValue(StateFromTableEnum.WAITING_TO_BOARD);
            stateFromTables.setName("WAITING_TO_BOARD");
            stateFromTablesService.save(stateFromTables);
        };

    }


    @Bean
    CommandLineRunner runnerDish(DishesService service) {
        return args -> {
            service.save(saveDish("Coca Cola", 1.5, 0.5, 40, 0, 0, 0, "https://avatars.mds.yandex.net/get-mpic/1901070/img_id7460934699373157446.jpeg/orig"));
            service.save(saveDish("Coca Cola Zero", 1.5, 0.5, 40, 0, 0, 0, "https://www.insidehook.com/wp-content/uploads/2021/07/coke_zero.jpg?resize=50"));
            service.save(saveDish("Pasta Carbonara", 12.99, 250.0, 700, 15, 20, 50, "https://pastaletto.com/wp-content/uploads/2023/04/carbonara-horizontal-superJumbo-v7.jpg"));
            service.save(saveDish("Goulash", 10.99, 300.0, 800, 18, 25, 40, "https://montisbar.ru/wp-content/uploads/f/b/3/fb3e2d3cfcd63b8381683bf41ad37172.jpeg"));
            service.save(saveDish("Paella", 14.99, 400.0, 900, 20, 22, 55, "https://www.unileverfoodsolutions.ca/dam/global-ufs/mcos/nam/ufs-website/article-page/1260x741_Paella.jpg"));
            service.save(saveDish("Pizza Margherita", 9.99, 350.0, 750, 12, 15, 40, "https://mykaleidoscope.ru/x/uploads/posts/2022-09/1663871439_24-mykaleidoscope-ru-p-pitstsa-chetire-sira-yeda-pinterest-26.jpg"));
            service.save(saveDish("Fish and Chips", 11.99, 300.0, 850, 20, 30, 50, "https://pitportal.ru/wp-content/uploads/2016/05/Fish-and-Chips-LS-1500x1000-1.jpg"));
            service.save(saveDish("Cordon Bleu", 13.99, 400.0, 950, 25, 28, 45, "https://i.pinimg.com/originals/cd/2e/6d/cd2e6d70889279208084893264cda02b.jpg"));
            service.save(saveDish("Moussaka", 12.99, 350.0, 800, 15, 20, 55, "https://ohotamyasa.ru/wp-content/uploads/e/8/d/e8d4879786d62389301b97c9610fa8ca.jpeg"));
            service.save(saveDish("Lasagna", 11.99, 300.0, 900, 18, 25, 45, "https://i.pinimg.com/originals/90/21/4e/90214eae386d69866431f8d9e14d0a92.jpg"));
            service.save(saveDish("Wiener Schnitzel", 10.99, 250.0, 750, 20, 30, 40, "https://ohotamyasa.ru/wp-content/uploads/4/9/e/49ebca3d51334b6a7bcf4a1913f98e31.jpeg"));
            service.save(saveDish("Ratatouille", 9.99, 300.0, 600, 10, 15, 50, "https://montisbar.ru/wp-content/uploads/4/f/f/4ff379372009c055fdcfc8e6d6552e46.jpeg"));
            service.save(saveDish("Sarma", 12.99, 350.0, 800, 15, 17, 40, "https://3.bp.blogspot.com/-hROf94Bqd50/WhM-LJHquDI/AAAAAAAAUrs/jNpbOtfQArkWArnWnoast9l4LCl_HMFnACLcBGAs/s1600/karalahanasarmasitarifleri.JPG"));
            service.save(saveDish("Borscht", 10.99, 300.0, 700, 12, 18, 35, "https://img.razrisyika.ru/kart/56/221410-borsch-6.jpg"));
            service.save(saveDish("Pierogi", 8.99, 250.0, 650, 10, 12, 40, "https://staticcookist.akamaized.net/wp-content/uploads/sites/22/2021/08/pierogi-1.jpg"));
            service.save(saveDish("Cannelloni", 11.99, 300.0, 850, 20, 25, 45, "https://www.passion.ru/thumb/1280x720/smart/filters:quality(75)/imgs/2018/11/14/10/2445403/8970fb348b3a46c39867e0b0bfa8330cc150e236.jpg"));
            service.save(saveDish("Tiramisu", 7.99, 200.0, 500, 8, 12, 40, "https://klike.net/uploads/posts/2023-03/1679375311_3-10.jpg"));
            service.save(saveDish("Gazpacho", 9.99, 250.0, 400, 5, 10, 30, "https://montisbar.ru/wp-content/uploads/8/0/7/807f7471628f294f9d4db0be7d0d7570.jpeg"));
            service.save(saveDish("Shepherd's Pie", 11.99, 350.0, 900, 20, 25, 50, "https://sun9-48.userapi.com/impg/WJ2-eoqevLwk2r99jezh954gk_9XH28OpWM5tw/34LeBoxWsgM.jpg?size=1200x720&quality=96&sign=d678cf0323a899f094e99aefc123ad10&type=album"));
            service.save(saveDish("Fish Soup", 9.99, 300.0, 600, 10, 12, 35, "https://activefisher.net/wp-content/uploads/a/a/5/aa5ab7f7b80df0c7e1a3c6e70f18f266.jpg"));
            service.save(saveDish("Rösti", 8.99, 250.0, 500, 8, 15, 35, "https://www.thespruceeats.com/thmb/y0W8T21gTtn7TvVYJlothnU1W54=/4494x3000/filters:no_upscale():max_bytes(150000):strip_icc()/roesti-step-by-step-1447190-step-07-5bd8c80ac9e77c0026656bb4.jpg"));

        };
    }

    private Dishes saveDish(String name, double price, double weight, int calories, int protein, int fat, int carbs, String imageUrl) {
        Dishes dish = new Dishes();
        dish.setName(name);
        dish.setPrice(price);
        dish.setWeight(weight);
        dish.setCalories(calories);
        dish.setProteins(protein);
        dish.setFats(fat);
        dish.setCarbohydrates(carbs);
        dish.setImageURL(imageUrl);

        return dish;
    }

}
