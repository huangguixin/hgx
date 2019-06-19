package com.hgx;


import com.google.common.collect.Maps;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;


/**
 * @author :huangguixin / 2677540604@qq.com
 * @version : 1.0
 */
@Path("/hgx")
public class WsResource {

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public HashMap test(@PathParam("name") String name) {
        System.out.println(name);
        HashMap<String, String> hashMap = Maps.newHashMap();
        hashMap.put("hgx","hgx");
        return hashMap;
    }

}
