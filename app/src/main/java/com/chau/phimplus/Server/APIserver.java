package com.chau.phimplus.Server;

public class APIserver {

	//URL  folder chứa API
    private static String base_url = "https://phimhay2k.000webhostapp.com/Server/";

    public static Dataserver getServer()
    {
        return APIRetrofitClient.getClient(base_url).create(Dataserver.class);
    }

}
