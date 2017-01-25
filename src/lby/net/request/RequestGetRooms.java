/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lby.net.request;

import java.io.DataInputStream;
import java.io.IOException;
import lby.net.response.ResponseGetRooms;

/**
 *
 * @author yanxing wang
 */
public class RequestGetRooms extends GameRequest {
    
    @Override
    public void parse(DataInputStream dataInput) throws IOException {
    }

    @Override
    public void process() throws Exception {
        ResponseGetRooms response = new ResponseGetRooms();     
        client.add(response);
    }
}
