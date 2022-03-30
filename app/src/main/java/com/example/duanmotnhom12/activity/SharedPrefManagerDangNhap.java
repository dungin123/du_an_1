//package com.example.duanmotnhom12.activity;
//
//import android.content.Context;
//import android.content.Intent;
//import android.content.SharedPreferences;
//
//import com.example.duanmotnhom12.FromDangNhap.FromDangNhap;
//import com.example.duanmotnhom12.Model.ModelDangNhap;
//
//public class SharedPrefManagerDangNhap {
//
//    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
//    private static final String KEY_USERNAME = "keyusername";
//    private static final String KEY_EMAIL = "keyemail";
//    private static final String KEY_ID = "keyid";
//
//
//   private static SharedPrefManagerDangNhap sharedPrefManager;
//    private static Context mcontext;
//
//    public SharedPrefManagerDangNhap(Context mcontext) {
//        this.mcontext = mcontext;
//    }
//    public static synchronized  SharedPrefManagerDangNhap getInstance(Context context){
//        if (sharedPrefManager == null){
//            sharedPrefManager = new SharedPrefManagerDangNhap(context);
//        }
//        return sharedPrefManager ;
//    }
//    public void userLogin ( ModelDangNhap modelDangNhap){
//        SharedPreferences sharedPreferences = mcontext.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putInt(KEY_ID,modelDangNhap.getId_user());
//        editor.putString(KEY_USERNAME,modelDangNhap.getHoTen_user());
//        editor.putString(KEY_EMAIL,modelDangNhap.getEmail_user());
//        editor.apply();
//
//
////        return new ModelDangNhap(
////          sharedPreferences.getInt(KEY_ID ,-1),
////                sharedPreferences.getString(KEY_USERNAME,null),
////                sharedPreferences.getString(KEY_EMAIL,null)
////        );
//    }
//    public boolean isLoggeIn(){
//        SharedPreferences sharedPreferences =mcontext.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
//        return  sharedPreferences.getString(KEY_USERNAME,null)!= null;
//    }
//    public  ModelDangNhap getUser(){
//        SharedPreferences sharedPreferences = mcontext.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
//        return new ModelDangNhap(
//          sharedPreferences.getInt(KEY_ID ,-1),
//                sharedPreferences.getString(KEY_USERNAME,null),
//                sharedPreferences.getString(KEY_EMAIL,null)
//        );
//    }
//    public void logout() {
//        SharedPreferences sharedPreferences = mcontext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.clear();
//        editor.apply();
//        mcontext.startActivity(new Intent(mcontext, FromDangNhap.class));
//    }
//}
