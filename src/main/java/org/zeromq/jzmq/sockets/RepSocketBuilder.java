package org.zeromq.jzmq.sockets;

import org.zeromq.ZMQ;
import org.zeromq.api.Socket;
import org.zeromq.api.SocketType;
import org.zeromq.jzmq.ManagedContext;
import org.zeromq.jzmq.ManagedSocket;

public class RepSocketBuilder extends SocketBuilder {
    public RepSocketBuilder(ManagedContext managedContext) {
        super(managedContext, SocketType.REP);
    }

    @Override
    public Socket connect(String url) {
        throw new UnsupportedOperationException("REP sockets must be bound, not connected.");
    }

    @Override
    public Socket bind(String url) {
        ZMQ.Context zmqContext = context.getZMQContext();
        ZMQ.Socket socket = zmqContext.socket(this.getSocketType().getType());
        socket.setLinger(getLinger());
        socket.setRcvHWM(getRecvHWM());
        socket.setSndHWM(getSendHWM());
        socket.bind(url);
        return new ManagedSocket(context, socket);
    }
}
