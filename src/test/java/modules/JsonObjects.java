package modules;

public class JsonObjects {

    public class ShowWeather {
            public Coord_info coord;
            //public Weather_info weather;
            public String base;
            public Main_info main;
            public int visibikity;
            public Wind_info wind;
            public Clouds_info clouds;
            public long dt;
            public Sys_info sys;
            public int timezone;
            public long id;
            public String name;
            public int cod;

            class Coord_info {
                public float lon;
                public float lat;
            }
            /*class Weather_info {
                public int id;
                public String main;
                public String description;
                public String icon;
            }*/
            class Main_info {
                public float temp;
                public float fells_like;
                public float temp_min;
                public float temp_max;
                public int pressure;
                public int humidity;
                public String main;
                public String description;
                public String icon;
            }
            class Wind_info {
                public float speed;
                public int deg;
            }
            class Clouds_info {
                public int all;
            }
            class Sys_info {
                public int type;
                public int id;
                public float message;
                public String country;
                public long sunrise;
                public long sunset;
            }
            String message;
        //@Override
        public String jsonString() {
            return "[ region: " + coord.lat + sys.country
                    + ", city: " + name + ", temp: " + main.temp
                    + " ][error: " + message + " ] cod: " + cod;
        }
    }
}
