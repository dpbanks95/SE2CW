package uk.ac.uea.framework;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.SharedPreferences;

/**
 * Interface for File Input/Output
 */
public interface FileIO {
    /**
     * Read from a file
     * @param file
     * @return InputStream
     * @throws IOException
     */
    public InputStream readFile(String file) throws IOException;

    /**
     * Write to file
     * @param file
     * @return OutputStream
     * @throws IOException
     */
    public OutputStream writeFile(String file) throws IOException;

    /**
     * read asset
     * @param file
     * @return InputStream
     * @throws IOException
     */
    public InputStream readAsset(String file) throws IOException;

    /**
     * Get the shared preferences
     * @return SharedPreferences
     */
    public SharedPreferences getSharedPref();
}
