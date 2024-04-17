package ru.shop.entity;

public record Customer(String id, String name, String phone, long age) {
    public Customer {
        if (age < 14 || age > 150){
            throw new IllegalArgumentException("Age must be between 14 and 150");
        }
        if (name == null || name.isEmpty()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (phone == null || phone.isEmpty()){
            throw new IllegalArgumentException("Phone cannot be empty");
        }
        if (phone.length() != 11){
            throw new IllegalArgumentException("Phone length must be 11");
        }
        boolean hasNotOnlyDigits = false;
        for(int i = 0; i < phone.length() && !hasNotOnlyDigits; i++) {
            if(!Character.isDigit(phone.charAt(i))) {
                hasNotOnlyDigits = true;
            }
        }
        if (hasNotOnlyDigits){
            throw new IllegalArgumentException("Phone cannot contain anything except digits");
        }
    }
    public String getCustomerId(){
        return id;
    }
}

