package com.example.android.detail_transaksi.config;

/**
 * Created by x453 on 08/02/2017.
 */

public class Config {

    public static final String URL = "http://sipeja.pe.hu/";

    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String MyPREFERENCES = "MyPrefs" ;

    //This would be used to store the email of current logged in user
    public static final String Name = "nameKey";
    public static final String NamePengguna = "npKey";
    public static final String Password = "passKey";
    public static final String NIP = "nipKey";
    public static final String Email = "emailKey";

    //JSON for log actvitity
    public static final String KEY_USERNAME = "username";
    public static final String KEY_AKTIVITAS = "aktivitas";
    public static final String KEY_WAKTU = "updated_at";
    public static final String JSON_ARRAY = "result";

    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";

    //untuk menghitung jumlah transaksi
    public  static Integer index = 0;

    //untuk detail
    public static String kode = "Kode_transaksi";

    public  static final String kode_transaki="kode";
    public  static final String nama_lab="lab";
    public  static final String nama_kontak="kontak";
}
