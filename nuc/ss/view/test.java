package nuc.ss.view;

import java.lang.reflect.Field;
import java.util.Arrays;

import nuc.ss.entity.User;

public class test {
    public static void main(String[] args) throws NoSuchFieldException, SecurityException {
        Field[] field = User.class.getFields();
        System.out.println(field.length);
        System.out.println(Arrays.toString(field));
    }
}
