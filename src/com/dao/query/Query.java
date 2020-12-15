package com.dao.query;

public class Query {
public static final String GetAllUsers = "SELECT * FROM coffeemachine.users";
public static final String GetAllBeverages ="SELECT * FROM coffeemachine.beverages";
public static final String LanguageRussian = "theLocale=ru_RU";
public static final String LanguageEnglish = "theLocale=en_EN";
public static final String AddUser="INSERT INTO coffeemachine.users (login,password,first_name,last_name,email,bill,role_id) "
		+ "VALUES (?,?,?,?,?,?,?)";
public static final String FindUser= "SELECT * FROM coffeemachine.users WHERE login=? and password=?";
}
