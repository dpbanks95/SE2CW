//package uk.ac.uea.activityprogram;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//import android.content.res.AssetManager;
//import android.os.Environment;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
///**
// * Created by ChanelleRichardson on 21/11/2016.
// * https://docs.google.com/spreadsheets/d/1sOHSXjEKIJ1-0dsrE8R-Qr0wDZNmkKNRyznimkUuVzQ/export?format=xlsx
// */
//
//public class DatabaseFileIO implements FileIO {
//
//    Context context;
//    AssetManager assets;
//    String externalStoragePath;
//
//    public DatabaseFileIO(Context context) {
//        this.context = context;
//        this.assets = context.getAssets();
//        this.externalStoragePath = Environment.getExternalStorageDirectory()
//                .getAbsolutePath() + File.separator;
//
//    }
//
//    @Override
//    public InputStream readFile(String file) throws IOException {
//        XSSFWorkbook wb = new XSSFWorkbook(this.assets.open("TESTActivityPlannerData.xlsx"));
//        int numSheets = wb.getNumberOfSheets();
//        return null;
//    }
//
//    @Override
//    public OutputStream writeFile(String file) throws IOException {
//        return null;
//    }
//
//    @Override
//    public InputStream readAsset(String file) throws IOException {
//        return null;
//    }
//
//    @Override
//    public SharedPreferences getSharedPref() {
//        return null;
//    }
//}
