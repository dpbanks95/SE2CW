package uk.ac.uea.framework.implementation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Environment;
import android.preference.PreferenceManager;

import uk.ac.uea.framework.FileIO;

/**
 * Manages the reading and writing of files
 */
public class AndroidFileIO implements FileIO {
    Context context;
    AssetManager assets;
    String externalStoragePath;

    /**
     * Class constructor given a Context
     * @param context
     */
    public AndroidFileIO(Context context) {
        this.context = context;
        this.assets = context.getAssets();
        this.externalStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator;
    }

    /**
     * Read an asset file
     * @param file
     * @return InputStream
     * @throws IOException
     */
    @Override
    public InputStream readAsset(String file) throws IOException {
        return assets.open(file);
    }

    /**
     * Read a file
     * @param file
     * @return InputStream
     * @throws IOException
     */
    @Override
    public InputStream readFile(String file) throws IOException {
        return new FileInputStream(externalStoragePath + file);
    }

    /**
     * Write to a file
     * @param file
     * @return OutputStream
     * @throws IOException
     */
    @Override
    public OutputStream writeFile(String file) throws IOException {
        return new FileOutputStream(externalStoragePath + file);
    }

    /**
     * Get the shared preferences for this Context
     * @return SharedPreferences
     */
    public SharedPreferences getSharedPref() {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
