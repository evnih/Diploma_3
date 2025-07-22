package utils;


import net.datafaker.Faker;



public class TestDataGenerator {
    public static final Faker faker = new Faker();

    public static String randomEmail() {
        return faker.internet().emailAddress();
    }

    public static String randomName() {
        return faker.name().fullName();
    }

    public static String randomPassword() {
        return faker.internet().password(8, 16, true, true);
    }
    public static String randomPassword(int length) {
        return faker.internet().password(length, length, true, true);
    }


}
