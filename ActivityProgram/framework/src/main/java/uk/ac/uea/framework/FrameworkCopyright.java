package uk.ac.uea.framework;

/**
 * Created by ybm14yju on 14/10/2016.
 */

/**
 * Allows application developers to automatically add their own copyright text.
 */
public abstract class FrameworkCopyright {
    private String copyrightText = "“This application is based on the Simple Android Application Framework. (c) University\n" +
            "of East Anglia 2016.";

    public final String getCopyright() {
        return this.copyrightText + "\n\n" + getAppCopyright();
    }

    protected abstract String getAppCopyright();
}
