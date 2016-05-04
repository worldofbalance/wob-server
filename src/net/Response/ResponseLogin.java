/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.Response;

import metadata.Constants;
import metadata.NetworkCode;
import model.Player;
import utility.GamePacket;

/**
 *
 * @author anu
 * The ResponseLogin class contains information about the authentication
 * process.
 */
public class ResponseLogin extends GameResponse {
 


    private short status;
    private Player player;

    public ResponseLogin() {
        responseCode = NetworkCode.SD_GAME_LOGIN;
    }

    @Override
    public byte[] constructResponseInBytes() {
        GamePacket packet = new GamePacket(responseCode);
        packet.addShort16(status);

        if (status == 0) {
            packet.addInt32(player.getPlayer_id());
            packet.addString(player.getUsername());
        }

        return packet.getBytes();
    }

    public void setStatus(short status) {
        this.status = status;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


}
