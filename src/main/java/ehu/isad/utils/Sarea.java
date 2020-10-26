package ehu.isad.utils;



import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Sarea {

    public Sarea() { }

    public String readFromURL(String ISBN) {
        String inputLine = "";
        URL api;
        try {
            api = new URL("https://openlibrary.org/api/books?bibkeys=ISBN:" + ISBN + "&jscmd=details&format=json");
            BufferedReader in = new BufferedReader(new InputStreamReader(api.openStream()));
            URLConnection konexioa = api.openConnection();
            inputLine = in.readLine();
            in.close();
        } catch (MalformedURLException malformedURLException) {
            malformedURLException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        String[] zatiak = inputLine.split("ISBN:" + ISBN + "\": ");
        inputLine = zatiak[1].substring(0, zatiak[1].length() - 1);
        return inputLine;
    }

    public void argazkiaGorde(String url,String fitxategia){
        BufferedImage image;
        try{
            URL URL = new URL(url);
            image = ImageIO.read(URL);
            ImageIO.write(image,"png", new File(Utils.lortuEzarpenak().getProperty("pathToImages")+fitxategia+".png"));
        }catch(IOException ioException){
            ioException.printStackTrace();
        }
    }
}