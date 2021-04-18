package modules;

import com.google.gson.Gson;
import java.net.URL;
import static modules.Logging.*;
import modules.JsonObjects.*;


public class ApiRequests {

  private static String apiRequest (String url, String method, String cookie) {
      if (url != null) try {
        URL www = new URL(url);
        if (method.equals("GET"))
            return MyTinyHttpClient.getResponseFromHttpUrl(www, cookie);
        else
            return MyTinyHttpClient.sendPost(www, method, cookie);
      } catch (Exception e) {
          e.printStackTrace();
      }
      writeStrToFile(logname,"url `"+url+"` is empty or wrong!");
      return "";
  }
  public static float getWeather (){
      //ShowWeather
      Gson gson = new Gson();
      String vvv = apiRequest("https://api.openweathermap.org/data/2.5/weather?lat=59.917&lon=30.250&appid=ace3c482ec0edcfec570a8f8c274a1e6", "GET", "");
      String json = null;
      float temp = -100;
      try {
          ShowWeather weather = gson.fromJson(vvv, ShowWeather.class);
          writeStrToFile(logname, vvv);
          json = weather.toString();
          writeStrToFile(logname, json);
          writeStrToFile(logname, "TEMP: " + weather.main.temp);
          temp=weather.main.temp - 270;
      } catch (Exception e) {
          e.printStackTrace();
      }
      return temp;
  }
}
