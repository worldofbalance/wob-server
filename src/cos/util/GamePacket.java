package cos.util;

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
public final class GamePacket {

    private final short packet_id;
    private final GamePacketStream buffer = new GamePacketStream();

    public GamePacket(short packet_id) {
        addShort16(this.packet_id = packet_id);
    }

    public short getID() {
        return packet_id;
    }

    public void addShort16(short value) {
        buffer.add(value);
    }

    public void addInt32(int value) {
        buffer.add(value);
    }

    public void addBoolean(boolean b) {
        byte[] bytes = new byte[1];
        bytes[0] = (byte) (b ? 1 : 0);
        addBytes(bytes);
    }

    public void addBytes(byte[] bytes) {
        buffer.add(bytes);
    }

    public void addString(String str) {
        buffer.add((short) str.length());
        buffer.add(str.getBytes());
    }

    public void addFloat(float float_val) {
        addInt32(Float.floatToIntBits(float_val));
    }

    public int size() {
        return buffer.size();
    }

    public byte[] getBytes() {
        return buffer.toByteArray();
    }
}