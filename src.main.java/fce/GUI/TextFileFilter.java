package fce.GUI;

import javax.swing.filechooser.FileFilter;
/**
 * Class which extend standard Swing element FileFilter and
 * set 
 * @author Tanya Ohotnik
 */
public class TextFileFilter extends FileFilter 
{
    private String ext;
    /**
     * Constructor
     * @param ext - expansion of file
     */
    public TextFileFilter(String ext)
    {
        this.ext=ext;
    }
    /**
     * @return true if file name right
     */
    public boolean accept(java.io.File file) 
    {
         if (file.isDirectory()) return true;
         return (file.getName().endsWith(ext));
    }
    /**
     * @return description of file name
     */
    public String getDescription() 
    {
         return "*"+ext;
    }
}