package utility;

/**
 * GamePacket sent by the server.
 * BigEndian conversion is done within GamePacketStream.
 *
 * Method names are mostly matched with PyDatagram
 *
 * Example:
 * 	GamePacket packet = new GamePacket(Constants.SMSG_DICE_RES);
 *      packet.addUint16((short)result);
 *      return packet.getBytes();
 *
 *      This makes internally prepares the packet to send
 *
 *      byte[0] = Lo of Packet Length = 0x04
 *      byte[1] = Hi of Packet Length = 0x00
 *      byte[2] = Lo of Constants.SMSG_DICE_RES = 0x1f
 *      byte[3] = Hi of Constants.SMSG_DICE_RES = 0x00
 *      byte[4] = Lo of Result = ?
 *      byte[5] = Hi of Result = 0x00
 */
public class GamePacket {

    private GamePacketStream buf;

    public GamePacket(short responseCode) {
        buf = new GamePacketStream(responseCode);
    }

    public void addShort16(short value) {
        buf.add(value);
    }

    public void addInt32(int value) {
        buf.add(value);
    }

    public void addLong(long value) {
        buf.add(value);
    }

    public void addBoolean(boolean b) {
        byte[] bytes = new byte[1];
        bytes[0] = (byte) (b ? 1 : 0);
        addBytes(bytes);
    }

    public void addBytes(byte[] bytes) {
        buf.add(bytes);
    }

    public void addString(String str) {
        buf.add((short) str.length());
        buf.add(str.getBytes());
    }

    public void addFloat(float float_val) {
        addInt32(Float.floatToIntBits(float_val));
    }

    public int size() {
        return buf.size();
    }

    public byte[] getBytes() {
        return buf.toByteArray();
    }
}