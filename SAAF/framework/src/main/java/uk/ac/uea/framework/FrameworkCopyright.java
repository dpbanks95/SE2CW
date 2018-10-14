package uk.ac.uea.framework;

/**
 * Abstract class for Copyright notification
 */
public abstract class FrameworkCopyright {
    private String copyrightText = "This application is based on the Simple Android Application Framework. (c) University of East Anglia 2016.";

    /**
     * Get the entire text copyright
     * @return String
     */
    public final String getCopyright(){
        return copyrightText + "\n\n" + getAppCopyright();
    }

    /**
     * Get the app's copyright text
     * @return String
     */
    protected abstract String getAppCopyright();
}
