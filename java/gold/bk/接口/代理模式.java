package com.gold.接口;

public class 代理模式 {
    public static void main(String[] args) {
        ProxyServer ps = new ProxyServer(new Server());
        ps.connect();
    }
}

interface Network {
    void connect();
}

class Server implements Network {

    @Override
    public void connect() {
        System.out.println("Server connecting...");
    }
}

class ProxyServer implements Network {

    private Network network;

    public ProxyServer(Network network) {
        this.network = network;
    }

    public void staff(){
        System.out.println("do something");
    }

    @Override
    public void connect() {
        staff();
        network.connect();
    }
}