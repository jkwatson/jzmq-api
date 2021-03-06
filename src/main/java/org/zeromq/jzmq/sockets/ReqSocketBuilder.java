package org.zeromq.jzmq.sockets;

import org.zeromq.ZMQ;
import org.zeromq.api.Socket;
import org.zeromq.api.SocketType;
import org.zeromq.jzmq.ManagedContext;
import org.zeromq.jzmq.ManagedSocket;

public class ReqSocketBuilder extends SocketBuilder {
    public ReqSocketBuilder(ManagedContext context) {
        super(context, SocketType.REQ);
    }

    @Override
    public Socket connect(String url) {
        ZMQ.Context zmqContext = context.getZMQContext();
        ZMQ.Socket socket = zmqContext.socket(this.getSocketType().getType());
        socket.setLinger(getLinger());
        socket.setSndHWM(getSendHWM());
        socket.setRcvHWM(getRecvHWM());
        socket.connect(url);
        return new ManagedSocket(context, socket);
    }

    @Override
    public Socket bind(String url) {
        throw new UnsupportedOperationException("REQ sockets should be connected, rather than bound.");
    }
}
